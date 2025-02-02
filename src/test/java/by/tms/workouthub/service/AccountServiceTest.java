package by.tms.workouthub.service;


import by.tms.workouthub.dto.AccountCreateDto;
import by.tms.workouthub.dto.AccountPublicDto;
import by.tms.workouthub.dto.AccountResponseDto;
import by.tms.workouthub.dto.AccountUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    private AccountCreateDto accountCreateDto;
    private AccountUpdateDto accountUpdateDto;

    @BeforeEach
    void setUp() {
        accountCreateDto = new AccountCreateDto();
        accountCreateDto.setUsername("testUser");
        accountCreateDto.setPassword("password");
        accountUpdateDto = new AccountUpdateDto();
        accountUpdateDto.setEmail("test@email.com");
        accountUpdateDto.setFirstName("testFirstName");
        accountUpdateDto.setLastName("testLastName");
    }

    @Test
    void create() {
        AccountResponseDto created = accountService.create(accountCreateDto);
        assertNotNull(created);
        assertEquals("testUser", created.getUsername());
    }

    @Test
    void getAccount() {
    }

    @Test
    void getAccountByUsername() {
        AccountPublicDto account = accountService.getAccountByUsername("testUser");
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}