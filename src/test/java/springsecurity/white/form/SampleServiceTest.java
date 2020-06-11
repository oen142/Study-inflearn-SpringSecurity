package springsecurity.white.form;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import springsecurity.white.account.Account;
import springsecurity.white.account.AccountService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    @WithMockUser
    public void dashboard() {

        Account account = new Account();
        account.setRole("USER");
        account.setUsername("KIM");
        account.setPassword("123");
        accountService.createNew(account);
        UserDetails userDetails = accountService.loadUserByUsername("KIM");


        //userDetails 비밀번호 같이 인증
        //credentials 사용자가 입력한 비밀번호
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "123");
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        sampleService.test();
    }
}