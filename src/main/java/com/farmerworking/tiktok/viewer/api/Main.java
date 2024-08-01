package com.farmerworking.tiktok.viewer.api;

import com.google.gson.Gson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();

        System.out.println(Video.embed("https://www.tiktok.com/@realdonaldtrump/video/7375744699140721963"));
    }
}
