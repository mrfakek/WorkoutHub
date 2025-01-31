package by.tms.workouthub.service;

import by.tms.workouthub.dto.AnthropometryCreateDto;
import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.Anthropometry;
import by.tms.workouthub.entity.AnthropometryHistory;
import by.tms.workouthub.exceptions.AccessDeniedException;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.mappers.AnthropometryMapper;
import by.tms.workouthub.repository.AccountRepository;
import by.tms.workouthub.repository.AnthropometryHistoryRepository;
import by.tms.workouthub.repository.AnthropometryRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class AnthropometryService {
    private final AnthropometryRepository anthropometryRepository;
    private final AnthropometryHistoryRepository anthropometryHistoryRepository;
    private final AnthropometryMapper anthropometryMapper;
    private final AccountRepository accountRepository;

    public AnthropometryService(AnthropometryRepository anthropometryRepository,
                                AnthropometryHistoryRepository anthropometryHistoryRepository,
                                AnthropometryMapper anthropometryMapper,
                                AccountRepository accountRepository) {
        this.anthropometryRepository = anthropometryRepository;
        this.anthropometryHistoryRepository = anthropometryHistoryRepository;
        this.anthropometryMapper = anthropometryMapper;
        this.accountRepository = accountRepository;
    }

    public AnthropometryResponseDto getCurrentAnthropometryByOwner(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Anthropometry currentAnthropometry = account.getCurrentAnthropometry();
        return anthropometryMapper.toAnthropometryResponseDto(currentAnthropometry);
    }

    public List<AnthropometryResponseDto> getHistoryOfAnthropometry (Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        List<AnthropometryHistory> anthropometryHistorieList = anthropometryHistoryRepository.findByOwner(account);
        if (anthropometryHistorieList.isEmpty()) {
            throw new NotFoundEntityException("Anthropometry history not found");
        }
        return anthropometryMapper.toAnthropometryResponseDtoList(anthropometryHistorieList);
    }

    public AnthropometryResponseDto createAnthropometry(AnthropometryCreateDto anthropometryCreateDto, Authentication authentication) {
        Account account =accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Anthropometry currentAnthropometry = account.getCurrentAnthropometry();
        Anthropometry newAnthropometry = anthropometryMapper.toAnthropometry(anthropometryCreateDto);
        newAnthropometry.setCreatedAt(LocalDateTime.now());
       if (currentAnthropometry == null) {
        account.setCurrentAnthropometry(newAnthropometry);
        accountRepository.save(account);
          return anthropometryMapper.toAnthropometryResponseDto(newAnthropometry);
       }
        AnthropometryHistory anthropometryInHistory = anthropometryMapper.toAnthropometryHistory(currentAnthropometry);
       anthropometryInHistory.setOwner(account);
       account.getAnthropometryHistory().add(anthropometryInHistory);
        account = accountRepository.save(account);
        return anthropometryMapper.toAnthropometryResponseDto(account.getCurrentAnthropometry());
    }

    public void deleteAnthropometryById(Long anthropometryId, Authentication authentication) {
        AnthropometryHistory anthropometryHistory = anthropometryHistoryRepository.findById(anthropometryId).orElseThrow(()->new NotFoundEntityException("Anthropometry not found"));
       validateAccess(anthropometryHistory, authentication);
            anthropometryHistoryRepository.delete(anthropometryHistory);
    }
    public AnthropometryResponseDto updateCurrentAnthropometry(AnthropometryUpdateDto updateDto, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Anthropometry anthropometryForUpdate = account.getCurrentAnthropometry();
        anthropometryForUpdate = anthropometryMapper.updateAnthropometry(updateDto, anthropometryForUpdate);
       anthropometryForUpdate = anthropometryRepository.save(anthropometryForUpdate);
        return anthropometryMapper.toAnthropometryResponseDto(anthropometryForUpdate);
    }

    public void validateAccess(AnthropometryHistory anthropometryHistory, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        if (!anthropometryHistory.getOwner().getId().equals(account.getId())) {
            throw new AccessDeniedException("Account is not the owner of this Anthropometry, Access denied.");
        }
    }
}
