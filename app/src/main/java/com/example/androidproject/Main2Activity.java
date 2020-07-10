package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    double breakfast,lunch,dinner,othercost=100,diningcost,totalcost;
    TextView status,youare,bata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       status =  findViewById(R.id.StatusTV);
       youare = findViewById(R.id.YouAreTV);
      bata=findViewById(R.id.bata);

        Intent it = getIntent(); //傳入參數?
        breakfast = it.getDoubleExtra("早餐", 20);                          //.getIntExtra("早餐", 0); //讀出早餐錢
        lunch = it.getDoubleExtra("午餐", 70);
        dinner = it.getDoubleExtra("晚餐", 60);
        othercost = it.getDoubleExtra("每日支出", 99);//假設 賺多少花多少
        //bata.setText(String.format("%.1f",breakfast));//測試

        //othercost = it.getIntExtra("其他支出", 1);
        diningcost = breakfast + lunch + dinner; //吃飯總支出
        totalcost=othercost;
        Calc();


    }

    protected void Calc(){
        double engel=diningcost/totalcost;
        String stryouare,strstatus;
        if (engel>.5){
            if(engel>.6) strstatus="貧窮";
            else strstatus="溫飽";
            stryouare="窮鬼";
        }
        else {
            if(engel>.4) strstatus="小康";
            else if(engel>.3) strstatus="相對富裕";
            else if(engel>.2) strstatus="富足";
            else strstatus="極其富裕";
            stryouare="好野人";
        }
        engel=engel*100;
        youare.setText("你是"+stryouare);
        status.setText("你的Engel‘s law 已達"+String.format("%.1f",engel)+"%\n符合聯合國"+strstatus+"標準");
    }
    public void onClick(View v){
        Intent it=new Intent(this,Main4Activity.class); //MainActivity改為下一頁面名
        it.putExtra("總支出",totalcost);
        it.putExtra("食物支出",diningcost);
        //it.putExtra("其他支出",othercost);
        startActivity(it);
    }

}
