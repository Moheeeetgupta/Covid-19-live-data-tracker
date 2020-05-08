package com.moheeeetgupta.livestatus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.actvity_main);
        getSupportActionBar ().hide ();
        lottieAnimationView = findViewById (R.id.lottie_layer_name);


        lottieAnimationView.addAnimatorListener (new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd(Animator animation) {

                startActivity (new Intent (MainActivity.this, buttons_activity.class));
                finish ();
            }
        });
    }
}
