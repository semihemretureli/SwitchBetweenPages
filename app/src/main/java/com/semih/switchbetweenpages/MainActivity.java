package com.semih.switchbetweenpages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private TextView txtConclusion;
    private String uName, uPassword, trueUName = "Semih", trueUPassword = "12345M";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.editTxtUsername);
        password = (EditText) findViewById(R.id.editTxtPassword);
        txtConclusion = (TextView) findViewById(R.id.txtVievConclusion);

    }

    public void btnLogin(View v) {
        uName = userName.getText().toString();
        uPassword = password.getText().toString();

        if (!TextUtils.isEmpty(uName)) {
            if (!TextUtils.isEmpty(uPassword)) {
                if (uName.equals(trueUName)) {
                    if (uPassword.equals(trueUPassword)) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("userName", uName);
                        finish();
                        startActivity(intent);
                    } else {
                        txtConclusion.setText("Wrong Password.");
                    }
                } else {
                    txtConclusion.setText("Username is wrong");
                }
            } else {
                txtConclusion.setText("Password cannot be empty");
            }
        } else {
            txtConclusion.setText("Username cannot be empty");
        }
    }
}