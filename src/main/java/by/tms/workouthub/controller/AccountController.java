package by.tms.workouthub.controller;


import by.tms.workouthub.dto.AccountCreateDto;
import by.tms.workouthub.dto.AccountResponseDto;
import by.tms.workouthub.dto.AccountUpdateDto;
import by.tms.workouthub.dto.PublicAccountDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        var saved = accountService.create(accountCreateDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<PublicAccountDto> getAccountByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(accountService.getAccountByUsername(username), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<AccountResponseDto> getAccount(Authentication authentication) {
        return new ResponseEntity<>(accountService.getAccount(authentication), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<AccountResponseDto> updateAccount(Authentication authentication, @RequestBody AccountUpdateDto accountUpdateDto) {
        var updated = accountService.update(accountUpdateDto, authentication);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(Authentication authentication) {
      accountService.delete(authentication);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}