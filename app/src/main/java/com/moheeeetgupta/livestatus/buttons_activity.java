package com.moheeeetgupta.livestatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class buttons_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_buttons_activity);
    }
    public void Map(View view){
        startActivity (new Intent (getApplicationContext (),LiveActvity.class));
    }
    public void Tracker(View view){
        startActivity (new Intent (getApplicationContext (),MapsActivity.class));
    }
}
