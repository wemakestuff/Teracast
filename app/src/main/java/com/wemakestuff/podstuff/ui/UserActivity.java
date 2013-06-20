package com.wemakestuff.podstuff.ui;

import static com.wemakestuff.podstuff.core.Constants.Extra.USER;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wemakestuff.podstuff.BootstrapApplication;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.core.AvatarLoader;
import com.wemakestuff.podstuff.core.User;
import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;

public class UserActivity extends BootstrapActivity {

    @InjectView(R.id.iv_avatar) protected ImageView avatar;
    @InjectView(R.id.tv_name) protected TextView name;

    @Inject protected AvatarLoader avatarLoader;

    protected User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_view);

        if(getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().getSerializable(USER);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        avatarLoader.bind(avatar, user);
        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));

    }


}
