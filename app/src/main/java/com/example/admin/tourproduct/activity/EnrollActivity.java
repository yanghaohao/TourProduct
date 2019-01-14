package com.example.admin.tourproduct.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.db.DBProcess;
import com.example.admin.tourproduct.entry.Result;
import com.example.admin.tourproduct.entry.User;
import com.example.admin.tourproduct.interfaces.EnrollView;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.activity.BaseActivity;

public class EnrollActivity extends BaseActivity implements View.OnClickListener {


    private DBProcess dbProcess;
    private Result result;
    private AutoCompleteTextView mEmail,mUsername;
    private EditText mPassword;
    private LinearLayout mLlLogin;
    private ShapeLoadingDialog mShapeLoadingDialog;
    private String email;
    private String password,username;
    private User users;
    private SharedUtils sharedUtils;
    private HomeFragmentPresenter mHomeFragmentPresenter;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 5){
                showShortToast("注册成功");

                sharedUtils.saveString(SharedUtils.USERID,email);
                sharedUtils.saveString(SharedUtils.PASSWORD,password);

                findViewById(R.id.til_username).setVisibility(View.VISIBLE);

//                sharedUtils.saveBoolean(Constant.IS_LOGIN,true);
//                mShapeLoadingDialog.cancel();
//                HomeActivity.user = new User(new User.EntityBean(1));

                JSONObject object2 = new JSONObject();
                try {
                    object2.put("LoginName", URLEncoder.encode(email));
                    object2.put("PassWord", URLEncoder.encode(password));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String data = object2.toString();

                // Simulate network access.
                mHomeFragmentPresenter.loadingData(Constant.LOGIN,data,Constant.POST,false);

            }else if (msg.what == -1){
                showSnackBar(mLlLogin,result.getMessage(),2000);
            }
            if (msg.what == 10) {
                sharedUtils.saveString(SharedUtils.USERNAME,email);
                sharedUtils.saveString(SharedUtils.PASSWORD,password);
                sharedUtils.saveBoolean(Constant.IS_LOGIN,true);
                mShapeLoadingDialog.cancel();
                HomeActivity.user = users;
                openActivity(EnrollActivity.this, HomeActivity.class, 0, 0, true);
            }
            return false;
        }
    });

    private LoginView loginView = new LoginView() {
        @Override
        public void loginFail() {
            sharedUtils.saveBoolean(Constant.IS_LOGIN,false);
        }

        @Override
        public void showlogin(String user) {
            users = (User) new Gson().fromJson(user, new TypeToken<User>() {
            }.getType());
            if (users.getStatus() == 1)
                handler.sendEmptyMessage(Constant.ISLOGIN);
            else {
                handler.sendEmptyMessage(Constant.NotLOGIN);
            }
        }
    };

    EnrollView enrollView = new EnrollView() {
        @Override
        public void enrollFail() {

        }

        @Override
        public void showEnroll(String user) {
            result = (Result) new Gson().fromJson(user, new TypeToken<Result>() {}.getType());
            if (result.getStatus() == 1){
                handler.sendEmptyMessage(Constant.ENROLL);
            }else if (result.getStatus() == -1){
                handler.sendEmptyMessage(Constant.FAIL);
            }
        }
    };

    @Override
    protected void intiView() {
        hideActionBar();

        mShapeLoadingDialog = new ShapeLoadingDialog.Builder(this)
                .loadText(loadText)
                .build();

        sharedUtils = SharedUtils.getSharedUtils(EnrollActivity.this);

        findViewById(R.id.til_username).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_forget_password).setVisibility(View.GONE);
        Button emailSignInButton = findViewById(R.id.email_sign_in_button);
        emailSignInButton.setText(R.string.enroll);
        emailSignInButton.setOnClickListener(this);

        ImageView ivActionBar = findViewById(R.id.iv_action_bar);
        ivActionBar.setVisibility(View.VISIBLE);
        ivActionBar.setOnClickListener(this);

        TextView tvActionBar = findViewById(R.id.tv_action_bar);
        tvActionBar.setText(R.string.enroll);
        tvActionBar.setVisibility(View.VISIBLE);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mUsername = findViewById(R.id.username);

        mHomeFragmentPresenter = new HomeFragmentPresenter(enrollView,loginView);

        mLlLogin = findViewById(R.id.ll_login);

        dbProcess = new DBProcess(this);
    }

    private void check(AutoCompleteTextView mEmailView, EditText mPasswordView) {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        username = mUsername.getText().toString();

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
//        else if (!dbProcess.havePhone(email)){
//            mEmailView.setError(getString(R.string.repeat));
//            focusView = mEmailView;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            dbProcess.addUser(new User(email,password));
            mShapeLoadingDialog.show();
            String data = new Gson().toJson(new User.EntityBean(email,username,password));
            mHomeFragmentPresenter.loadingData(Constant.ENROLL,data,Constant.POST,false);
        }
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_login);
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
    public void onBackPressed() {
        super.onBackPressed();
        openActivity(LoginActivity.class,true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_action_bar:
                openActivity(LoginActivity.class,true);
                break;
            case R.id.email_sign_in_button:
                check(mEmail,mPassword);
                break;
        }
    }

}
