package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageSelectActivity extends AppCompatActivity
{

    MaterialCardView card;

    //adapter
    private LanguageSelectAdapter adapter;

    //a list to store all the prs users name
    List<LanguageSelectInfo> languageSelectInfoList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(!isConnected())
        {

            setContentView(R.layout.no_internet);
            getSupportActionBar().hide();
            alertMessage();
            return;

        }



        if(SharedPreferenceManager.getInstance(this).isLoggedIn())
        {
            startActivity(new Intent(this, HomePageActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_language_select);

        getSupportActionBar().hide();

        languageSelectInfoList = new ArrayList<>();

        recyclerView = findViewById(R.id.idRecycleViewLanguageSelect);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LanguageSelectAdapter(languageSelectInfoList);

        languageSelectInfoList.add(new LanguageSelectInfo("G", "English"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "हिंदी"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "मराठी"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "বাংলা"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "తెలుగు"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "தமிழ்"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "ગુજરાતી"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "اردو"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "ಕನ್ನಡ"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "ଓଡିଆ"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "മലയാളം"));
        languageSelectInfoList.add(new LanguageSelectInfo("G", "ਪੰਜਾਬੀ"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LanguageSelectAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                Toast.makeText(getApplicationContext(),"dd"+position,Toast.LENGTH_LONG).show();

//                TextView T1 = recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.)
                showSelectedLanguage(position);
                showAlertBoxLanguageSelection(position);
            }
        });

//        card = findViewById(R.id.cardview);
//
//
//
//        card.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                //cardView.setChecked(!cardView.isChecked());
//                card.toggle();
//
////                card.setChecked(!card.isChecked());
//            }
//        });

    }

    private void showSelectedLanguage(int position)
    {
        if(position == 0)
        {
            setLocale("en", "English");
        }
        else if(position == 1)
        {
            setLocale("hi", "हिंदी");
        }
        else if(position == 2)
        {
            setLocale("mr", "मराठी");
        }
        else if (position == 3)
        {
            setLocale("bn", "বাংলা");
        }
        else if (position == 4)
        {
            setLocale("te", "తెలుగు");
        }
        else if (position == 5)
        {
            setLocale("ta" ,"தமிழ்");
        }
        else if (position == 6)
        {
            setLocale("gu", "ગુજરાતી");
        }
        else if (position == 7)
        {
            setLocale("ur", "اردو");
        }
        else if (position == 8)
        {
            setLocale("kn", "ಕನ್ನಡ");
        }
        else if (position == 9)
        {
            setLocale("or", "ଓଡିଆ");
        }
        else if (position == 10)
        {
            setLocale("ml", "മലയാളം");
        }
        else if (position == 11)
        {
            setLocale("pa", "ਪੰਜਾਬੀ");
        }
    }

    private void setLocale(String lang, String language)
    {
        Locale locale = new Locale(lang);

        Locale.setDefault(locale);

        Configuration configuration = new Configuration();

        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(
                configuration,
                getBaseContext().getResources().getDisplayMetrics()
        );

        //Save data to shared preference

        SharedPreferenceManager.getInstance(getApplicationContext())
                .userLanguageCode(lang);

        SharedPreferenceManager.getInstance(getApplicationContext())
                .userLanguage(language);
    }

//    public void loadLocale()
//    {
//        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//        String language = sharedPreferences.getString("myLanguage","");
//
//        setLocale(language);
//    }

    private void showAlertBoxLanguageSelection(int position)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(LanguageSelectActivity.this);
        View view = getLayoutInflater().inflate(R.layout.custom_alert_dialouge_box_language_selection,null);

        ImageButton buttonClose = view.findViewById(R.id.idImageButtonCloseCustomeAlertBoxLanguageSelection);
        Button buttonContinue = view.findViewById(R.id.idButtonContinueCustomeAlertBoxLanguageSelection);

        alert.setView(view);

        final AlertDialog alertDialog = alert.create();

        alertDialog.setCanceledOnTouchOutside(false);

        buttonClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();
                startActivity(new Intent(LanguageSelectActivity.this, MainActivity.class));
            }
        });

        alertDialog.show();
    }


    public boolean isConnected()
    {
        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            //we are connected to a network
            connected = true;
        }
        else
        {
            connected = false;
        }

        return connected;
    }


    public void alertMessage()
    {

        // Create the object of
        // AlertDialog Builder class
        android.app.AlertDialog.Builder builder
                = new android.app.AlertDialog
                .Builder(LanguageSelectActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Please connect with to working internet connection");

        // Set Alert Title
        builder.setTitle("Network Error!");

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
                                startActivity(getIntent());
                            }
                        });


        // Create the Alert dialog
        android.app.AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
}
