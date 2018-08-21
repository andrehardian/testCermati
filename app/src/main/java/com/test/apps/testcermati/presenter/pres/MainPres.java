package com.test.apps.testcermati.presenter.pres;

import com.test.apps.testcermati.presenter.view.MainView;

public interface MainPres {
    void init(MainView mainView);
    void searchUser();
    void afterTextChange(String input);
}
