package springsecurity.white.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInOutController {


    @GetMapping("/sign-in")
    public String loginForm(){
        return "sign-in";
    }


    @GetMapping("/sign-out")
    public String logoutForm(){
        return "sign-out";
    }
}
