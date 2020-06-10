package springsecurity.white.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springsecurity.white.account.AccountService;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE - 10)
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    /*private final AccountService accountService;

    public SecurityConfig(AccountService accountService) {
        this.accountService = accountService;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*       .mvcMatchers("/", "/info*", "/account/**").permitAll()
                       .mvcMatchers("/admin").hasRole("ADMIN")
                */.anyRequest().permitAll();
        http.formLogin();

        http.httpBasic();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        http.httpBasic();
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }*/
}
