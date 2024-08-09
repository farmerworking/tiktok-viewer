package com.farmerworking.tiktok.viewer.web.controller;

import com.farmerworking.tiktok.viewer.api.Search;
import com.farmerworking.tiktok.viewer.api.Video;
import com.farmerworking.tiktok.viewer.api.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
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
            @RequestParam(name = "secUid", required = false, defaultValue = "") String secUid,
            Model model) {
        if (!StringUtils.isEmpty(secUid)) {
            queryVideoBySecUid(username, secUid, model);
        } else if (!StringUtils.isEmpty(username)) {
            searchByUsername(username, model);
        }
        return "index";
    }

    private void queryVideoBySecUid(String username, String secUid, Model model) {
        LOGGER.info("query video for: " + secUid + ", " + username);
        try {
            List<String> videos = new ArrayList<>();
            for(String s : Video.user(secUid, 0, 30)) {
                videos.add(Video.embed(s));
            }
            model.addAttribute("videos", videos);
        } catch (IOException e) {
            LOGGER.error("query video failed for: " + secUid, e);
        }
    }

    private void searchByUsername(String username, Model model) {
        LOGGER.info("search for: " + username);
        model.addAttribute("username", username);
        try {
            List<UserInfo> users = Search.user(username, 0, 30);
            model.addAttribute("users", users);
        } catch (IOException e) {
            LOGGER.error("search failed for: " + username, e);
        }
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
