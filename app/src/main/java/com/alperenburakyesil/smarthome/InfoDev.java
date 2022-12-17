package com.alperenburakyesil.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoDev extends AppCompatActivity {

    ImageButton user;
    EditText serial_no, purchase_date;

    String account_username;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_dev);

        //User
        account_username = Login.sent_username;

        //Database
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User").child(account_username);

        //Texts
        serial_no = findViewById(R.id.serial_no);
        purchase_date = findViewById(R.id.purchase_date);

        show_more();
    }

     public void show_more(){

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(InfoDev.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.user_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.settings:
                                startActivity(new Intent(InfoDev.this, Settings.class));
                                return true;
                            case R.id.exit:
                                startActivity(new Intent(InfoDev.this, SignInUp.class));
                                finish();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    public void confirm(View view) {

        if (String.valueOf(serial_no.getText()).isEmpty() || String.valueOf(purchase_date.getText()).isEmpty()){
            AlertDialog alertDialog = new AlertDialog.Builder(InfoDev.this).create();
            alertDialog.setTitle("Device Status");
            alertDialog.setMessage("Unsuccessful\n      Please fill all boxes");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
        }

        else {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    reference.child(Add.device).child(Brands.brand).child(Room.room).setValue(serial_no.getText(), purchase_date.getText());

                    android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(InfoDev.this).create();
                    alertDialog.setTitle("Device Status");
                    alertDialog.setMessage("Device Added");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(InfoDev.this, Devices.class));
                                }
                            });
                    alertDialog.show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(InfoDev.this, "Fail to add data ", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}