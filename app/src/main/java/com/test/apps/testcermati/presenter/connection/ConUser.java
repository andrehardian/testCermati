package com.test.apps.testcermati.presenter.connection;

import android.content.Context;

import com.test.apps.testcermati.model.response.ResponseUser;
import com.test.apps.testcermati.presenter.utils.URL;

import connection.rxconnection.connection.HttpMethod;
import connection.rxconnection.connection.HttpRequest;

public class ConUser extends HttpRequest<Object, ResponseUser> {
    public ConUser(Context context, String key, int page) {
        super(context, ResponseUser.class, URL.searchUser(key,page), HttpMethod.GET);
    }
}
