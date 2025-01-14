package by.tms.workouthub.service;

import by.tms.workouthub.dto.AccountUpdateDto;
import by.tms.workouthub.dto.PublicAccountDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.exceptions.AccessDeniedException;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account) {
        account.setPassword(new BCryptPasswordEncoder(11).encode(account.getPassword()));
        account.setCreatedAt(LocalDateTime.now());
        accountRepository.save(account);
        return account;
    }

    public PublicAccountDto getAccountByUsername(String username) {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()) {
            return ConvertToPublicAccountDto(account.get());
        }
        throw new NotFoundEntityException("Account not found");
    }

    public PublicAccountDto ConvertToPublicAccountDto(Account account) {
        PublicAccountDto publicAccountDto = new PublicAccountDto();
        publicAccountDto.setUsername(account.getUsername());
        publicAccountDto.setFirstName(account.getFirstName());
        publicAccountDto.setLastName(account.getLastName());
        publicAccountDto.setCreatedAt(account.getCreatedAt());
        return publicAccountDto;
    }

    public Account update(AccountUpdateDto accountUpdateDto, String username, Authentication authentication) {
        checkAccount(username, authentication);
       Account account = accountRepository.findByUsername(username).orElseThrow(()-> new NotFoundEntityException("Account not found"));
       account.setFirstName(accountUpdateDto.getFirstName());
       account.setLastName(accountUpdateDto.getLastName());
       account.setEmail(accountUpdateDto.getEmail());
       return account;
    }

    public boolean delete (String username, Authentication authentication) {
        checkAccount(username, authentication);
        Account account  = accountRepository.findByUsername(username).orElseThrow(()-> new NotFoundEntityException("Account not found"));
        accountRepository.delete(account);
        return true;
    }

    public void checkAccount(String username, Authentication authentication) {
        String currentUsername = ((User) authentication.getPrincipal()).getUsername();
        if (!username.equals(currentUsername)) {
            throw new AccessDeniedException("You do not have sufficient access rights to modify this account.");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()) {
            var acc = account.get();
            return User
                    .withUsername(acc.getUsername())
                    .password(acc.getPassword())
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }
}
