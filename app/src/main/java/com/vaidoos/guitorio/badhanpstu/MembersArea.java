package com.vaidoos.guitorio.badhanpstu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MembersArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_area);
    }

    public void btnMembersSrch(View view) {
        startActivity(new Intent(this,DonorSearch.class));
    }

    public void btnRegisterDonor(View view) {
        startActivity(new Intent(this,DonorRegisterForm.class));
    }
}
