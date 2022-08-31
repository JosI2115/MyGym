package com.app.mygym.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mygym.Global;
import com.app.mygym.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private View parent_view;
    private TextInputEditText userEditText;
    private TextInputEditText passwordEditText;
    private TextView forgottenPasswordTextView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parent_view = findViewById(android.R.id.content);
        loginButton = findViewById(R.id.loginButton);
        userEditText = findViewById(R.id.userEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton.setOnClickListener(this);
        initComponents();
    }

    private void initComponents() {
        //Global.getInstance().setLoggedUser(new User());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private View.OnClickListener setOnForgottenPasswordClick() {
        return view -> {

        };
        /*return v -> {
            Intent myIntent = new Intent(this, ForgottenPasswordActivity.class);
            startActivity(myIntent);
        };*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        attemptLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        Global.getInstance().setCurrentActivity(this);
    }

    public static void attemptLogin(){
        Activity activity = Global.getInstance().getCurrentActivity();
        Intent myIntent = new Intent(activity, MainActivity.class);
        activity.startActivity(myIntent);
    }

}