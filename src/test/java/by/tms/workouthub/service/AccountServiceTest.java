package by.tms.workouthub.service;


import by.tms.workouthub.dto.AccountCreateDto;
import by.tms.workouthub.dto.AccountPublicDto;
import by.tms.workouthub.dto.AccountResponseDto;
import by.tms.workouthub.dto.AccountUpdateDto;
import by.tms.workouthub.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import static org.junit.jupiter.api.Assertions.*;
@Disabled
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private static AuthenticationManager authenticationManager;
    private static AccountCreateDto accountCreateDto;
    private static AccountUpdateDto accountUpdateDto;
    private static Authentication authentication;

    @BeforeAll
    static void setUpBeforeAll() {
        accountCreateDto = new AccountCreateDto();
        accountCreateDto.setUsername("testUser");
        accountCreateDto.setPassword("MyPassword123!");
        accountUpdateDto = new AccountUpdateDto();
        accountUpdateDto.setEmail("test@email.com");
        accountUpdateDto.setFirstName("testFirstName");
        accountUpdateDto.setLastName("testLastName");
    }

    @Test
    @Order(1)
    void create() {
        AccountResponseDto created = accountService.create(accountCreateDto);
        assertNotNull(created);
        assertEquals("testUser", created.getUsername());
        assertNotEquals("MyPassword123!", created.getPassword());
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountCreateDto.getUsername(), accountCreateDto.getPassword()));
    }

    @Test
    @Order(2)
    void getAccount() {
       AccountResponseDto accountResponseDto = accountService.getAccount(authentication);
        assertNotNull(accountResponseDto);
        assertEquals("MyUsername", accountResponseDto.getUsername());
    }

    @Test
    @Order(3)
    void getAccountByUsername() {
        AccountPublicDto account = accountService.getAccountByUsername("MyUsername");
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}