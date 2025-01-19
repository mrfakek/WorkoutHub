package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.AccountResponseDto;
import by.tms.workouthub.dto.PublicAccountDto;
import by.tms.workouthub.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponseDto toAccountResponseDto(Account account);
    Account toAccount(AccountResponseDto accountResponseDto);
    PublicAccountDto toPublicAccountDto(Account account);
}
