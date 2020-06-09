package springsecurity.white.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
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
        http.csrf().disable();
        http.formLogin();
        http.httpBasic();
        //동적

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {

        //exculde
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        //정적
    }
    /*
     * 동적으로 가는건 web에서 하는건 추천하지 않는다.
     * */
    /*
     * 파비콘 요청 -
     * anonyAuthenticationFilter
     * filterSecurityInterceptor에서 최종적으로 검사를 한다.
     * */
}
