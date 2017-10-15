package com.vaidoos.guitorio.badhanpstu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DonorRegisterForm extends AppCompatActivity {


    private String full_name,blood_group,gender,id,faculty,season,contact_no;

    private EditText etFullname,etContactNo,etID,etSeason;

    private Spinner spnBloodGroup,spnGender,spnFaculty;

    private ArrayAdapter spnAdapterBloodGroup,spnAdapterGender,spnAdapterFaculty;

    private Button btnRegister;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_register_form);

        getSupportActionBar().setTitle("Donar Registration");

        spnBloodGroup = (Spinner) findViewById(R.id.spnBloodGroup);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        spnFaculty = (Spinner) findViewById(R.id.spnFaculty);

        etFullname = (EditText) findViewById(R.id.etFullName);
        etContactNo = (EditText) findViewById(R.id.etContactNo);
        etID = (EditText) findViewById(R.id.etID);
        etSeason = (EditText) findViewById(R.id.etSeason);

        btnRegister = (Button) findViewById(R.id.btnRegister);



        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(DonorRegisterForm.this, R.array.blood_group, android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnBloodGroup.setAdapter(spnAdapterBloodGroup);

        spnBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DonorSearch.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (blood_group == parent.getItemAtPosition(0))
                    Toast.makeText(DonorRegisterForm.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================


        //================================ Gender =====================================

        spnAdapterGender = ArrayAdapter.createFromResource(DonorRegisterForm.this,R.array.gender,android.R.layout.simple_spinner_item);
        spnAdapterGender.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnGender.setAdapter(spnAdapterGender);

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(DonorRegisterForm.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Gender =====================================


        //================================ Faculty =====================================

        spnAdapterFaculty = ArrayAdapter.createFromResource(DonorRegisterForm.this,R.array.faculty,android.R.layout.simple_spinner_item);
        spnAdapterFaculty.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnFaculty.setAdapter(spnAdapterFaculty);

        spnFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                faculty = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(DonorRegisterForm.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Faculty =====================================


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(DonorRegisterForm.this);
                progressDialog.setMessage("Processing...");
                progressDialog.show();
                progressDialog.setCancelable(false);


                full_name = etFullname.getText().toString();
                contact_no = etContactNo.getText().toString();
                id = etID.getText().toString();
                season = etSeason.getText().toString();

                //Toast.makeText(DonorRegisterForm.this, full_name+" "+" "+blood_group+" "+gender+" "+id+" "+faculty+" "+season+" "+contact_no, Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REGISTER,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressDialog.dismiss();

                                Toast.makeText(DonorRegisterForm.this, response, Toast.LENGTH_SHORT).show();

                                Intent refresh = new Intent(DonorRegisterForm.this, DonorRegisterForm.class);
                                startActivity(refresh);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonorRegisterForm.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("full_name",full_name);
                        params.put("blood_group",blood_group);
                        params.put("gender",gender);
                        params.put("student_id",id);
                        params.put("faculty",faculty);
                        params.put("season",season);
                        params.put("contact_no",contact_no);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(DonorRegisterForm.this);
                requestQueue.add(stringRequest);

            }
        });

    }
}
