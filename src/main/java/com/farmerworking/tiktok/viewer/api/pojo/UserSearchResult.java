package com.farmerworking.tiktok.viewer.api.pojo;

import java.util.List;

public class UserSearchResult {
    public int cursor;
    public int has_more;
    public List<SingleUserSearchResult> user_list;
}
