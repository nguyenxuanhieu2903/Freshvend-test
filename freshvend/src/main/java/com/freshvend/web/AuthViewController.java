package com.freshvend.web; // đổi cho đúng package của project

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    /** Trang đăng nhập. Khi có Spring Security: .formLogin(f -> f.loginPage("/login").permitAll()) */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
