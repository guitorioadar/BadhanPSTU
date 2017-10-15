package com.vaidoos.guitorio.badhanpstu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    TextView num_sakib,num2,num3,num4;
    Button call_sakib,call1,call2,call3;
    String sNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setTitle("Contact");

        call_sakib = (Button) findViewById(R.id.call_sakib);
        num_sakib = (TextView) findViewById(R.id.sakib);

        call1 = (Button) findViewById(R.id.call1);
        num2 = (TextView) findViewById(R.id.num2);

        call2 = (Button) findViewById(R.id.call2);
        num3 = (TextView) findViewById(R.id.num3);

        call3 = (Button) findViewById(R.id.call3);
        num4 = (TextView) findViewById(R.id.num4);

    }

    public void callSakib(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);


        sNum = num_sakib.getText().toString();
        if(sNum.trim().isEmpty()){
            i.setData(Uri.parse("tel:01620606689"));
        }
        else{
            i.setData(Uri.parse("tel:"+sNum));
        }
        startActivity(i);
    }

    public void call2(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);


        sNum = call1.getText().toString();
        if(sNum.trim().isEmpty()){
            i.setData(Uri.parse("tel:01834546395"));
        }
        else{
            i.setData(Uri.parse("tel:"+sNum));
        }
        startActivity(i);
    }

    public void call3(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);


        sNum = call2.getText().toString();
        if(sNum.trim().isEmpty()){
            i.setData(Uri.parse("tel:01749113495"));
        }
        else{
            i.setData(Uri.parse("tel:"+sNum));
        }
        startActivity(i);
    }

    public void call4(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);


        sNum = call3.getText().toString();
        if(sNum.trim().isEmpty()){
            i.setData(Uri.parse("tel:01558911700"));
        }
        else{
            i.setData(Uri.parse("tel:"+sNum));
        }
        startActivity(i);
    }

}


