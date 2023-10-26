package com.example.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    String user;
    FrameLayout frameLayout;
    FundingAgencyPostModel fundingAgencyPostModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.sharedPreferences = getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);

        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.frame_layout_dashboard);

        user = sharedPreferences.getString("userType","default");



        fundingAgencyPostModel = (FundingAgencyPostModel) getIntent().getSerializableExtra("user");


//        onCreateOptionsMenu(navigationView.getMenu());

        setUpToolbar();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        onCreateOptionsMenu(navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AboutUsFragment()).commit();
        // fragmentHeading.setText("All Events")

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuitem_verify_users:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AdminRetrieval()).commit();
                        return true;
                    case R.id.menuitem_managePayments:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AboutUsFragment()).commit();
                        return true;
                    case R.id.menuitem_upload_fund:
                        Fragment fragment = FundingAgencyPost.newInstance(fundingAgencyPostModel);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, fragment).commit();
                        return true;
                    case R.id.menuitem_problem_feed:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new ViewPSHei()).commit();
                        return true;
                    case R.id.menuitem_proposed_fund:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new FundingAgencyUploadedPS()).commit();
                        return true;
                    case R.id.menuitem_proposed_hei:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new ProposalValidation()).commit();
                        return true;
                    case R.id.menuitem_bank_fund:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new BankDetails()).commit();
                        return true;
                    case R.id.menuitem_bank_hei:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new BankDetails()).commit();
                        return true;
                    case R.id.menuitem_aboutUs:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new AboutUsFragment()).commit();
                        return true;

                    case R.id.menuitem_myProfile:
                        if(user.equals("FundingAgency")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new FundingAgencyProfile()).commit();

                        }
                        if(user.equals("HEI")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new HeiProfile()).commit();
                        }
                        return true;


                    default:
                        drawerLayout.openDrawer(GravityCompat.START);
                        return true;

                }
            }
        });
    }


//    navigationView.setNavigationItemSelectedListener {
//        when(it.itemId){
//            R.id.menuitem_allevents -> {
//
//                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,AllEvents_fragment()).commit()
//
//                //navigationView.menu.findItem(R.id.menuitem_allevents)
//                drawerLayout.closeDrawers()
//                fragmentHeading.setText("All Events")
//            }
//            R.id.menuitem_registered_events -> {
//                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,RegisteredEvents_Fragment()).commit()
//
//                drawerLayout.closeDrawers()
//                fragmentHeading.setText("Registered Events")
//            }
//            R.id.menuitem_attended_events ->{
//                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,AttendedEvents_fragment()).commit()
//                drawerLayout.closeDrawers()
//                fragmentHeading.setText("Attended Events")
//            }
//            R.id.UploadEvent -> {
//                val intent = Intent(this, upload_an_event::class.java)
//                startActivity(intent)
//            }
//            R.id.sign_out->{
//                Firebase.auth.signOut()
//                //val intent=Intent(this,googleAuth::class.java)
//                //startActivity(intent)
//                finish()
//            }
//        }
//        return@setNavigationItemSelectedListener true
//    }
//
//}

    //toolbar hamburger listener
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId==android.R.id.home){
//        drawerLayout.openDrawer(GravityCompat.START)
////
//        //name and rollno in navigation drawer
//
//        if(intent!=null){
//
//
//        textView_rollno= findViewById(R.id.textview_rollno)
//        textView_username= drawerLayout.findViewById(R.id.textview_name)
//
//        textView_rollno.setText("Gmail - ${sharedPreferences.getString("email",null)}")
//        textView_username.setText("Name - ${sharedPreferences.getString("name",null)}")
//
//
//        }
//
//
//        }
//
//        return super.onOptionsItemSelected(item)
//        }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (navigationView != null) {
//            navigationView.getMenu().clear(); //clear old inflated items.
//            navigationView.inflateMenu(R.menu.navigation_menu_default);
//
//            // we need redirect the respective menu according to the user
//            String user = sharedPreferences.getString("userType", "HEI");
//            if (user == "Admin") {
////            inflater.inflate(R.menu.navigation_menu_admin,menu);
//                navigationView.inflateMenu(R.menu.navigation_menu_admin);
//            }
//            if (user == "HEI") {
////            inflater.inflate(R.menu.navigation_menu_hei, menu);
//                navigationView.inflateMenu(R.menu.navigation_menu_hei);
//            }
//            if (user == "FundingAgency") {
////            inflater.inflate(R.menu.navigation_menu_funding_agency, menu);
//                navigationView.inflateMenu(R.menu.navigation_menu_funding_agency);
//            }
//            Toast.makeText(this, user, Toast.LENGTH_SHORT).show();
//        }
//    }
    public boolean onCreateOptionsMenu(NavigationView navigationView) {
//        MenuInflater inflater = getMenuInflater();

        navigationView.getMenu().clear(); //clear old inflated items.
        navigationView.inflateMenu(R.menu.navigation_menu_default);

        // we need redirect the respective menu according to the user
        String user=sharedPreferences.getString("userType","default");
        if(user.equals("Admin")){
//            inflater.inflate(R.menu.navigation_menu_admin,menu);
            navigationView.inflateMenu(R.menu.navigation_menu_admin);
        }
        if(user.equals("HEI")){
//            inflater.inflate(R.menu.navigation_menu_hei, menu);
            navigationView.inflateMenu(R.menu.navigation_menu_hei);
        }
        if(user.equals("FundingAgency")){
//            inflater.inflate(R.menu.navigation_menu_funding_agency, menu);
            navigationView.inflateMenu(R.menu.navigation_menu_funding_agency);
        }
        Toast.makeText(this, user, Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
                                      return true;

        }
//        if(item.getItemId()==android.R.id.home){
//
//        }
//        if(item.getItemId() == R.id.menuitem_managePayments){
//
//            // Payment Verify fragment Admin to be replaced
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AboutUsFragment()).commit();
//
//        }
//
//        if(item.getItemId() == R.id.menuitem_verify_users){
//
//            //  verify user Fragment
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard, new AdminYesOrNo()).commit();
//            //startActivity(new Intent(DashBoardActivity.this,AdminYesOrNo.class));
//        }


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


