package com.test.apps.testcermati.presenter.manager;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class DialogManager {
    @RootContext
    protected Activity activity;
    private AlertDialog alert;

    public void errorDialog(String message) {
        if (alert == null || (alert != null && !alert.isShowing())) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                        }
                    });
            alert = builder.create();
            alert.show();
        }
    }

}
