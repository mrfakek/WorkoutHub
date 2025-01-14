package by.tms.workouthub.controller;


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
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        var saved = accountService.create(account);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<PublicAccountDto> getAccount(@PathVariable("username") String username) {
        return new ResponseEntity<>(accountService.getAccountByUsername(username), HttpStatus.OK);
    }

    @PatchMapping("/{username}/update")
    public ResponseEntity<Account> updateAccount(
            @PathVariable("username") String username,
            Authentication authentication,
            @RequestBody AccountUpdateDto accountUpdateDto
    ) {
        var updated = accountService.update(accountUpdateDto, username, authentication);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{username}/delete")
    public ResponseEntity<?> deleteAccount(@PathVariable("username") String username, Authentication authentication) {
      if (accountService.delete(username, authentication)){
          return new ResponseEntity<>("true",HttpStatus.OK);
      }
      return new ResponseEntity<>("false",HttpStatus.NOT_FOUND);
    }
}