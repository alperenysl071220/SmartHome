package com.alperenburakyesil.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class Room extends AppCompatActivity {

    ImageView user;
    static String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        show_more();
    }


    public void kitchen(View view) {
        room = "Kitchen";
        startActivity(new Intent(Room.this, InfoDev.class));
    }


    public void living_room(View view) {
        room = "Living_Room";
        startActivity(new Intent(Room.this, InfoDev.class));
    }

    public void dining_room(View view) {
        room = "Dining_Room";
        startActivity(new Intent(Room.this, InfoDev.class));
    }

    public void bathroom(View view) {
        room = "Bathroom";
        startActivity(new Intent(Room.this, InfoDev.class));
    }

    public void bedroom(View view) {
        room = "Bedroom";
        startActivity(new Intent(Room.this, InfoDev.class));
    }



    public void show_more(){

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Room.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.user_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.settings:
                                startActivity(new Intent(Room.this, Settings.class));
                                return true;
                            case R.id.exit:
                                startActivity(new Intent(Room.this, SignInUp.class));
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

}