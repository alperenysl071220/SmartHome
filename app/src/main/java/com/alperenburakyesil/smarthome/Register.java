package com.alperenburakyesil.smarthome;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alperenburakyesil.smarthome.Data.User_Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    Button register;

    EditText username;
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText phoneNumber;
    EditText password;

    FirebaseDatabase database;
    DatabaseReference reference;

    User_Data user_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");

        //Texts
        username = findViewById(R.id.Username);
        firstname = findViewById(R.id.Firstname);
        lastname = findViewById(R.id.Lastname);
        email = findViewById(R.id.Email);
        phoneNumber = findViewById(R.id.Phonenumber);
        password = findViewById(R.id.Password);

        //User_Data
        user_data = new User_Data();
        register();
    }

    private void register(){



        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = String.valueOf(username.getText());
                String first_name = String.valueOf(firstname.getText());
                String last_name = String.valueOf(lastname.getText());
                String e_mail = String.valueOf(email.getText());
                String phone_number = String.valueOf(phoneNumber.getText());
                String passwords = String.valueOf(password.getText());

                if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(first_name)|| TextUtils.isEmpty(last_name) || TextUtils.isEmpty(e_mail)
                || TextUtils.isEmpty(phone_number) || TextUtils.isEmpty(passwords)){

                    AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
                    alertDialog.setTitle("Registration Status");
                    alertDialog.setMessage("Unsuccessful\n      Please fill all boxes");
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
                    add_Data(user_name, first_name, last_name, e_mail, phone_number, passwords);
                }



            }
        });
    }

    private void add_Data(String username, String firstname, String lastname, String email, String phonenumber, String password){
        user_data.setUsername(username);
        user_data.setFirstname(firstname);
        user_data.setLastname(lastname);
        user_data.setEmail(email);
        user_data.setPhone_number(phonenumber);
        user_data.setPassword(password);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                reference.child(username).setValue(user_data);

                AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
                alertDialog.setTitle("Registration Status");
                alertDialog.setMessage("Successful");
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Register.this, Login.class));
                                finish();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Register.this, "Fail to add data ", Toast.LENGTH_SHORT).show();
            }
        });

    }


}