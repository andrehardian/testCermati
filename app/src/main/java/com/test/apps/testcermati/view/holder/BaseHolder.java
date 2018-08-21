package com.test.apps.testcermati.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseHolder<T> extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }

    public void setData(T data) {
        itemView.setTag(data);
    }

}
