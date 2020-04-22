package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccountVerificationActivity extends AppCompatActivity
{

    private TextView textViewVerifyMobile;
    private TextView textViewVerifyMobileMessage;
    private TextView textViewOTPMessage;
    private TextInputLayout textInputLayoutMobileNumber;
    private TextInputLayout textInputLayoutOTP;
    private Button buttonSend;
    private Button buttonVerifyOTP;
    private Button buttonWithoutOTP;

    public int oneTimePassword;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);

        getSupportActionBar().hide();

        textViewVerifyMobile = findViewById(R.id.idTextViewVerifyMobileAccountVerificationActivity);
        textViewVerifyMobileMessage = findViewById(R.id.idTextViewVerifyMobileMessageAccountVerificationActivity);
        textViewOTPMessage = findViewById(R.id.idTextViewOTPMessageAccountVerificationActivity);
        textInputLayoutMobileNumber = findViewById(R.id.idEditViewMobileNumberAccountVerificationActivity);
        textInputLayoutOTP = findViewById(R.id.idEditViewOTPAccountVerificationActivity);
        buttonSend = findViewById(R.id.idButtonSendAccountVerificationActivity);
        buttonVerifyOTP = findViewById(R.id.idButtonOTPAccountVerificationActivity);

        buttonWithoutOTP = findViewById(R.id.idButtonGoWithoutOTPAccountVerificationActivity);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        buttonWithoutOTP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                withoutOTP();

            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                userExist();
////                Log.d("ddhdhddgd", "onClick: "+s);
                sendOTP();
            }
        });

        buttonVerifyOTP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                verifyOTP();
            }
        });

        progressDialog = new ProgressDialog(this);

    }

    private void  userExist()
    {
        textInputLayoutMobileNumber.setErrorEnabled(false);

        final String mobileNumber = textInputLayoutMobileNumber.getEditText().getText().toString().trim();

        if(mobileNumber.length() == 0)
        {
            textInputLayoutMobileNumber.setError("Mobile Number Not Blank");
            return ;
        }

        progressDialog.setMessage("Pleas Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_CUSTOMER_EXIST,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();

                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);


                            if(jsonObject.getBoolean("error"))
                            {
                                startActivity(new Intent(AccountVerificationActivity.this, HomePageActivity.class));
                                finish();
                            }
                            else
                            {
                                sendOTP();
                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobileNumber);

                return params;
            }

        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void withoutOTP()
    {
        textInputLayoutMobileNumber.setErrorEnabled(false);

        String mobileNumber = textInputLayoutMobileNumber.getEditText().getText().toString().trim();

        if(mobileNumber.length() == 0)
        {
            textInputLayoutMobileNumber.setError("Mobile Number Not Blank");
            return;
        }

        SharedPreferenceManager.getInstance(getApplicationContext())
                .userMobileNumber(mobileNumber);

        finish();
        startActivity(new Intent(AccountVerificationActivity.this, StoreNameActivity.class));


    }

    private void showAlertDialogOTP()
    {

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(AccountVerificationActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("OTP VERIFIED SUCCESSFULLY");

        // Set Alert Title
        builder.setTitle("OTP");

        // Set Cancelable false// Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.

        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "Ok",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app will close
                                finish();
                                startActivity(new Intent(AccountVerificationActivity.this, StoreNameActivity.class));

                            }
                        });


        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    private void verifyOTP()
    {
        String stringOTP = "";
               stringOTP = textInputLayoutOTP.getEditText().getText().toString().trim();
//        int OTP = Integer.parseInt(s);
        String  d = String.valueOf(oneTimePassword);

        if(stringOTP.equals(d))
        {

            showAlertDialogOTP();
//            Toast toast = Toast.makeText(
//                    getApplicationContext(),
//                    "OTP VERIFIED SUCCESSFULLY",
//                    Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "WRONG OTP",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

    }

    private void sendOTP()
    {
        textInputLayoutMobileNumber.setErrorEnabled(false);

        String mobileNumber = textInputLayoutMobileNumber.getEditText().getText().toString().trim();

        if(mobileNumber.length() == 0)
        {
            textInputLayoutMobileNumber.setError("Mobile Number Not Blank");
            return;
        }



        Random random = new Random();
        oneTimePassword = random.nextInt(999999);

        /**************/
        try
        {
            // Construct data
            String apiKey = "apikey=" + URLEncoder.encode("IZ1xKamwFAY-LRzqKOw7LZCRC0Um1Q1hBmeEWGVLmh\t", "UTF-8");
            String message = "&message=" + URLEncoder.encode("#Your One Time Password is "+oneTimePassword, "UTF-8");
            String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
            String numbers = "&numbers=" + URLEncoder.encode(mobileNumber, "UTF-8");

            // Send data
            String data = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;
            URL url = new URL(data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String sResult="";
            while ((line = rd.readLine()) != null)
            {
                Log.d("TAG", "sendOTP: "+"dddddddddd");
                // Process line...
                sResult=sResult+line+" ";
            }
            rd.close();

            Log.d("TAG", "sendOTP: "+"111111111111111111111111");
            textViewVerifyMobileMessage.setVisibility(View.GONE);
            textInputLayoutMobileNumber.setVisibility(View.GONE);
            buttonSend.setVisibility(View.GONE);

            textViewOTPMessage.setVisibility(View.VISIBLE);
            textInputLayoutOTP.setVisibility(View.VISIBLE);
            buttonVerifyOTP.setVisibility(View.VISIBLE);

            SharedPreferenceManager.getInstance(getApplicationContext())
                    .userMobileNumber(mobileNumber);

            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "OTP SEND SUCCESSFULLY",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "OTP NOT SEND SUCCESSFULLY"+e,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            Log.d("TAG", "onClick: "+e);
        }
        /************/

    }
}
