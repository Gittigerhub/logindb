package com.muzik.logindb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"/", "/index"})
    public String indexForm() {
        return "index";
    }

    @GetMapping("/result")       // 모든 사용자 접근 가능
    public String resultForm() {
        return "result";
    }

    @GetMapping("/user")         // "USER" 권한자만 접근 가능
    public String userForm() {
        return "user";
    }

    @GetMapping("/admin")        // "ADMIN" 권한자만 접근 가능
    public String adminForm() {
        return "admin";
    }

}