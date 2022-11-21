package com.alperenburakyesil.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddDevice extends AppCompatActivity {
    Button brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        brand = findViewById(R.id.choose_a_brand);

        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {System.exit(0);}
        });
    }
}