package com.alperenburakyesil.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.android.material.imageview.ShapeableImageView;

public class Vaccum_Option extends AppCompatActivity {

    ShapeableImageView shapeableImageView;

    ImageView user;

    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccum_option);
        shapeableImageView = findViewById(R.id.clean_all);

        clean_all();

        show_more();
    }

    private void clean_all() {

        shapeableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status == 0){
                    shapeableImageView.setStrokeColor(ColorStateList.valueOf(Color.BLUE));
                    shapeableImageView.setStrokeWidth(20);
                    status++;
                }

                else if(status == 1){
                    shapeableImageView.setStrokeColor(null);
                    shapeableImageView.setStrokeWidth(0);
                    status--;
                }

            }
        });
    }

    public void show_more(){

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Vaccum_Option.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.user_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.settings:
                                startActivity(new Intent(Vaccum_Option.this, Settings.class));
                                return true;
                            case R.id.exit:
                                startActivity(new Intent(Vaccum_Option.this, SignInUp.class));
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