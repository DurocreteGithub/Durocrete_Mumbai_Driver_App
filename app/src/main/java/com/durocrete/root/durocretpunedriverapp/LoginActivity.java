package com.durocrete.root.durocretpunedriverapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;

import com.durocrete.root.durocretpunedriverapp.Utillity.MyPreferenceManager;
import com.durocrete.root.durocretpunedriverapp.Utillity.SharedPreference;
import com.durocrete.root.durocretpunedriverapp.Utillity.Utility;
import com.durocrete.root.durocretpunedriverapp.comman.URLS;
import com.durocrete.root.durocretpunedriverapp.listeners.VolleyResponseListener;
import com.durocrete.root.durocretpunedriverapp.model.CheckInOUTModel;
import com.durocrete.root.durocretpunedriverapp.network.RequestHandler;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = LoginActivity.class.getSimpleName();
    private Activity mActivity;
    private EditText edtPassword, etDriverLogin;
    private ImageView imgShowPassword;
    private Button btnLogin;
    MyPreferenceManager sharedPref;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = LoginActivity.this;
        sharedPref = new MyPreferenceManager(this);


        if (sharedPref.getBooleanPreferences(MyPreferenceManager.Loggedin)) {
            startActivity(new Intent(mActivity, RouteNSiteSelectionActivity.class));
            mActivity.finish();
        }

//
//        if (!SharedPreference.getInstanceProfileData(mActivity).getUserId().equals("")) {
//            Log.v(TAG, "User are login before");
//            startActivity(new Intent(mActivity, RouteNSiteSelectionActivity.class));
//            finish();
//        }

        setContentView(R.layout.activity_login);
        initializeUI();
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView()");

      *//*  if (!SharedPreference.getInstanceProfileData(getActivity()).getUserId().equals("")) {
            Log.v(TAG, "User are login before");
            getActivity().startActivity(new Intent(getActivity(), RouteNSiteSelectionActivity.class));
            return null;
        } else {*//*
            View view = inflater.inflate(R.layout.activity_login, container, false);

            etDriverLogin = (EditText) view.findViewById(R.id.et_Driver_login);

            edtPassword = (EditText) view.findViewById(R.id.edt_password);

            imgShowPassword = (ImageView) view.findViewById(R.id.img_show_password);

            btnLogin = (Button) view.findViewById(R.id.btn_login);

            imgShowPassword.setOnClickListener(this);
            btnLogin.setOnClickListener(this);
            *//*return view;
        }*//*
    }*/

    private void initializeUI() {
        etDriverLogin = (EditText) findViewById(R.id.etxuserID);





        btnLogin = (Button) findViewById(R.id.btnlogin);


        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnlogin:
                if (etDriverLogin.getText().toString().equals("")) {
                    Utility.errorDialog(getResources().getString(R.string.enter_driver_id), mActivity);
                } else
                    loginUser(etDriverLogin.getText().toString().trim());
                break;

        }

    }

    /* Used for loginUser Action */
    private void loginUser(final String loginId) {

        if (loginId == null) {
            Toast.makeText(this, "Invalid Driver ID", Toast.LENGTH_SHORT).show();
        } else {
            Log.v(TAG, " URLS.getInstance().loginDriveURL : " + URLS.getInstance().loginDriveURL + loginId);
            RequestHandler.makeWebservice(true, mActivity, Request.Method.GET, URLS.getInstance().loginDriveURL + loginId, null, CheckInOUTModel[].class, new VolleyResponseListener<CheckInOUTModel>() {
                @Override
                public void onResponse(CheckInOUTModel[] object) {
                    if (object[0] instanceof CheckInOUTModel) {
                        Log.v(TAG, "onResponse : " + object[0].getResult());

                        SharedPreference.getInstanceProfileData(mActivity).setUserId(object[0].getResult());
                        startActivity(new Intent(mActivity, RouteNSiteSelectionActivity.class));
                        sharedPref.setStringPreferences(MyPreferenceManager.UserId,object[0].getResult());
                        sharedPref.setStringPreferences(MyPreferenceManager.Username,loginId);
                        sharedPref.setBooleanPreferences(MyPreferenceManager.Loggedin,true);
                        mActivity.finish();
                    }
                }

                @Override
                public void onError(String message) {
                    Utility.errorDialog(message, mActivity);
                }
            });

        }
    }

    /*show text for some time */
    private void showPassword() {
        edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }, 3000);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            mActivity.finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(LoginActivity.this, "Double click to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }
}
