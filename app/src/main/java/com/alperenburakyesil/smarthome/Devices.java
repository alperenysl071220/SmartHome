package com.alperenburakyesil.smarthome;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

public class Devices extends AppCompatActivity {


    ImageButton user;
    RelativeLayout light, vacuum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        light = findViewById(R.id.samsung_light_1);
        vacuum = findViewById(R.id.bosch_vacuum_1);

        show_more();

        device_control();
    }

    private void device_control() {

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Devices.this, Light_Option.class));
            }
        });

        vacuum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Devices.this, Vaccum_Option.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }


    public void addDevice(View view) {
        startActivity(new Intent(Devices.this, Add.class));
    }

    public void show_more(){

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Devices.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.user_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.settings:
                                startActivity(new Intent(Devices.this, Settings.class));
                                return true;
                            case R.id.exit:
                                startActivity(new Intent(Devices.this, SignInUp.class));
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