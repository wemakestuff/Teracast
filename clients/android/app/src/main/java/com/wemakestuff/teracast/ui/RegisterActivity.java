package com.wemakestuff.teracast.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.github.frankiesardo.icepick.annotation.Icicle;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.squareup.otto.Bus;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.ui.base.BaseFragmentActivity;
import com.wemakestuff.teracast.ui.podcasts.PodcastsFragmentActivity;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

public class RegisterActivity extends BaseFragmentActivity implements Validator.ValidationListener {
    @Inject
    Bus mBus;
    Validator mValidator;
    @InjectView(R.id.iv_icon)
    ImageView icon;
    @Icicle
    String firstNameText;
    @Required(order = 1, messageResId = R.string.message_required)
    @InjectView(R.id.et_first_name)
    EditText firstName;
    @Icicle
    String lastNameText;
    @Required(order = 2, messageResId = R.string.message_required)
    @InjectView(R.id.et_last_name)
    EditText lastName;
    @Icicle
    String emailAddressText;
    @Required(order = 3)
    @Email(order = 4, messageResId = R.string.message_valid_email)
    @InjectView(R.id.et_email_address)
    EditText emailAddress;
    @Icicle
    String passwordText;
    @TextRule(order = 5, minLength = 6, messageResId = R.string.message_short_password)
    @Password(order = 6, messageResId = R.string.message_required)
    @InjectView(R.id.et_password)
    EditText password;
    @Icicle
    boolean termsChecked = false;
    @Checked(order = 7, messageResId = R.string.message_accept_terms)
    @InjectView(R.id.cb_terms)
    CheckBox terms;
    @InjectView(R.id.b_register)
    Button register;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.register, menu);
        return true;
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
        clearErrors();
        mValidator.validateAsync();
    }

    private void clearErrors() {
        firstName.setError(null);
        lastName.setError(null);
        emailAddress.setError(null);
        password.setError(null);
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Successfully Validated!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, PodcastsFragmentActivity.class);
        startActivity(intent);
    }

    @Override
    public void onValidationFailed(View view, Rule<?> rule) {
        String message = rule.getFailureMessage();

        if (view instanceof EditText) {
            view.requestFocus();
            ((EditText) view).setError(message);
        } else if (view instanceof CheckBox) {
            SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setMessage(message).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
