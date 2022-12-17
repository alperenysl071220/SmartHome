package com.alperenburakyesil.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Add extends AppCompatActivity {

    static String device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void vave(View view) {
        device = "Vave";
        startActivity(new Intent(Add.this, Brands.class));
    }

    public void fridge(View view) {
        device = "Fridge";
        startActivity(new Intent(Add.this, Brands.class));
    }

    public void vacuum(View view) {
        device = "Vacuum";
        startActivity(new Intent(Add.this, Brands.class));
    }

    public void lamp(View view) {
        device = "Lamp";
        startActivity(new Intent(Add.this, Brands.class));
    }

    public void air_cond(View view) {
        device = "Air_Conditioner";
        startActivity(new Intent(Add.this, Brands.class));
    }
}