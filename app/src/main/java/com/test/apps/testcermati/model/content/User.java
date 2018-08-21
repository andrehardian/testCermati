package com.test.apps.testcermati.model.content;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User {
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String login;
    private int id;
}
