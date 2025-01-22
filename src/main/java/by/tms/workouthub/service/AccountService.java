package by.tms.workouthub.service;

import by.tms.workouthub.dto.AccountCreateDto;
import by.tms.workouthub.dto.AccountPublicDto;
import by.tms.workouthub.dto.AccountResponseDto;
import by.tms.workouthub.dto.AccountUpdateDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.enums.Role;
import by.tms.workouthub.exceptions.AccessDeniedException;
import by.tms.workouthub.exceptions.AccountAlreadyExistsException;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.mappers.AccountMapper;
import by.tms.workouthub.repository.AccountRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountResponseDto create(AccountCreateDto accountCreateDto) {
        Account newAccount = new Account();
        newAccount.setUsername(accountCreateDto.getUsername());
        newAccount.setPassword(new BCryptPasswordEncoder(11).encode(accountCreateDto.getPassword()));
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setRole(Role.ADMIN);
        try {
            newAccount = accountRepository.save(newAccount);
        }catch (DataIntegrityViolationException e){
            throw new AccountAlreadyExistsException("Account with this name already exists.");
        }
        AccountResponseDto accountResponseDto = accountMapper.toAccountResponseDto(newAccount);
        return accountResponseDto;
    }

    public AccountResponseDto getAccount(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NotFoundEntityException("Account not found"));
        return accountMapper.toAccountResponseDto(account);
    }

    public AccountPublicDto getAccountByUsername(String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(()->new NotFoundEntityException("Account not found"));
            return accountMapper.toAccountPublicDto(account);

    }

    public AccountResponseDto update(AccountUpdateDto accountUpdateDto, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NotFoundEntityException("Account not found"));
        account.setFirstName(accountUpdateDto.getFirstName());
        account.setLastName(accountUpdateDto.getLastName());
        account.setEmail(accountUpdateDto.getEmail());
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponseDto(updatedAccount);
    }

    public void delete(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NotFoundEntityException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()) {
            var acc = account.get();
            return User
                    .withUsername(acc.getUsername())
                    .password(acc.getPassword())
                    .roles(acc.getRole().name())
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }
}
