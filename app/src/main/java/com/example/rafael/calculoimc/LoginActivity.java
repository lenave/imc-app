package com.example.rafael.calculoimc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoginActivity extends AppCompatActivity {

    View mProgressView;
    View mLoginFormView;
    EditText mTxtName;
    EditText mTxtAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressView = findViewById(R.id.pro_login);
                mLoginFormView = findViewById(R.id.login_form);

                mProgressView.setVisibility(View.VISIBLE);
                mLoginFormView.setVisibility(View.GONE);

                // Setting value in variables
                mTxtName = (EditText)findViewById(R.id.txt_name);
                mTxtAge = (EditText)findViewById(R.id.txt_age);

                String txtNameS = mTxtName.getText().toString();
                String txtAgeS = mTxtAge.getText().toString();

                // clean the errors
                mTxtName.setError(null);
                mTxtAge.setError(null);

                boolean cancel = false;

                // Form validation
                if (TextUtils.isEmpty(txtNameS)) {
                    mTxtName.setError("Preencha o seu nome");
                    cancel = true;
                }
                if (TextUtils.isEmpty(txtAgeS)) {
                    mTxtAge.setError("Preencha a sua idade");
                    cancel = true;
                }


                // submit
                if (!cancel) {
                    // Saving in sharedpreferences
                    setUserInfo(txtNameS, txtAgeS);

                    // go to page
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }


                mProgressView.setVisibility(View.GONE);
                mLoginFormView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setUserInfo(String userName, String userAge) {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("userName", userName);
        mEditor.putString("userAge", userAge);
        mEditor.apply();
    }

    private String getUserName() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String toReturn = mSharedPreferences.getString("userName", "Usuário");

        return toReturn;
    }

    private String getUserAge() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String toReturn = mSharedPreferences.getString("userAge", "Sem idade");

        return toReturn;
    }
}
