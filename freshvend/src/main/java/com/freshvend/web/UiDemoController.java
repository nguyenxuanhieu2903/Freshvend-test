package com.freshvend.web; // đổi cho đúng package của project

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Chỉ để xem layout. XÓA file này và thư mục templates/demo khi FE1/FE2 có trang thật. */
@Controller
public class UiDemoController {

    @GetMapping("/ui-demo/customer")
    public String customer() {
        return "demo/customer";
    }

    @GetMapping("/ui-demo/admin")
    public String admin() {
        return "demo/admin";
    }
}
