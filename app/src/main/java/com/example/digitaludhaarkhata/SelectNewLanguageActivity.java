package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectNewLanguageActivity extends AppCompatActivity
{
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
        setContentView(R.layout.activity_select_new_language);

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
                showSelectedLanguage(position);
                showAlertBoxLanguageSelection(position);
            }
        });
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

    private void showAlertBoxLanguageSelection(int position)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(SelectNewLanguageActivity.this);
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
                startActivity(new Intent(SelectNewLanguageActivity.this, HomePageActivity.class));
                finish();
            }
        });

        alertDialog.show();
    }
}
