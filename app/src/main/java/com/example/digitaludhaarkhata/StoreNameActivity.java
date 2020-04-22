package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class StoreNameActivity extends AppCompatActivity
{

    private TextView textViewShopNameMessage;
    private TextInputLayout textInputLayoutShopName;
    private Button buttonSubmit;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_name);

        getSupportActionBar().hide();

        textViewShopNameMessage = findViewById(R.id.idTextViewShopNameMessageStoreNameActivity);
        textInputLayoutShopName = findViewById(R.id.idEditTextShopNameStoreNameActivity);
        buttonSubmit = findViewById(R.id.idButtonSubmitStoreNameActivity);

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                readShopName();
            }
        });

        progressDialog = new ProgressDialog(this);

    }

    private void readShopName()
    {

        final String mobile = SharedPreferenceManager.getInstance(this).getUserMobileNumber();

        textInputLayoutShopName.setErrorEnabled(false);
        final String name = textInputLayoutShopName.getEditText().getText().toString().trim();

        if(name.length() == 0)
        {
            textInputLayoutShopName.setError("Shop Name Not Blank");
            return;
        }

        progressDialog.setMessage("Pleas Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_REGISTER_USER,
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
                                SharedPreferenceManager.getInstance(getApplicationContext())
                                        .userShopName(name);
                                SharedPreferenceManager.getInstance(getApplicationContext())
                                        .userId(jsonObject.getString("id"));

                                startActivity(new Intent(StoreNameActivity.this, HomePageActivity.class));
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
                params.put("mobile", mobile);
                params.put("name", name);

                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
