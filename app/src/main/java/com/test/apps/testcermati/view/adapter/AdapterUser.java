package com.test.apps.testcermati.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.apps.testcermati.R;
import com.test.apps.testcermati.model.content.User;
import com.test.apps.testcermati.view.holder.EmptyHolder;
import com.test.apps.testcermati.view.holder.UserHolder;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected ArrayList<User> listItem;

    protected final int EMPTY = 0;
    protected final int NON_EMPTY = 1;


    public AdapterUser(ArrayList<User> listItem) {
        this.listItem = listItem;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY:
                return createEmptyHolder(parent);
            default:
                return createHolder(parent, viewType);
        }

    }


    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new UserHolder(getlayoutInflater(parent.getContext())
                .inflate(R.layout.user, parent, false));
    }

    protected RecyclerView.ViewHolder createEmptyHolder(ViewGroup parent) {
        return new EmptyHolder(getlayoutInflater(parent.getContext())
                .inflate(R.layout.empty, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserHolder){
            ((UserHolder) holder).setData(listItem.get(position));
        }
    }


    @Override
    public int getItemCount() {
        if (listItem != null && listItem.size() > 0) {
            return listItem.size();
        } else {
            return 1;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (listItem.size() != 0) {
            return NON_EMPTY;
        } else {
            return EMPTY;
        }
    }

    protected LayoutInflater getlayoutInflater(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater;
    }


}
