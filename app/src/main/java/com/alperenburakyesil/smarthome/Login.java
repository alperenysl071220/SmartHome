package com.alperenburakyesil.smarthome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alperenburakyesil.smarthome.Data.User_Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button login;
    EditText username, password;

    FirebaseDatabase database;
    DatabaseReference reference;

    private User_Data user;

    String Password_data, Username_data;

    ArrayList<User_Data> user_data_array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");

        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        download_Data();
        success_login();

    }

    public User_Data getUser() {
        return user;
    }

    private void success_login(){

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (User_Data user_data : user_data_array){
                    if (user_data.getUsername().equals(String.valueOf(username.getText()))){

                        user = user_data;
                        Password_data = user_data.getPassword();
                        Username_data = user_data.getUsername();
                    }
                }



                if (String.valueOf(password.getText()).equals(Password_data)  && String.valueOf(username.getText()).equals(Username_data)){
                    AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                    alertDialog.setTitle("Login Status");
                    alertDialog.setMessage("Successful");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Login.this, Devices.class));
                                    finish();
                                }
                            });
                    alertDialog.show();
                }

                else if (String.valueOf(password.getText()).equals(Password_data)  && !String.valueOf(username.getText()).equals(Username_data)){
                    AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                    alertDialog.setTitle("Login Status");
                    alertDialog.setMessage("Unsuccessful\n      Please check username");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                else if (!String.valueOf(password.getText()).equals(Password_data)  && String.valueOf(username.getText()).equals(Username_data)){
                    AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                    alertDialog.setTitle("Login Status");
                    alertDialog.setMessage("Unsuccessful\n      Please check password");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                    alertDialog.setTitle("Login Status");
                    alertDialog.setMessage("Unsuccessful\n      Please check username and password");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }
        });

    }

    private void download_Data(){
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        user_data_array.add(new User_Data(String.valueOf(snapshot1.child("username").getValue()), String.valueOf(snapshot1.child("firstname").getValue()),
                                String.valueOf(snapshot1.child("lastname").getValue()), String.valueOf(snapshot1.child("email").getValue()),String.valueOf(snapshot1.child("phone_number").getValue()),
                                String.valueOf(snapshot1.child("password").getValue())));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }
}