package springsecurity.white.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springsecurity.white.account.Account;
import springsecurity.white.account.AccountContext;
import springsecurity.white.account.AccountRepository;
import springsecurity.white.common.SecurityLogger;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    private final SampleService sampleService;
    private final AccountRepository accountRepository;

    public SampleController(SampleService sampleService ,AccountRepository accountRepository) {
        this.sampleService = sampleService;
        this.accountRepository  = accountRepository;
    }

    @GetMapping("/")
    public String index(Model model , Principal principal){
        if(principal == null){
            model.addAttribute("message" , "Hello Spring Security");
        }else{
            model.addAttribute("message" , "Hello ," + principal.getName());
        }
        return "index";
    }
    @GetMapping("/info")
    public String info(Model model){
        model.addAttribute("message" , "Info");
        return "info";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model , Principal principal){
        model.addAttribute("message" , "Hello"+ principal.getName());
        //AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();
        return "dashboard";
    }
    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("message" , "Hello Admin"+principal.getName());
        return "admin";
    }
    @GetMapping("/aysnc-handler")
    @ResponseBody
    public Callable<String> asyncHandler(){
        /*
        * 톰캣이 할당해준 Nio 스레드
        * */
        SecurityLogger.log("MVC");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
        /*
        *
        * */
    }
    @GetMapping("/aysnc-service")
    @ResponseBody
    public Callable<String> asyncService(){
        SecurityLogger.log("MVC before");
        sampleService.asyncService();
        SecurityLogger.log("MVC after");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Service";
        };
    }
}
