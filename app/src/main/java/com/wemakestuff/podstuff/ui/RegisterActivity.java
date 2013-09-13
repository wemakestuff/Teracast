package com.wemakestuff.podstuff.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.InjectView;
import butterknife.OnClick;
import com.actionbarsherlock.view.MenuItem;
import com.github.frankiesardo.icepick.annotation.Icicle;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.*;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.R;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements Validator.ValidationListener {
	@Inject
	Bus mBus;
	Validator mValidator;
	@InjectView(R.id.iv_icon)
	ImageView icon;
	@Icicle
	String    firstNameText;
	@Required(order = 1, messageResId = R.string.message_required)
	@InjectView(R.id.et_first_name)
	EditText  firstName;
	@Icicle
	String    lastNameText;
	@Required(order = 2, messageResId = R.string.message_required)
	@InjectView(R.id.et_last_name)
	EditText  lastName;
	@Icicle
	String    emailAddressText;
	@Required(order = 3)
	@Email(order = 4, messageResId = R.string.message_valid_email)
	@InjectView(R.id.et_email_address)
	EditText  emailAddress;
	@Icicle
	String    passwordText;
	@TextRule(order = 5, minLength = 6, messageResId = R.string.message_short_password)
	@Password(order = 6, messageResId = R.string.message_required)
	@InjectView(R.id.et_password)
	EditText  password;
	@Icicle
	boolean termsChecked = false;
	@Checked(order = 7, messageResId = R.string.message_accept_terms)
	@InjectView(R.id.cb_terms)
	CheckBox terms;
	@InjectView(R.id.b_register)
	Button   register;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@OnClick(R.id.b_register)
	void register() {
		mValidator.validate();
	}

	@Override
	public void preValidation() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onSuccess() {
		Toast.makeText(this, "Successfully Validated!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onFailure(final View failedView, final Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();

		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(message);
		} else {
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onValidationCancelled() {
		Toast.makeText(this, "Validation Cancelled", Toast.LENGTH_LONG).show();
	}

	private void clearErrors() {
		firstName.setError(null);
		lastName.setError(null);
		emailAddress.setError(null);
		password.setError(null);
	}
}