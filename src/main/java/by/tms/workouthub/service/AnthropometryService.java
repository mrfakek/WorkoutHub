package by.tms.workouthub.service;

import by.tms.workouthub.dto.AnthropometryCreateDto;
import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.Anthropometry;
import by.tms.workouthub.entity.AnthropometryHistory;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.mappers.AnthropometryMapper;
import by.tms.workouthub.repository.AccountRepository;
import by.tms.workouthub.repository.AnthropometryHistoryRepository;
import by.tms.workouthub.repository.AnthropometryRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional
public class AnthropometryService {
    private final AnthropometryRepository anthropometryRepository;
    private final AnthropometryHistoryRepository anthropometryHistoryRepository;
    private final AnthropometryMapper anthropometryMapper;
    private final AccountRepository accountRepository;
    private final AnthropometryHistoryService anthropometryHistoryService;

    public AnthropometryService(AnthropometryRepository anthropometryRepository,
                                AnthropometryHistoryRepository anthropometryHistoryRepository,
                                AnthropometryMapper anthropometryMapper,
                                AccountRepository accountRepository,
                                AnthropometryHistoryService anthropometryHistoryService) {
        this.anthropometryRepository = anthropometryRepository;
        this.anthropometryHistoryRepository = anthropometryHistoryRepository;
        this.anthropometryMapper = anthropometryMapper;
        this.accountRepository = accountRepository;
        this.anthropometryHistoryService = anthropometryHistoryService;
    }

    public AnthropometryResponseDto getCurrentAnthropometry(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Anthropometry currentAnthropometry = account.getCurrentAnthropometry();
        if (currentAnthropometry == null) {
            throw new NotFoundEntityException("Current anthropometry not found");
        }
        return anthropometryMapper.toAnthropometryResponseDto(currentAnthropometry);
    }



    public AnthropometryResponseDto createAnthropometry(AnthropometryCreateDto anthropometryCreateDto, Authentication authentication) {
        Account account =accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Anthropometry currentAnthropometry = account.getCurrentAnthropometry();
        Anthropometry newAnthropometry = anthropometryMapper.toAnthropometry(anthropometryCreateDto);
        newAnthropometry.setCreatedAt(LocalDateTime.now());
       if (currentAnthropometry != null) {
        anthropometryHistoryService.addAnthropometryHistory(account);
       }
        account.setCurrentAnthropometry(newAnthropometry);
        accountRepository.save(account);
        return anthropometryMapper.toAnthropometryResponseDto(account.getCurrentAnthropometry());
    }

    public void deleteCurrentAnthropometry(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Optional<AnthropometryHistory> lastAnthropometryFromHistoryOpt = anthropometryHistoryRepository
                .findTopByOwnerOrderByCreatedAtDesc(account);
        if (lastAnthropometryFromHistoryOpt.isPresent()) {
            Anthropometry newCurrentAnthropometry = anthropometryMapper.toAnthropometryFromHistory(lastAnthropometryFromHistoryOpt.get());
            account.getAnthropometryHistory().remove(lastAnthropometryFromHistoryOpt.get());
            account.setCurrentAnthropometry(newCurrentAnthropometry);
            accountRepository.save(account);
        }
        else {
            account.setCurrentAnthropometry(null);
            accountRepository.save(account);
        }
    }

    public AnthropometryResponseDto updateCurrentAnthropometry(AnthropometryUpdateDto updateDto, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        Anthropometry anthropometryForUpdate = account.getCurrentAnthropometry();
        anthropometryForUpdate = anthropometryMapper.updateAnthropometry(updateDto, anthropometryForUpdate);
       anthropometryForUpdate = anthropometryRepository.save(anthropometryForUpdate);
        return anthropometryMapper.toAnthropometryResponseDto(anthropometryForUpdate);
    }

}
