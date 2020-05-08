package com.moheeeetgupta.livestatus;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private  int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);
        Objects.requireNonNull (getSupportActionBar ()).setTitle("Details of "+affectedcountries.countrymodelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);

        tvCountry.setText(affectedcountries.countrymodelList.get(positionCountry).getCountry());
        tvCases.setText(affectedcountries.countrymodelList.get(positionCountry).getCases ());
        tvRecovered.setText(affectedcountries.countrymodelList.get(positionCountry).getRecovered ());
        tvCritical.setText(affectedcountries.countrymodelList.get(positionCountry).getCritical ());
        tvActive.setText(affectedcountries.countrymodelList.get(positionCountry).getActive ());
        tvTodayCases.setText(affectedcountries.countrymodelList.get(positionCountry).getTodayCases ());
        tvTotalDeaths.setText(affectedcountries.countrymodelList.get(positionCountry).getDeaths ());
        tvTodayDeaths.setText(affectedcountries.countrymodelList.get(positionCountry).getTodayDeaths ());




    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}