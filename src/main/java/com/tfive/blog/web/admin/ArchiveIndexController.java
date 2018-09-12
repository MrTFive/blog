package com.tfive.blog.web.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveIndexController {

    @GetMapping("/archives")
    public String archives(Model model){




        return "archives";
    }

}
