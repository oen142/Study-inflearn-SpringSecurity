package springsecurity.white.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springsecurity.white.account.Account;
import springsecurity.white.account.AccountService;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE - 10)
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll();
        http.formLogin()
                .usernameParameter("my-username")
                .passwordParameter("my-password")
                .loginPage("/sign-in");

        http.httpBasic();
        http.rememberMe()
                .rememberMeParameter("remember")
//                .userDetailsService(accountService)
//                .tokenValiditySeconds()//기본값 2week
//                .useSecureCookie() //https일때 사용하는 것이좋다.
        //       .alwaysRemember()//기본값 false 폼에서 안해도 기본적으로 된다.
        ;
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        http.httpBasic();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
