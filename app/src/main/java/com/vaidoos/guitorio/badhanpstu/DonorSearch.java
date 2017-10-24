package com.vaidoos.guitorio.badhanpstu;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonorSearch extends AppCompatActivity {


    private ListView listView;
    private List<Donor> donorList;
    private DonorAdapter donorAdapter;

    private ArrayAdapter spnAdapterBloodGroup;

    private Spinner spnBloodGroup;

    private String blood_group;

    private Button mBtnSearch,btnRefresh;

    private JSONArray jsonArray = null;

    private ProgressDialog progressDialog;

    public static final String JSON_ARRAY = "result";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_search);

        getSupportActionBar().setTitle("Search Donar");

        listView = (ListView) findViewById(R.id.listviewDonors);

        mBtnSearch = (Button) findViewById(R.id.btnSearch);

        spnBloodGroup = (Spinner) findViewById(R.id.spnDonor);




        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(DonorSearch.this, DonorSearch.class);
                startActivity(refresh);
            }
        });

        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(DonorSearch.this, R.array.blood_group, android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnBloodGroup.setAdapter(spnAdapterBloodGroup);

        spnBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DonorSearch.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();
                //donorAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (blood_group == parent.getItemAtPosition(0))
                    Toast.makeText(DonorSearch.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //jsonArray = null;


                donorList = new ArrayList<Donor>();


                progressDialog = new ProgressDialog(DonorSearch.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                //Toast.makeText(DonorSearch.this, blood_group.toString(), Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SEARCH,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {



                                progressDialog.dismiss();

                                //Toast.makeText(DonorSearch.this, response.toString(), Toast.LENGTH_SHORT).show();
                                JSONObject jsonObject = null;

                                try {


                                    jsonObject = new JSONObject(response);
                                    jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                                    if (jsonArray.length() == 0) {
                                        Toast.makeText(DonorSearch.this, "No Donor Resgistered", Toast.LENGTH_SHORT).show();
                                    } else {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject object = jsonArray.getJSONObject(i);
                                            Donor donor = new Donor();
                                            donor.setFull_name(object.getString("full_name"));
                                            donor.setBlood_group(object.getString("blood_group"));
                                            donor.setContact_no(object.getString("contact_no"));
                                            donorList.add(donor);
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                donorAdapter = new DonorAdapter(DonorSearch.this, donorList);
                                listView.setAdapter(donorAdapter);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonorSearch.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("blood_group", blood_group);


                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(DonorSearch.this);
                requestQueue.add(stringRequest);


            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Donor dnr = donorList.get(position);

                //Toast.makeText(DonorSearch.this, dnr.getContact_no(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dnr.getContact_no()));
                if (ActivityCompat.checkSelfPermission(DonorSearch.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });



    }

}
