package com.freshvend.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller điều hướng giao diện mặc định cho trang chủ FreshVend
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // Trả về view customer/index.html trong frontend/templates/customer/index.html
        return "customer/index";
    }
}
