package com.alperenburakyesil.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Devices extends AppCompatActivity {

    Button new_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        add_device();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    private void add_device(){
        new_device = findViewById(R.id.add_a_new_device);

        new_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Devices.this, AddDevice.class));
                onStop();
            }
        });
    }
}