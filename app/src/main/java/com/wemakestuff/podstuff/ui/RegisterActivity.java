package com.wemakestuff.podstuff.ui;

import android.os.Bundle;
import android.widget.*;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.frankiesardo.icepick.annotation.Icicle;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.R;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity {
	@Inject
	Bus       mBus;
	@InjectView(R.id.iv_icon)
	ImageView icon;
	@Icicle
	@Required(order = 1, messageResId = R.string.message_required)
	@InjectView(R.id.et_email_address)
	EditText  emailAddress;
	@Icicle
	@Required(order = 2, messageResId = R.string.message_required)
	@InjectView(R.id.et_password)
	EditText  password;
	@InjectView(R.id.tv_forgot_password)
	TextView  forgotPassword;
	@Icicle
	@Checked(order = 3, messageResId = R.string.message_accept_terms)
	@InjectView(R.id.cb_terms)
	CheckBox  terms;
	@InjectView(R.id.b_sign_in)
	Button    signIn;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	@OnClick(R.id.b_sign_in)
	void signIn() {

	}
}
