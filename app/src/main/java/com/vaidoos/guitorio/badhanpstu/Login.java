package com.vaidoos.guitorio.badhanpstu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText mName,mPassword;

    private String username,password;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mName = (EditText) findViewById(R.id.etName);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mButton = (Button) findViewById(R.id.button) ;

        username = mName.getText().toString();
        password = mPassword.getText().toString();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mName.getText().toString().equals("badhan") && mPassword.getText().toString().equals("badhanpstu")) {
                    Intent i = new Intent(Login.this, DonorSearch.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "please enter valid user name & password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
