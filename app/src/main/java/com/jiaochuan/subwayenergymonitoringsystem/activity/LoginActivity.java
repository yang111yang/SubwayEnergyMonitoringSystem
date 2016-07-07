package com.jiaochuan.subwayenergymonitoringsystem.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.jiaochuan.subwayenergymonitoringsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/5.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.btn_back) Button btnBack;
    @BindView(R.id.btnClearUsername) Button btnClearUsername;
    @BindView(R.id.btnClearPwd) Button btnClearPwd;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.etUsername) EditText etUsername;
    @BindView(R.id.etPwd) EditText etPwd;
    @BindView(R.id.cbRemPwd) CheckBox cbRemPwd;
    @BindView(R.id.cbAutoLogin) CheckBox cbAutoLogin;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
        /**
         * 初始化SharedPreferences
         */
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        /**
         * 获取sp里面存储的数据
         */
        String saveUsername = sp.getString("username", "");
        String savePwd = sp.getString("pwd", "");
        etUsername.setText(saveUsername);
        etPwd.setText(savePwd);

        btnClearUsername.setVisibility(View.INVISIBLE);
        btnClearPwd.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnClearUsername.setOnClickListener(this);
        btnClearPwd.setOnClickListener(this);

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                displayClearUsername();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                displayClearPwd();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //监听记住密码框和自动登录多选框的状态
        cbRemPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbRemPwd.isChecked()){
                    sp.edit().putBoolean("ISCHECK",true).commit();
                    cbRemPwd.setChecked(true);
                } else {
                    sp.edit().putBoolean("ISCHECK",false).commit();
                }
            }
        });
        cbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbAutoLogin.isChecked()){
                    sp.edit().putBoolean("AUTO_ISCHECK",true).commit();
                    cbAutoLogin.setChecked(true);
                } else {
                    sp.edit().putBoolean("AUTO_ISCHECK",false).commit();
                }
            }
        });

        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK", true)){
            //设置默认是记住密码状态
            etUsername.setText(sp.getString("USER_NAME",""));
            etPwd.setText(sp.getString("PASSWORD",""));
            //判断自动登录多选框的状态
            if (sp.getBoolean("AUTO_ISCHECK", true)){
                //跳转界面
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }
        }




    }

    /**
     * 验证用户名和密码
     */
    public void loginMain(View v){
        String username = etUsername.getText().toString();
        String pwd = etPwd.getText().toString();
        if ("".equals(username)){
            Toast.makeText(LoginActivity.this,"用户名为空",Toast.LENGTH_SHORT).show();
        } else if ("".equals(pwd)){
            Toast.makeText(LoginActivity.this,"密码为空",Toast.LENGTH_SHORT).show();
        } else if ("".equals(username) && "".equals(pwd)){
            Toast.makeText(LoginActivity.this,"用户名为空",Toast.LENGTH_SHORT).show();
        } else if ("admin".equals(username) && "123456".equals(pwd)){
            //记住密码和自动登录为选中状态时才保存用户信息
            if (cbAutoLogin.isChecked()){
                //获取一个参数文件的编辑器
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", username);
                editor.putString("PASSWORD", pwd);
                editor.commit();
            }
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (!"admin".equals(username)){
            Toast.makeText(LoginActivity.this,"用户名不存在",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 返回主界面
     */
    public void backToMain(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    /**
     * 显示清空按钮
     */
    public void displayClearUsername(){
        btnClearUsername.setVisibility(View.VISIBLE);
    }
    public void displayClearPwd(){
        btnClearPwd.setVisibility(View.VISIBLE);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                backToMain(view);
                break;
            case R.id.btnLogin:
                loginMain(view);
                break;
            case R.id.btnClearUsername:
                etUsername.setText("");
                break;
            case R.id.btnClearPwd:
                etPwd.setText("");
                break;
        }
    }
}
