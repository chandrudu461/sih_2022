package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

public class ProfileActivity extends AppCompatActivity {

    private Spinner spinner;
    private FrameLayout frameLayout;

    SharedPreferences sharedPreferences;

    String typeOfUser[] = { "HEI" , "Funding Agency", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        this.sharedPreferences= getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);


        spinner = findViewById(R.id.spinner);
        frameLayout = findViewById(R.id.FragContainer);

        ArrayAdapter adapter
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.fragments));
        adapter.setDropDownViewResource( android.R.layout
                .simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch(i){
                case 0:
                    sharedPreferences.edit().putString("userType","HEI");
                    setFragment(new Hei());
                    break;
                case 1:
                    sharedPreferences.edit().putString("userType","FundingAgency");
                    setFragment(new FundingAgency());
                    break;
                case 2:
                    sharedPreferences.edit().putString("userType","Admin");
                    setFragment(new Admin());
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
}

    private void setFragment(Fragment fragment) {FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FragContainer,fragment);
        fragmentTransaction.commit();
    }
}