package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    Button btn_h,btn_c,btn_w,btn_re;
    TextView tex_show;
    EditText edi_price;
    double eat_cost,total_E;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

//        btn_h=findViewById(R.id.btn_home);
//        btn_c=findViewById(R.id.btn_car);
        btn_re=findViewById(R.id.btn_RE);
//        btn_re.setVisibility(View.INVISIBLE);
        btn_w=findViewById(R.id.btn_how);
//        btn_w.setVisibility(View.INVISIBLE);
        tex_show=findViewById(R.id.textView_show);
        edi_price=findViewById(R.id.edit_Price);
//        edi_price.setVisibility(View.INVISIBLE);

        Intent it=getIntent();
        total_E=it.getDoubleExtra("總支出",20);//假設賺多少 花多少
        eat_cost=it.getDoubleExtra("食物支出",150);

        //btton_home();
       // btton_car();
        //btton_how();

    }

    public void btton_how(View w) {

        edi_price.setVisibility(View.VISIBLE);
//        btn_w.setText("確定?!");
        if(edi_price.length()==0){
            tex_show.setText("請輸入完整資料");}
        else {
            String str=edi_price.getText().toString();
            double var=Double.parseDouble(str);
            tex_show.setText("目標存到 : "+String.format("%.0f",var/10000)+"萬"+"\n"
            +"不吃不喝要 :"+String.format("%.0f",var/total_E)+" 天"
            +"\n維持最低開銷要 :"+String.format("%.0f",var/(total_E-eat_cost))+" 天");

            btn_re.setVisibility(View.VISIBLE);
        }

        }
public  void  OneAgain(View w){
        Intent it =new Intent(this,Main3Activity.class);
        startActivity(it);
}



//    public void btton_car(View w) {
//        btn_w.setVisibility(View.VISIBLE);
//        btn_h.setVisibility(View.VISIBLE);
//        btn_w.setText("要花多少");
//        edi_price.setText(null);
//    }
//
//    public void btton_home(View w) {
//        btn_w.setVisibility(View.VISIBLE);
//        btn_h.setVisibility(View.VISIBLE);
//        btn_w.setText("要花多少");
//        edi_price.setText(null);
//    }
}
