package com.vaidoos.guitorio.badhanpstu;

import android.app.Service;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ADAR on 10/13/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTICE";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Toast.makeText(this, refreshedToken, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
