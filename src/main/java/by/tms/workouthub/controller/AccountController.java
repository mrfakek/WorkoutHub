package by.tms.workouthub.controller;


import by.tms.workouthub.dto.AccountCreateDto;
import by.tms.workouthub.dto.AccountPublicDto;
import by.tms.workouthub.dto.AccountResponseDto;
import by.tms.workouthub.dto.AccountUpdateDto;
import by.tms.workouthub.service.AccountService;
import by.tms.workouthub.validation.annotation.ValidateThis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @Operation(summary = "Create a new account", responses = {
            @ApiResponse(description = "Account created successfully", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDto.class))),
            @ApiResponse(description = "Bad request", responseCode = "400")
    })
    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Account creation data", required = true)
                                                                @Valid @RequestBody AccountCreateDto accountCreateDto) {
        var saved = accountService.create(accountCreateDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get account by username", responses = {
            @ApiResponse(description = "Account found", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountPublicDto.class))),
            @ApiResponse(description = "Account not found", responseCode = "404")
    })
    @GetMapping("/{username}")
    public ResponseEntity<AccountPublicDto> getAccountByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(accountService.getAccountByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Get current account details", responses = {
            @ApiResponse(description = "Account details", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<AccountResponseDto> getAccount(Authentication authentication) {
        return new ResponseEntity<>(accountService.getAccount(authentication), HttpStatus.OK);
    }

    @Operation(summary = "Update current account", responses = {
            @ApiResponse(description = "Account updated successfully", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDto.class))),
            @ApiResponse(description = "Bad request", responseCode = "400")
    })
    @ValidateThis
    @PatchMapping
    public ResponseEntity<AccountResponseDto> updateAccount(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Account update data", required = true)
                                                                @Valid @RequestBody AccountUpdateDto accountUpdateDto, Authentication authentication) {
        var updated = accountService.update(accountUpdateDto, authentication);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @Operation(summary = "Delete current account", responses = {
            @ApiResponse(description = "Account deleted", responseCode = "204"),
            @ApiResponse(description = "Unauthorized", responseCode = "401")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(Authentication authentication) {
        accountService.delete(authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}