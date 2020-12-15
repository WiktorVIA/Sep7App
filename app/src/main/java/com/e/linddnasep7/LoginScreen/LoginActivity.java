package com.e.linddnasep7.LoginScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.e.linddnasep7.MainScreen.MainActivity;
import com.e.linddnasep7.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText rEmail, rPassword;
        FirebaseAuth fAuth;
        //TextView rCreateBtn;
        Button rLoginBtn;
        ProgressBar progressBar1;

        rEmail = findViewById(R.id.email);
        rPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        //rCreateBtn = findViewById(R.id.createText);
        rLoginBtn = findViewById(R.id.loginBtn);
        progressBar1 = findViewById(R.id.progressBar);

        rLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = rEmail.getText().toString().trim();
                final String password = rPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    rEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    rPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    rPassword.setError("Password must be at least 6 characters long");
                    return;
                }

                //sets the progress bar to visible as the user goes through the process
                progressBar1.setVisibility(View.VISIBLE);

                // Authenticate the user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        } else {
                            Toast.makeText(LoginActivity.this, "An Error as occurred " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar1.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }

}