package com.wemakestuff.teracast.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.squareup.otto.Bus;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.ui.base.BaseFragmentActivity;
import com.wemakestuff.teracast.ui.podcasts.PodcastsFragmentActivity;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

public class LoginActivity extends BaseFragmentActivity implements Validator.ValidationListener {
    @Inject
    Bus mBus;
    Validator mValidator;
    @InjectView(R.id.iv_icon)
    ImageView icon;
    @Icicle
    String emailAddressText;
    @Required(order = 1)
    @Email(order = 2, messageResId = R.string.message_valid_email)
    @InjectView(R.id.et_email_address)
    EditText emailAddress;
    @Icicle
    String passwordText;
    @Password(order = 3, messageResId = R.string.message_required)
    @InjectView(R.id.et_password)
    EditText password;
    @InjectView(R.id.tv_forgot_password)
    TextView forgotPassword;
    @Icicle
    boolean termsChecked = false;
    @Checked(order = 4, messageResId = R.string.message_accept_terms)
    @InjectView(R.id.cb_terms)
    CheckBox terms;
    @InjectView(R.id.b_sign_in)
    Button signIn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.b_sign_in)
    void signIn() {
        clearErrors();
        mValidator.validateAsync();
    }

    private void clearErrors() {
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
