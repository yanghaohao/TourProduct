package com.example.admin.tourproduct.activity;

import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.db.DBProcess;
import com.example.admin.tourproduct.entry.User;
import com.example.admin.tourproduct.interfaces.LoginView;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.SharedUtils;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.activity.BaseActivity;
import base.async.Async;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoaderCallbacks<Cursor>, OnClickListener {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;


    private LinearLayout mLlLogin;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private DBProcess dbProcess;
    private User users;
    private ShapeLoadingDialog shapeLoadingDialog;
    private HomeFragmentPresenter presenter;
    private SharedUtils sharedUtils;
    private String email;
    private String password;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            shapeLoadingDialog.cancel();
            if (users!=null){
                switch (msg.what){
                    case 10:
                        sharedUtils.saveBoolean(Constant.IS_LOGIN,true);
                        sharedUtils.saveString(SharedUtils.USERNAME,email);
                        sharedUtils.saveString(SharedUtils.PASSWORD,password);
                        HomeActivity.user = users;
                        finish();
                        break;
                    default:
                        showSnackBar(mLlLogin,users.getMessage(),2000);
                        break;
                }
            }
            return false;
        }
    });

    @Override
    protected void intiView() {

        hideActionBar();
        dbProcess = new DBProcess(this);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(this)
                .loadText(loadText)
                .build();

        ImageView ivActionBar = findViewById(R.id.iv_action_bar);
        ivActionBar.setVisibility(View.VISIBLE);
        ivActionBar.setOnClickListener(this);
        TextView tvActionBar = findViewById(R.id.tv_action_bar);
        tvActionBar.setVisibility(View.VISIBLE);
        tvActionBar.setText(R.string.login);
        TextView tvRegister = findViewById(R.id.tv_register);
        tvRegister.setVisibility(View.VISIBLE);
        tvRegister.setOnClickListener(this);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mLlLogin = findViewById(R.id.ll_login);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.email_sign_in_button).setOnClickListener(this);

        presenter = new HomeFragmentPresenter(loginView);

        sharedUtils = SharedUtils.getSharedUtils(LoginActivity.this);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_login);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            showSnackBar(mLlLogin,getString(R.string.error_invalid_password),2000);
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            showSnackBar(mLlLogin,getString(R.string.error_field_required),2000);
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            showSnackBar(mLlLogin,getString(R.string.error_invalid_email),2000);
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            shapeLoadingDialog.show();
            Async.run(new Runnable() {
                @Override
                public void run() {
                    JSONObject object2 = new JSONObject();
                    try {
                        object2.put("LoginName", URLEncoder.encode(email));
                        object2.put("PassWord", URLEncoder.encode(password));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String data = object2.toString();

                    // Simulate network access.
                    presenter.loadingData(Constant.LOGIN,data,Constant.POST,false);
                }
            });
        }
    }

    private boolean isEmailValid(String email) {

        Pattern p = Pattern.compile("^(1[0-9][0-9])\\d{8}$");

        Matcher m = p.matcher(email);

        return m.matches();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_action_bar:
                finish();
                break;
            case R.id.tv_register:
                openActivity(EnrollActivity.class,true);
                break;
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
        }
    }

    LoginView loginView = new LoginView() {
        @Override
        public void loginFail() {
            sharedUtils.saveBoolean(Constant.IS_LOGIN,false);
        }

        @Override
        public void showlogin(String user) {
            users = (User) new Gson().fromJson(user, new TypeToken<User>() {}.getType());
            if(users.getStatus()==1)
                handler.sendEmptyMessage(Constant.ISLOGIN);
            else {
                handler.sendEmptyMessage(Constant.NotLOGIN);
            }
        }
    };


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }
}

