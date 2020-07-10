package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity implements TextWatcher {
//implements TextWatcher 宣告EditText監聽器
    EditText edtuid,edtrev;
    TextView txv;
    double var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        edtuid = findViewById(R.id.edtUid);
        edtuid.setText("1002054");//預設值
        edtrev = findViewById(R.id.edtRev);
        txv = findViewById(R.id.txvRevday);

        edtrev.addTextChangedListener(this);//啟用監聽器
        edtuid.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str = edtrev.getText().toString();
        try {
            double b = Double.parseDouble(str)/30;
            var=b;
            txv.setText("每天平均收入： "+ String.format("%.1f",b));
        } catch(Exception e){
            txv.setText("收入請輸入整數");}
    }

    public void gotopage3(View v){
        if (edtuid.length()==0  ||  edtrev.length()==0)
        {txv.setText("請輸入完整資料");
        }else{ //修改前往下一頁
            Intent it = new Intent(this,MainActivity.class);
            String str1 = edtuid.getText().toString();
            //String str2 = edtrev.getText().toString();
            //String str3 = txv.getText().toString();


            it.putExtra("uid",str1);
            //it.putExtra("edtrev",str2);
            it.putExtra("每日收入",var);
            startActivity(it);}
    }

}
