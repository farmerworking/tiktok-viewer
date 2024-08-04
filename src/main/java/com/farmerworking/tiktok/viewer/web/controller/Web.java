package com.farmerworking.tiktok.viewer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class Web {
    @GetMapping("/example")
    public String example(
            @RequestParam(name = "username", required = false, defaultValue = "") String username,
            Model model) {
        model.addAttribute("message", "username is " + username);
        model.addAttribute("tasks", Arrays.asList("a", "b", "c", "d", "e", "f", "g"));

        return "example"; //view
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
