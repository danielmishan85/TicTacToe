package com.example.matala1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
    }

    public void startPlayBtnOnClick(View view){
        Intent intent = new Intent(this, XPlayerScreen.class);
        startActivity(intent);
    }
}