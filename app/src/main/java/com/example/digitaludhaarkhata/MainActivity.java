package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity
{

    private TextView textViewDigital;
    private TextView textViewUdhaar;
    private TextView textViewKhaata;
    private MaterialButton materialButtonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        textViewDigital = findViewById(R.id.idTextViewDigitalMainActivity);
        textViewUdhaar = findViewById(R.id.idTextViewUdhaarMainActivity);
        textViewKhaata = findViewById(R.id.idTextViewKhaataMainActivity);
        materialButtonNext = findViewById(R.id.idButtonNextImageMainActivity);

//        textViewDigital.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                textViewDigital.setVisibility(View.VISIBLE);
//            }
//        },700);
//
//        textViewUdhaar.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                textViewUdhaar.setVisibility(View.VISIBLE);
//            }
//        },1400);
//
//        textViewKhaata.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                textViewKhaata.setVisibility(View.VISIBLE);
//            }
//        },2100);
//
//        imageViewNext.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//
//                imageViewNext.setVisibility(View.VISIBLE);
//
//            }
//        }, 3000);


//
//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                                            0.0f,
//                                            100.0f,
//                                            0.0f,
//                                            0.0f
//                                    );

//        translateAnimation.setDuration(1000);
//        translateAnimation.setRepeatCount(10);
//        translateAnimation.setRepeatMode(2);
//        imageViewNext.startAnimation(translateAnimation);

//        Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
//        animation.setDuration(2000); //1 second duration for each animation cycle
//        animation.setInterpolator(new LinearInterpolator());
//        animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
//        animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
//        imageButton.startAnimation(animation); //to start animation
//
        materialButtonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, AccountVerificationActivity.class));
            }
        });
    }
}
