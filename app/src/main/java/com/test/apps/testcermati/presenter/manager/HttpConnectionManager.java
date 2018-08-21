package com.test.apps.testcermati.presenter.manager;

import android.app.ProgressDialog;
import android.content.Context;

import com.test.apps.testcermati.presenter.connection.ConUser;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import connection.rxconnection.connection.BaseServiceResponse;
import connection.rxconnection.connection.ConnectionManager;
import connection.rxconnection.connection.HttpRequest;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@EBean
public class HttpConnectionManager extends ConnectionManager {
    @RootContext
    protected Context context;

    @AfterInject
    protected void init() {
        setContext(context);
    }

    public void searchUserByName(String name, int page) {
        subscribe(new ConUser(context, name,page));
    }

    @Override
    protected void subscribe(HttpRequest httpRequest) {
        Observable.create(httpRequest).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.newThread()).
                subscribe((new BaseServiceResponse(getConnectionListener()))
                        .setCallBackSubscriber(this));

    }
}
