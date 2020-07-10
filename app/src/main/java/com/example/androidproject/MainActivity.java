package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.Instant;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//implements AdapterView.OnItemSelectedListener 開啟監聽器
    double[] proportion={0.54,0.77,1};//根據主計處每人每月消費(100-106)推演而來 //資料來源：行政院主計總處家庭收支調查
    int temp_re;double[] var={0,0,0};double form_page02;
    EditText Uid,edi_01,edi_02,edi_03;
    TextView show,ind_01,ind_02,ind_03,ind_04;
    Spinner mealfee,feel;
    Button btn01,btn_re;
    int temp=0,temp_lock=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show=findViewById(R.id.Show_01);
        btn01=findViewById(R.id.button_show);
        btn_re=findViewById(R.id.button_re);
        btn_re.setVisibility(View.INVISIBLE);
        Uid=findViewById(R.id.editText_id);
        Intent it=getIntent();
        Uid.setText(it.getStringExtra("uid"));
        form_page02=it.getDoubleExtra("每日收入",1000);
       //Uid.setText(String.format("%.1f",it.getDoubleExtra("每日收入",10000)));//測試


        //資料欄位
        edi_01=findViewById(R.id.editText_bre);
        edi_01.setText("20");//預設值
        edi_01.setVisibility(View.INVISIBLE);
        edi_02=findViewById(R.id.editText_lun);
        edi_02.setText("70");//預設值
        edi_02.setVisibility(View.INVISIBLE);
        edi_03=findViewById(R.id.editText_din);
        edi_03.setText("60");//預設值
        edi_03.setVisibility(View.INVISIBLE);
        //說明欄位
        ind_01=findViewById(R.id.instructions01);
        ind_02=findViewById(R.id.instructions02);
        ind_02.setVisibility(View.INVISIBLE);//早餐
        ind_03=findViewById(R.id.instructions03);
        ind_03.setVisibility(View.INVISIBLE);//午餐
        ind_04=findViewById(R.id.instructions04);
        ind_04.setVisibility(View.INVISIBLE);//晚餐

        mealfee=findViewById(R.id.spineer_meel);
        mealfee.setOnItemSelectedListener(this);

        feel=findViewById(R.id.spinner_feel);
        //feel.setOnItemSelectedListener(this);
//        feel.setVisibility(View.INVISIBLE);
    }

    public  void  Again(View w){
        temp_re+=1;
        temp=0;show.setText(null);
        btn01.setText("確認");
        btn_re.setVisibility(View.INVISIBLE);
    }

    public  void Show_butten (View w){

        String[] mellfees=getResources().getStringArray(R.array.MealFee);//取得spinner陣列中文字
        int index_mell=mealfee.getSelectedItemPosition();//取得餐點編號
        int indx_feel=feel.getSelectedItemPosition();//取得感覺選項編號
        String[] str={edi_01.getText().toString(),edi_02.getText().toString(),edi_03.getText().toString()};
       // String str1=edi_01.getText().toString();
       // String str2=edi_02.getText().toString();
       // String str3=edi_03.getText().toString();
        //show.setText(show.getText()+""+ind_01.getText()+" : "+Uid.getText()+"\n"+  mellfees[index_mell]);//測試

        if(btn01.getText().toString().equals("下一步")){
            Intent it=new Intent(this,Main2Activity.class);


            it.putExtra("早餐", var[0]);
            it.putExtra("午餐",var[1]);
            it.putExtra("晚餐",var[2]);
            it.putExtra("每日支出",form_page02);

            startActivity(it);
        }


        if(btn01.getText().toString().equals("再次確認")){
            temp=3;
            btn01.setText("下一步");
        }

        if(temp==0 &&temp_lock>=5) {
            show.setText(null);//每次按一下先清除資料
            for (int i=0;i<=2;i++){
                try {
                    var[i]=Double.parseDouble(str[i])*proportion[indx_feel];//消費金額依序 * 比重
                }catch (Exception e){
                    var[i]=0;
                }
                //show.setText(show.getText()+""+var[i]);//測試迴圈用
            }
            for (int i=0;i<=2;i++){
                try {
                    show.setText(show.getText()+""+"\n"+  mellfees[i]+" "+String.format("%.1f",var[i]));
                }catch (Exception e){
                    show.setText(show.getText()+"第 "+i+" 項錯誤");
                }
            }
            temp=2;
            btn01.setText("再次確認");
            btn_re.setVisibility(View.VISIBLE);}
        else if(edi_01.getText().toString()!="")
                {show.setText("請輸入完整資料");}












        //feel.setVisibility(View.VISIBLE);

        //show.setText(String.format("%.1f",temp_re[0]));//測試
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



       while (temp==0){
            Conversion(position);
            temp=1;
        }
       // while (temp==1){show.setText("1");}




        // double temp_re=Double.parseDouble(String.valueOf(edi_01));
        //if(feel.){if(position==2)ind_04.setVisibility(View.VISIBLE);}
        //show.setText(String.format("%.1f",proportion[0]));
        if(temp==1)temp=0;
    }

    private void Conversion(int position) {
        if(position==0){
            edi_01.setVisibility(View.VISIBLE);
            ind_02.setVisibility(View.VISIBLE);
            if(temp_lock<1)temp_lock+=1;
        }else if(position==1){
            edi_02.setVisibility(View.VISIBLE);
            ind_03.setVisibility(View.VISIBLE);
            if(temp_lock<2 || temp_lock>4)temp_lock+=2;
        }else {
            edi_03.setVisibility(View.VISIBLE);
            ind_04.setVisibility(View.VISIBLE);
            if(temp_lock<=3)temp_lock+=3;
            feel.setVisibility(View.VISIBLE);
        }
        //feel.setVisibility(View.VISIBLE);

//show.setText(temp);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


