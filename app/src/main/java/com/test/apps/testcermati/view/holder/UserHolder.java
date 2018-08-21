package com.test.apps.testcermati.view.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.apps.testcermati.R;
import com.test.apps.testcermati.model.content.User;

public class UserHolder extends BaseHolder<User> {
    private ImageView avatar;
    private TextView name;

    public UserHolder(View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);
    }

    @Override
    public void setData(User data) {
        super.setData(data);
        if (data != null) {
            name.setText(data.getLogin());
            Glide.with(itemView.getContext()).load(data.getAvatarUrl()).
                    diskCacheStrategy(DiskCacheStrategy.ALL).override(24,24)
                    .into(avatar);

        }
    }
}
