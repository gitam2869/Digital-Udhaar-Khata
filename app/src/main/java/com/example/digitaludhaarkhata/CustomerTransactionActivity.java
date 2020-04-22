package com.example.digitaludhaarkhata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerTransactionActivity extends AppCompatActivity
{
    public String stringName;
    public String stringMobile;

    private MaterialButton materialButtonCustomerTrancation;

    private ProgressDialog progressDialog;

    //adapter
    private CustomerTransactionAdapter adapter;

    //a list to store all the prs users name
    List<CustomerTransactionInfo> customerTransactionInfoList;

    //the recyclerview
    RecyclerView recyclerView;

    TextView textViewTotalBalance;
    TextView textViewYouGave;
    TextView textViewYouGot;
    TextView textViewTotalMessage;

    public int youGave;
    public int youGot;
    public int total;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_transaction);

        materialButtonCustomerTrancation = findViewById(R.id.idButtonMakeYourTransactionCustomerTransactionActivity);

        textViewTotalBalance = findViewById(R.id.idTextViewTotalBalanceCustomerTransactionActivity);
        textViewYouGave = findViewById(R.id.idTextViewYouGaveCustomerTransactionActivity);
        textViewYouGot = findViewById(R.id.idTextViewYouGotCustomerTransactionActivity);
        textViewTotalMessage = findViewById(R.id.idTextViewTotalMessageCustomerTransactionActivity);

        customerTransactionInfoList = new ArrayList<>();

        recyclerView = findViewById(R.id.idRecycleViewCustomerTransaction);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomerTransactionAdapter(customerTransactionInfoList);

        stringName = getIntent().getStringExtra("name");
        stringMobile = getIntent().getStringExtra("mobile");

        Log.d("d0000000000000000d", "onCreate: "+stringName);
        Log.d("d0000000000000000d", "onCreate: "+stringMobile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(stringName);

        materialButtonCustomerTrancation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), AddCustomerActivity.class);
                intent.putExtra("name", stringName);
                intent.putExtra("mobile", stringMobile);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(this);

        getTransactionDetails();
    }

    private void getTransactionDetails()
    {
        final String customermobile = stringMobile;

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_CUSTOMER_TRANSACTIONS,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++)
                            {

                                //getting product object from json array
                                JSONObject jsonObject = array.getJSONObject(i);

                                customerTransactionInfoList.add(new CustomerTransactionInfo(
                                                jsonObject.getString("userid"),
                                                jsonObject.getString("customername"),
                                                jsonObject.getString("customermobile"),
                                                jsonObject.getString("usergave"),
                                                jsonObject.getString("usergot"),
                                                jsonObject.getString("date")
                                        )
                                );

                                youGave = youGave + jsonObject.getInt("usergave");
                                youGot = youGot + jsonObject.getInt("usergot");


                                Log.d("goooooooooooo", "onResponse: "+youGave);

                                Log.d("goooooooooooo", "onResponse: "+youGot);
                            }

                            total = youGave + youGot;

                            textViewYouGave.setText(""+youGave);
                            textViewYouGot.setText(""+youGot);
                            textViewTotalBalance.setText(""+total);

                            if(youGave > youGot)
                            {
                                textViewTotalMessage.setText(getResources().getString(R.string.stringOfYouWillTake));
                                textViewTotalMessage.setTextColor(getResources().getColor(R.color.colorOfGreen));
                                int amount = youGave - youGot;
                                textViewTotalBalance.setText(""+amount);
                            }
                            else if(youGave < youGot)
                            {
                                textViewTotalMessage.setText(getResources().getString(R.string.stringOfYouWillGive));
                                textViewTotalMessage.setTextColor(getResources().getColor(R.color.colorOfRed));
                                int amount = youGot - youGave;
                                textViewTotalBalance.setText(""+amount);
                            }
                            else
                            {
                                textViewTotalMessage.setText("Transaction");
                                textViewTotalMessage.setTextColor(getResources().getColor(R.color.colorOfOrange));
                                int amount = youGave - youGot;
                                textViewTotalBalance.setText("is Done");
                                textViewTotalBalance.setTextColor(getResources().getColor(R.color.colorOfOrange));

                            }

                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();
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
                params.put("mobile", customermobile);

                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
