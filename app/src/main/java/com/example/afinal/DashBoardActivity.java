package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DashBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FrameLayout frameLayout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.sharedPreferences = getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);


        setContentView(R.layout.activity_dashboard);
        toolbar=findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        navigationView=findViewById(R.id.navigation_layout);
        drawerLayout=findViewById(R.id.drawer_layout);
        frameLayout=findViewById(R.id.frame_layout_dashboard);

        onCreateOptionsMenu(navigationView.getMenu());

        setUpToolbar();
        actionBarDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AboutUsFragment()).commit();
        // fragmentHeading.setText("All Events")

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // we need redirect the respective menu according to the user

        if(sharedPreferences.getString("userType","user") == "Admin"){
            inflater.inflate(R.menu.navigation_menu_admin,menu);
        }
        if(sharedPreferences.getString("userType","user") == "HEI"){
            inflater.inflate(R.menu.navigation_menu_hei, menu);
        }
        if(sharedPreferences.getString("userType","user") == "FundingAgency"){
            inflater.inflate(R.menu.navigation_menu_funding_agency, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.menuitem_managePayments){

            // Payment Verify fragment Admin to be replaced
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AboutUsFragment()).commit();

        }

        if(item.getItemId() == R.id.menuitem_verify_users){

            //  verify user Fragment
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AboutUsFragment()).commit();
            startActivity(new Intent(DashBoardActivity.this,AdminYesOrNo.class));
        }


        return super.onOptionsItemSelected(item);
    }


    //toolbar to actionbar
    public void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setTitle("title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}


