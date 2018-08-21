package com.test.apps.testcermati.presenter.utils;

public class URL {
    private static final String BASE_URL = "https://api.github.com/search/users";

    public static String searchUser(String key, int page) {
        return BASE_URL + "?q=" + key + "&page=" + page;
    }
}
