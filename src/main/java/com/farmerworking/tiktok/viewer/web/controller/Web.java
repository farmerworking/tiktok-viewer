package com.farmerworking.tiktok.viewer.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;

@Controller
public class Web {
    private static Logger LOGGER = LoggerFactory.getLogger(Web.class);

    @GetMapping("/example")
    public String example(
            @RequestParam(name = "username", required = false, defaultValue = "") String username,
            Model model) {
        model.addAttribute("message", "username is " + username);
        model.addAttribute("tasks", Arrays.asList("a", "b", "c", "d", "e", "f", "g"));

        return "example"; //view
    }

    @GetMapping("/")
    public String index(
            @RequestParam(name = "username", required = false, defaultValue = "") String username,
            Model model) {
        if (!StringUtils.isEmpty(username)) {
            LOGGER.info("search for: " + username);
            model.addAttribute("username", username);
        }
        return "index";
    }
}
