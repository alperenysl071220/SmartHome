package com.alperenburakyesil.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alperenburakyesil.smarthome.Data.User_Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    String account_username;

    TextView username;
    TextView name;
    TextView surname;
    TextView email;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        account_username = Login.sent_username;

        username = findViewById(R.id.account_username);
        name = findViewById(R.id.account_name);
        surname = findViewById(R.id.account_surname);
        email = findViewById(R.id.account_email);
        phone = findViewById(R.id.account_phone);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User").child(account_username);

        download_Data();
    }

    private void download_Data() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                username.setText(String.valueOf(snapshot.child("username").getValue()));
                name.setText(String.valueOf(snapshot.child("firstname").getValue()));
                surname.setText(String.valueOf(snapshot.child("lastname").getValue()));
                email.setText(String.valueOf(snapshot.child("email").getValue()));
                phone.setText(String.valueOf(snapshot.child("phone_number").getValue()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}