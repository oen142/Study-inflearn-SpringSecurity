package springsecurity.white.account;


import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@WithMockUser(username = "kim" ,roles = {"USER"})
public @interface WithUser {
}
