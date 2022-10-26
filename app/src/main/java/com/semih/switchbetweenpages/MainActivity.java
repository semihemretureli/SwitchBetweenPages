package com.semih.switchbetweenpages;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity<EmailPasswordActivity, user> extends AppCompatActivity {
    private EditText userName, password;
    private TextView txtConclusion;
    private String uName, uPassword, trueUName = "Semih", trueUPassword = "12345M";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.editTxtUsername);
        password = (EditText) findViewById(R.id.editTxtPassword);
        txtConclusion = (TextView) findViewById(R.id.txtVievConclusion);
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }

    public void btnLogin(View v) {
        uName = userName.getText().toString();
        uPassword = password.getText().toString();

        if (!TextUtils.isEmpty(uName)) {
            if (!TextUtils.isEmpty(uPassword)) {

                mAuth.signInWithEmailAndPassword(uName, uPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("userName", uName);
                            finish();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            } else {
                txtConclusion.setText("Password cannot be empty");
            }
        } else {
            txtConclusion.setText("Username cannot be empty");
        }
    }

}