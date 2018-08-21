package com.test.apps.testcermati.presenter.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.test.apps.testcermati.model.content.User;
import com.test.apps.testcermati.model.response.ResponseError;
import com.test.apps.testcermati.model.response.ResponseUser;
import com.test.apps.testcermati.presenter.manager.DialogManager;
import com.test.apps.testcermati.presenter.manager.HttpConnectionManager;
import com.test.apps.testcermati.presenter.pres.MainPres;
import com.test.apps.testcermati.presenter.view.MainView;
import com.test.apps.testcermati.view.adapter.AdapterUser;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import connection.rxconnection.connection.ConnectionListener;
import connection.rxconnection.connection.HttpRequest;

@EBean
public class MainImpl implements MainPres, ConnectionListener {
    @RootContext
    protected Context context;
    @Bean
    protected HttpConnectionManager httpConnectionManager;
    @Bean
    protected DialogManager dialogManager;
    private ArrayList<User> users = new ArrayList<>();

    private MainView mainView;
    private int page = 1;
    private int hitPageDone = 0;
    private boolean lastPage;
    private String lastInput;

    @AfterInject
    protected void inject() {
        httpConnectionManager.setConnectionListener(this);
    }

    private void loadMore() {
        if (page == hitPageDone) {
            page += 1;
            checkBeforeHit();
        }
    }

    @Override
    public void onSuccessWithData(Object o) {
        hitPageDone += 1;
        if (o instanceof ResponseUser) {
            setData((ResponseUser) o);
        }
    }

    private void setData(ResponseUser data) {
        if (page == 1) {
            users.clear();
        }
        if (data.getItems() != null) {
            users.addAll(data.getItems());
            mainView.getRecyclerUser().getAdapter().notifyDataSetChanged();
        }

        if (data.getItems() == null || data.getItems().size() == 0) {
            lastPage = true;
        }
    }

    @Override
    public void onSuccessNull() {

    }

    @Override
    public void onMessageSuccess(String s) {

    }

    @Override
    public void onError(Object o, HttpRequest httpRequest) {
        if (o instanceof String) {
            String errorMessage = (String) o;
            try {
                ResponseError error = new Gson().fromJson(errorMessage, ResponseError.class);
                errorMessage = error.getMessage();
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            dialogManager.errorDialog(errorMessage);

        }

    }

    @Override
    public void unAuthorized(HttpRequest httpRequest) {

    }

    @Override
    public void init(MainView mainView) {
        this.mainView = mainView;
        RecyclerView recyclerView = mainView.getRecyclerUser();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new AdapterUser(users));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int count = layoutManager.getItemCount();
                int visibleCount = layoutManager.findLastVisibleItemPosition() + 1;
                if (count <= visibleCount && !lastPage) {
                    loadMore();
                }
            }
        });

    }

    @Override
    public void searchUser() {
        httpConnectionManager.searchUserByName(lastInput, page);
    }

    @Override
    public void afterTextChange(String input) {
        page = 1;
        lastInput = input;
        checkBeforeHit();
    }

    private void checkBeforeHit() {
        if (lastInput != null && lastInput.length() > 0) {
            searchUser();
        } else {
            users.clear();
            mainView.getRecyclerUser().getAdapter().notifyDataSetChanged();
        }

    }
}
