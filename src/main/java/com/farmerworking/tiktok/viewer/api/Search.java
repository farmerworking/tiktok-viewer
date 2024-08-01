package com.farmerworking.tiktok.viewer.api;

import com.farmerworking.tiktok.viewer.api.pojo.UserInfo;
import com.farmerworking.tiktok.viewer.api.pojo.UserSearchResult;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    static String URL = "https://tokapi-mobile-version.p.rapidapi.com/v1/search/user?keyword=%s&offset=%d&count=%d";

    public static List<UserInfo> user(String username, int offset, int count) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = String.format(URL, username, offset, count);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "57c4e9e06bmsh419574946bc7e60p14ccc6jsnce6635c2b635")
                .addHeader("x-rapidapi-host", "tokapi-mobile-version.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        UserSearchResult tmp = (new Gson()).fromJson(response.body().string(), UserSearchResult.class);
        return tmp.user_list.stream().map(i -> i.user_info).collect(Collectors.toList());
    }
}
