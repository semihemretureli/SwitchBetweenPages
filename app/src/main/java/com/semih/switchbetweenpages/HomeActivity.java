package com.semih.switchbetweenpages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {
    private TextView txtUser;
    private String incomingUName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtUser = (TextView) findViewById(R.id.txtViewUser);

        Intent incomingIntent = getIntent();
        incomingUName = incomingIntent.getStringExtra("userName");
        txtUser.setText("Welcome : " + incomingUName);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(HomeActivity.this,MainActivity.class);
        finish();
        startActivity(backIntent);
    }
}