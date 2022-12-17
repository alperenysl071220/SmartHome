package com.alperenburakyesil.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class Brands extends AppCompatActivity {

    ImageButton user;

    static String brand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);

        show_more();


    }

    public void bosch(View view) {
         brand = "Bosch";
         startActivity(new Intent(Brands.this, Room.class));
    }

    public void siemens(View view) {
        brand = "Siemens";
        startActivity(new Intent(Brands.this, Room.class));
    }

    public void samsung(View view) {
        brand = "Samsung";
        startActivity(new Intent(Brands.this, Room.class));
    }

    public void lg(View view) {
        brand = "Lg";
        startActivity(new Intent(Brands.this, Room.class));
    }

    public void grunding(View view) {
        brand = "Grunding";
        startActivity(new Intent(Brands.this, Room.class));
    }

    public void show_more(){

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Brands.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.user_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.settings:
                                startActivity(new Intent(Brands.this, Settings.class));
                                return true;
                            case R.id.exit:
                                startActivity(new Intent(Brands.this, SignInUp.class));
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