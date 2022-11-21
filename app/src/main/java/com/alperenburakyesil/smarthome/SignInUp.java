package com.alperenburakyesil.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInUp extends AppCompatActivity {

    Button signUp;
    Button signIn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);

        signIn();
        signUp();
    }

    public void signIn(){
        signIn = findViewById(R.id.signin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignInUp.this, Login.class);
                startActivity(intent);
                onPause();
            }
        });
    }

    public void signUp(){
        signUp = findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignInUp.this, Register.class);
                startActivity(intent);
            }
        });

    }
}