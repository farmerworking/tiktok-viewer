package com.farmerworking.tiktok.viewer.api;

import com.farmerworking.tiktok.viewer.api.pojo.EmbedResult;
import com.farmerworking.tiktok.viewer.api.pojo.SingleUserVideo;
import com.farmerworking.tiktok.viewer.api.pojo.UserVideoResult;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Video {
    static String URL = "https://tokapi-mobile-version.p.rapidapi.com/v1/post/user/posts?" +
            "sec_user_id=%s&offset=%d&count=%d&region=GB&with_pinned_posts=1";

    public static List<String> user(String secUserId, int offset, int count) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = String.format(URL, secUserId, offset, count);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "57c4e9e06bmsh419574946bc7e60p14ccc6jsnce6635c2b635")
                .addHeader("x-rapidapi-host", "tokapi-mobile-version.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        UserVideoResult tmp = (new Gson()).fromJson(response.body().string(), UserVideoResult.class);

        List<String> result = new ArrayList<>();
        for(SingleUserVideo video : tmp.aweme_list) {
            int index = video.share_url.indexOf('?');
            result.add(video.share_url.substring(0, index));
        }
        return result;
    }

    static String EMBED_URL = "https://www.tiktok.com/oembed?url=%s";

    public static String embed(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(EMBED_URL, url))
                .get()
                .build();

        Response response = client.newCall(request).execute();
        EmbedResult tmp = (new Gson()).fromJson(response.body().string(), EmbedResult.class);
        return tmp.html;
    }
}
