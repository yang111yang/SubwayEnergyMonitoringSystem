package com.jiaochuan.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/5.
 */
public class LoginActivity extends Activity {

    @BindView(R.id.btnClearUsername) Button btnClearUsername;
    @BindView(R.id.btnClearPwd) Button btnClearPwd;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.btnForgetPwd) Button btnForgetPwd;
    @BindView(R.id.etUsername) EditText etUsername;
    @BindView(R.id.etPwd) EditText etPwd;
    @BindView(R.id.cbRemPwd) CheckBox cbRemPwd;
    @BindView(R.id.cbAutoLogin) CheckBox cbAutoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);

        btnClearUsername.setVisibility(View.INVISIBLE);
        btnClearPwd.setVisibility(View.INVISIBLE);


    }
}
