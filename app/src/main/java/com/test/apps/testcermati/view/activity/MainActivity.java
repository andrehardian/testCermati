package com.test.apps.testcermati.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;

import com.test.apps.testcermati.R;
import com.test.apps.testcermati.presenter.impl.MainImpl;
import com.test.apps.testcermati.presenter.view.MainView;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainView {
    @ViewById
    protected EditText search;
    @ViewById(R.id.list_user)
    protected RecyclerView listUser;

    @Bean
    protected MainImpl main;

    @AfterViews
    protected void init() {
        main.init(this);
    }

    @UiThread(delay = 1000)
    @AfterTextChange
    protected void search(Editable input) {
        main.afterTextChange(input.toString());
    }

    @Override
    public RecyclerView getRecyclerUser() {
        return listUser;
    }

}
