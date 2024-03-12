package com.example.ApiProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class PageController {

    @GetMapping("/Monitor")
    @ResponseBody
    public String Gui() {
        return "MonitorPage";
    }
}
