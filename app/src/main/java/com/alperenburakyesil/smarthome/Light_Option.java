package com.alperenburakyesil.smarthome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.imageview.ShapeableImageView;

public class Light_Option extends AppCompatActivity {

    ShapeableImageView shapeableImageView;

    ImageButton user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_option);

        shapeableImageView = (ShapeableImageView) findViewById(R.id.color_change);

        colors_change();

        show_more();
    }

    private void colors_change() {

        shapeableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Light_Option.this);
                builder.setTitle("Choose Color");

                LayoutInflater layoutInflater = Light_Option.this.getLayoutInflater();
                View change_color = layoutInflater.inflate(R.layout.change_color, null);

                builder.setView(change_color);

                builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

            }
        });
    }

    public void show_more(){

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Light_Option.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.user_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.settings:
                                startActivity(new Intent(Light_Option.this, Settings.class));
                                return true;
                            case R.id.exit:
                                startActivity(new Intent(Light_Option.this, SignInUp.class));
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