package com.example.week6_nhom6_advertisingid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caverock.androidsvg.SVGParseException;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void onBackClick(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}