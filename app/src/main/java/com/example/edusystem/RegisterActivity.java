package com.example.edusystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();

    EditText userNameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordREEditText;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bundle bundle = getIntent().getExtras();
        //bundle.getInt("SECRET_KEY");
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key != 99) {
            finish();
        }

        userNameEditText = findViewById(R.id.userNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordREEditText = findViewById(R.id.passwordREEditText);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");

        userNameEditText.setText(username);
        passwordEditText.setText(password);
        passwordREEditText.setText(password);

        Log.i(LOG_TAG, "onCreate");
    }

    public void register(View view) {
        String username = userNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordRE = passwordREEditText.getText().toString();

        Log.i(LOG_TAG, "Regisztrált: " + username + ", jelszó: " + password + ", email: " + email);
    }

    public void cancel(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        Log.i(LOG_TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(LOG_TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(LOG_TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i(LOG_TAG, "onRestart");
        super.onRestart();
    }
}