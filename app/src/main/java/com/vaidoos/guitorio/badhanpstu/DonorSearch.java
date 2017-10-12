package com.vaidoos.guitorio.badhanpstu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DonorSearch extends AppCompatActivity {

    private ArrayAdapter spnAdapterBloodGroup;

    private Spinner spnBloodGroup;

    private String blood_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_search);

        spnBloodGroup = (Spinner) findViewById(R.id.spnDonor);


        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(DonorSearch.this,R.array.blood_group,android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnBloodGroup.setAdapter(spnAdapterBloodGroup);

        spnBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(BloodDonorSearch.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(blood_group==parent.getItemAtPosition(0))
                    Toast.makeText(DonorSearch.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("blood_group", blood_group);

                return params;
            }
        };


    }
}
