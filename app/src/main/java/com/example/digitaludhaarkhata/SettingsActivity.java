package com.example.digitaludhaarkhata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity
{

    private TextView textViewShopName;
    private TextView textViewMobileNumber;
    private TextView textViewCustomerCount;
    private TextView textViewBalance;
    private TextView textViewLanguage;

    private LinearLayout linearLayoutChangeLanguage;
    private LinearLayout linearLayoutSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().hide();

        textViewShopName = findViewById(R.id.idTextViewShopNameSettingsActivity);
        textViewMobileNumber = findViewById(R.id.idTextViewMobileNumberSettingsActivity);
        textViewCustomerCount = findViewById(R.id.idTextViewCustomerCountSettingsActivity);
        textViewBalance = findViewById(R.id.idTextViewBalanceSettingsActivity);
        textViewLanguage = findViewById(R.id.idTextViewLanguageSettingsActivity);

        linearLayoutChangeLanguage = findViewById(R.id.idLinearLayoutChangeLanguageSettingsActivity);
        linearLayoutSignOut = findViewById(R.id.idLinearLayoutSignOutSettingsActivity);

        String userMobileNumber = SharedPreferenceManager.getInstance(this).getUserMobileNumber();
        String userShopName = SharedPreferenceManager.getInstance(this).getUserShopName();
        String userLanguage = SharedPreferenceManager.getInstance(this).getUserLanguage();
        int count = SharedPreferenceManager.getInstance(this).getUserCustomerCount();

        textViewShopName.setText(userShopName);
        textViewMobileNumber.setText(userMobileNumber);
        textViewLanguage.setText(userLanguage);
        textViewCustomerCount.setText(""+count);

        linearLayoutChangeLanguage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SettingsActivity.this, SelectNewLanguageActivity.class));
//                finish();
            }
        });

        linearLayoutSignOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signOutUser();
            }
        });

        String balance = SharedPreferenceManager.getInstance(this).getUserBalance();
        String color = SharedPreferenceManager.getInstance(this).getUserBalanceColor();

        if(color.equals("g"))
        {
            textViewBalance.setTextColor(getResources().getColor(R.color.colorOfGreen));
        }
        else if(color.equals("r"))
        {
            textViewBalance.setTextColor(getResources().getColor(R.color.colorOfRed));
        }
        else
        {
            textViewBalance.setTextColor(getResources().getColor(R.color.colorOfOrange));
        }

        textViewBalance.setText(balance);
    }


    private void signOutUser()
    {
// Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(SettingsActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Are you sure to Logout ?");

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
                                SharedPreferenceManager.getInstance(getApplicationContext()).logout();
                                startActivity(new Intent(getApplicationContext(), LanguageSelectActivity.class));
                                finish();
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
}
