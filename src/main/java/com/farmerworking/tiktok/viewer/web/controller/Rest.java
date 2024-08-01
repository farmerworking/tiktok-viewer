package com.farmerworking.tiktok.viewer.web.controller;

import com.farmerworking.tiktok.viewer.api.pojo.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {
    @RequestMapping("/api/user")
    UserInfo user() {
        UserInfo info = new UserInfo();
        info.follower_count = 1;
        info.nickname = "trump";
        return info;
    }
}
