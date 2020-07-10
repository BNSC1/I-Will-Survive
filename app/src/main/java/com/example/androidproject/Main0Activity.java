package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main0Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
    }
    public void onClick(View v){
        Intent it=new Intent(this,Main3Activity.class); //MainActivity改為下一頁面名
        startActivity(it);
    }

}
