package com.farmerworking.tiktok.viewer.web.controller;

import com.farmerworking.tiktok.viewer.api.Search;
import com.farmerworking.tiktok.viewer.api.pojo.UserInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
            try {
                List<UserInfo> users = Search.user(username, 0, 30);
                model.addAttribute("users", users);
            } catch (IOException e) {
                LOGGER.error("search failed for: " + username, e);
            }
        }
        return "index";
    }
}
