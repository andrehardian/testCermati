package com.test.apps.testcermati.model.response;

import com.google.gson.annotations.SerializedName;
import com.test.apps.testcermati.model.content.User;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ResponseUser {
    @SerializedName("total_count")
    private int count;
    private ArrayList<User> items;
}
