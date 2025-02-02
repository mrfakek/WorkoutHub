package by.tms.workouthub.service;

import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.AnthropometryHistory;
import by.tms.workouthub.exceptions.AccessDeniedException;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.mappers.AnthropometryHistoryMapper;
import by.tms.workouthub.repository.AccountRepository;
import by.tms.workouthub.repository.AnthropometryHistoryRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnthropometryHistoryService {
    private final AnthropometryHistoryRepository anthropometryHistoryRepository;
    private final AnthropometryHistoryMapper anthropometryHistoryMapper;
    private final AccountRepository accountRepository;

    public AnthropometryHistoryService(AnthropometryHistoryRepository anthropometryHistoryRepository,
                                       AnthropometryHistoryMapper anthropometryHistoryMapper,
                                       AccountRepository accountRepository) {
        this.anthropometryHistoryRepository = anthropometryHistoryRepository;
        this.anthropometryHistoryMapper = anthropometryHistoryMapper;
        this.accountRepository = accountRepository;
    }
    public void addAnthropometryHistory(Account account) {
        AnthropometryHistory anthropometryHistory = anthropometryHistoryMapper.toAnthropometryHistory(account.getCurrentAnthropometry());
        anthropometryHistory.setOwner(account);
        account.getAnthropometryHistory().add(anthropometryHistory);
        anthropometryHistoryRepository.save(anthropometryHistory);
    }

    public List<AnthropometryResponseDto> getHistoryOfAnthropometry (Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        List<AnthropometryHistory> anthropometryHistorieList = anthropometryHistoryRepository.findByOwner(account);
        if (anthropometryHistorieList.isEmpty()) {
            throw new NotFoundEntityException("Anthropometry history not found");
        }
        return anthropometryHistoryMapper.toAnthropometryResponseDtoList(anthropometryHistorieList);
    }

    public AnthropometryResponseDto getAnthropometryHistoryById (Long id, Authentication authentication) {
        AnthropometryHistory anthropometryHistory = anthropometryHistoryRepository.findById(id).orElseThrow(()->new NotFoundEntityException("Anthropometry not found"));
        validateAccess(anthropometryHistory, authentication);
        return anthropometryHistoryMapper.toAnthropometryResponseDto(anthropometryHistory);
    }

    public AnthropometryResponseDto updateAnthropometryHistoryById(Long id, AnthropometryUpdateDto updateDto, Authentication authentication) {
        AnthropometryHistory anthropometryHistory = anthropometryHistoryRepository.findById(id).orElseThrow(()->new NotFoundEntityException("Anthropometry not found"));
        validateAccess(anthropometryHistory, authentication);
        anthropometryHistory = anthropometryHistoryMapper.updateAnthropometryHistory(updateDto, anthropometryHistory);
        anthropometryHistory = anthropometryHistoryRepository.save(anthropometryHistory);
        return anthropometryHistoryMapper.toAnthropometryResponseDto(anthropometryHistory);
    }

    public void deleteAnthropometryHistoryById(Long anthropometryId, Authentication authentication) {
        AnthropometryHistory anthropometryHistory = anthropometryHistoryRepository.findById(anthropometryId).orElseThrow(()->new NotFoundEntityException("Anthropometry not found"));
        validateAccess(anthropometryHistory, authentication);
        anthropometryHistoryRepository.delete(anthropometryHistory);
    }

    public void validateAccess(AnthropometryHistory anthropometryHistory, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        if (!anthropometryHistory.getOwner().getId().equals(account.getId())) {
            throw new AccessDeniedException("Account is not the owner of this Anthropometry, Access denied.");
        }
    }
}
