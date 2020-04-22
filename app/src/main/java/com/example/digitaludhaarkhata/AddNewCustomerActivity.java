package com.example.digitaludhaarkhata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNewCustomerActivity extends AppCompatActivity
{
    private TextInputLayout textInputLayoutCustomerName;
    private TextInputLayout textInputLayoutMobileNumber;
    private TextInputLayout textInputLayoutRupees;
    private MaterialButton materialButtonYouGave;
    private MaterialButton materialButtonYouGot;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_customer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.stringOfAddNewCustomer));

        textInputLayoutCustomerName = findViewById(R.id.idEditViewCustomerNameAddNewCustomerActivity);
        textInputLayoutMobileNumber = findViewById(R.id.idEditViewMobileNumberAddNewCustomerActivity);
        textInputLayoutRupees = findViewById(R.id.idEditViewRupeesAddNewCustomerActivity);
        materialButtonYouGave = findViewById(R.id.idButtonYouGaveAddNewCustomerActivity);
        materialButtonYouGot = findViewById(R.id.idButtonYouGotAddNewCustomerActivity);

        materialButtonYouGave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                youGaveMoney();
            }
        });

        materialButtonYouGot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                youGotMoney();
            }
        });

        progressDialog = new ProgressDialog(this);
    }

    private void youGaveMoney()
    {
        textInputLayoutCustomerName.setErrorEnabled(false);
        textInputLayoutMobileNumber.setErrorEnabled(false);
        textInputLayoutRupees.setErrorEnabled(false);

        final String userid = SharedPreferenceManager.getInstance(this).getUserId();

        final String customername = textInputLayoutCustomerName.getEditText().getText().toString().trim();
        final String customermobile = textInputLayoutMobileNumber.getEditText().getText().toString().trim();

        final String usergave = textInputLayoutRupees.getEditText().getText().toString().trim();
        final String usergot = "0";

        if(customername.length() == 0)
        {
            textInputLayoutCustomerName.setError("Blank field is not valid");
            return;
        }

        if(customermobile.length() == 0)
        {
            textInputLayoutMobileNumber.setError("Blank field is not valid");
            return;
        }

        if(usergave.length() == 0)
        {
            textInputLayoutRupees.setError("Blank field is not valid");
            return;
        }

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_REGISTER_CUSTOMER,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();

                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);


                            if(!jsonObject.getBoolean("error"))
                            {
                                startActivity(new Intent(AddNewCustomerActivity.this, HomePageActivity.class));
                                finish();
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
                params.put("userid", userid);
                params.put("customername", customername);
                params.put("customermobile", customermobile);
                params.put("usergave", usergave);
                params.put("usergot", usergot);

                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void youGotMoney()
    {
        textInputLayoutCustomerName.setErrorEnabled(false);
        textInputLayoutMobileNumber.setErrorEnabled(false);
        textInputLayoutRupees.setErrorEnabled(false);

        final String userid = SharedPreferenceManager.getInstance(this).getUserId();

        final String customername = textInputLayoutCustomerName.getEditText().getText().toString().trim();
        final String customermobile = textInputLayoutMobileNumber.getEditText().getText().toString().trim();

        final String usergot = textInputLayoutRupees.getEditText().getText().toString().trim();
        final String usergave = "0";

        if(customername.length() == 0)
        {
            textInputLayoutCustomerName.setError("Blank field is not valid");
            return;
        }

        if(customermobile.length() == 0)
        {
            textInputLayoutMobileNumber.setError("Blank field is not valid");
            return;
        }

        if(usergot.length() == 0)
        {
            textInputLayoutRupees.setError("Blank field is not valid");
            return;
        }

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_REGISTER_CUSTOMER,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();

                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);


                            if(!jsonObject.getBoolean("error"))
                            {
                                startActivity(new Intent(AddNewCustomerActivity.this, HomePageActivity.class));
                                finish();
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
                params.put("userid", userid);
                params.put("customername", customername);
                params.put("customermobile", customermobile);
                params.put("usergave", usergave);
                params.put("usergot", usergot);

                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }


}
