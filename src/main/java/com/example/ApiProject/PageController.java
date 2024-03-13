package com.example.ApiProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String MonitorPage() {
        return "MonitorPage.html";
    }
}

