package springbootsecuritylearning;


import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping("/toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    @RequestMapping("/toError")
    public String toError() {
        return "redirect:error.html";
    }

    @ResponseBody
    @GetMapping(value = "/demo")
    public String demo() {
        return "demo.....";
    }

}
