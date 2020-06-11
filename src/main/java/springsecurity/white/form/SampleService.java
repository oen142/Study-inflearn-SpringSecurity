package springsecurity.white.form;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import springsecurity.white.account.Account;
import springsecurity.white.account.AccountContext;
import springsecurity.white.common.SecurityLogger;

import javax.annotation.security.RolesAllowed;

@Service
public class SampleService {

    public void dashboard() {
        //한 쓰레드에 유지가 된다.  쓰레드가 달라진다면 다른 전략을 사용해야한다.
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();//사용자 내용
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//권한
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
        Object details = authentication.getDetails();
*/

        //PRINCIPAL 누구에 해당하는 정보 User객체가 리턴이 된다.

        //GRANTAUTHORITY 권한의 대한 증명.


//        Account account = AccountContext.getAccount();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("===============");
//        System.out.println("account = " + account);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        System.out.println("userDetails = " + userDetails);
    }

    @Async
    //특정 빈을 호출할때 비동기로 호출을 한다.
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("async Service is Called..");
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RolesAllowed("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    //@PostAuthorize("")
    public void test(){

    }
}
