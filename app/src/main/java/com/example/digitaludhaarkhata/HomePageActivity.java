package com.example.digitaludhaarkhata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity
{
    //adapter
    private CustomerListAdapter adapter;

    //a list to store all the prs users name
    List<CustomerListInfo> customerListInfoList;
    List<CustomerListInfo> list;


    //the recyclerview
    RecyclerView recyclerView;

    private MaterialToolbar materialToolbar;
    private FloatingActionButton floatingActionButton;

    private ProgressDialog progressDialog;

    TextView textViewName;
    TextView textViewPhone;

    public int youGave;
    public int youGot;
    public int total;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_home_page);

        materialToolbar = findViewById(R.id.idMaterialToolbarHomePageActivity);
        floatingActionButton = findViewById(R.id.idFloatingActionButtonAddCustomers);

        customerListInfoList = new ArrayList<>();
        list = new ArrayList<>();

        recyclerView = findViewById(R.id.idRecycleViewCustomerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomerListAdapter(customerListInfoList);

        adapter.setOnItemClickListener(new CustomerListAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                CustomerListInfo customerListInfo = customerListInfoList.get(position);


//                textViewName = recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.idTextViewCustomerNameCustomerList);
//                textViewPhone = recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.idTextViewContactPhoneContactReader);

                String name = customerListInfo.getCustomerName();
                String mobile = customerListInfo.getCustomerMobile();

                Intent intent = new Intent(getBaseContext(), CustomerTransactionActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        });

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"ddddd",Toast.LENGTH_LONG).show();
            }
        });

        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.item1 :
                        Toast.makeText(getApplicationContext(),"sf",Toast.LENGTH_LONG).show();
                        break;

                    default:
                }

                return HomePageActivity.super.onOptionsItemSelected(item);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(HomePageActivity.this, ContactsReaderActivity.class));
            }
        });

        String userMobileNumber = SharedPreferenceManager.getInstance(this).getUserMobileNumber();
        String userShopName = SharedPreferenceManager.getInstance(this).getUserShopName();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        actionBar.setIcon(R.drawable.ic_forward_black_24dp);

        progressDialog = new ProgressDialog(this);

        getCustomerList();

    }

    private void getCustomerList()
    {
        final String userid = SharedPreferenceManager.getInstance(this).getUserId();

        final String mobile = SharedPreferenceManager.getInstance(this).getUserMobileNumber();

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_CUSTOMER_LIST,
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

                                customerListInfoList.add(new CustomerListInfo(
                                        jsonObject.getString("userid"),
                                        jsonObject.getString("customername"),
                                        jsonObject.getString("customermobile"),
                                        jsonObject.getString("usergave"),
                                        jsonObject.getString("usergot")
                                        )
                                );


                                youGave = youGave + jsonObject.getInt("usergave");
                                youGot = youGot + jsonObject.getInt("usergot");

                                Log.d("goooooooooddddooo", "onResponse1: "+youGave);

                                Log.d("goooooooooddddooo", "onResponse2: "+youGot);
                            }

                            total = youGave + youGot;
                            int amount = 0;
                            String state = "";

                            if(youGave > youGot)
                            {
                                amount = youGave - youGot;
                                state = "r";
                            }
                            else if(youGave < youGot)
                            {
                                amount = youGot - youGave;
                                state = "g";
                            }
                            else
                            {
                                amount = youGave - youGot;
                                state = "o";
                            }

                            String balance = String.valueOf(amount);

                            SharedPreferenceManager.getInstance(getApplicationContext())
                                    .userBalance(balance,state);


                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();

                            for(int x=0;x<customerListInfoList.size();x++)
                            {
                                if(!list.contains(customerListInfoList.get(x)))
                                {
                                    for(int y=x+1;y<customerListInfoList.size()-1;y++)
                                    {

                                        if((customerListInfoList.get(y).equals(customerListInfoList.get(x))))
                                        {
//                                            System.out.print("repeating "+ar.get(x));
                                            Log.d("listeijfi", "onResponse: "+customerListInfoList.get(x));
                                            list.add(customerListInfoList.get(x));
                                            break;

                                        }


                                    }

                                }

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

                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_page_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.item1:
                Log.d("sisssssssssssss", "onOptionsItemSelected: "+customerListInfoList.size());
                SharedPreferenceManager.getInstance(getApplicationContext())
                        .customerCount(customerListInfoList.size());
                startActivity(new Intent(HomePageActivity.this, SettingsActivity.class));
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(HomePageActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(true);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app will close
//                                finish();
                                moveTaskToBack(true);
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    public void loadLocale()
    {
        String userLanguageCode = SharedPreferenceManager.getInstance(this).getUserLanguageCode();
        setLocale(userLanguageCode);
    }

    private void setLocale(String lang)
    {
        Locale locale = new Locale(lang);

        Locale.setDefault(locale);

        Configuration configuration = new Configuration();

        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(
                configuration,
                getBaseContext().getResources().getDisplayMetrics()
        );

    }
}
