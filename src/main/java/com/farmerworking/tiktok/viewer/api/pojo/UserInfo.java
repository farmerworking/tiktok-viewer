package com.farmerworking.tiktok.viewer.api.pojo;

public class UserInfo {
    public int follower_count;
    public String nickname;
    public String unique_id;
    public String sec_uid;
    public String uid;

    public Avatar300 avatar_300x300;

    public String getHumanReadableFollowerCount() {
        if (follower_count >= 100_0000) {
            return String.format("%.2fM", follower_count / 100_000.0);
        } else if (follower_count >= 1_000) {
            return String.format("%.2fK", follower_count / 1_000.0);
        } else {
            return String.valueOf(follower_count);
        }
    }
}
