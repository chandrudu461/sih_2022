package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MilestoneFurtherActivity extends AppCompatActivity {
    MilestonePostModel milestonePostModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_further);

        milestonePostModel = (MilestonePostModel) getIntent().getSerializableExtra("presentMilestone");

        Bundle bundle = new Bundle();
        bundle.putSerializable("presentMilestone",milestonePostModel);
        MilestoneFurther fragobj = new MilestoneFurther();
        fragobj.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragCont,fragobj).commit();

    }
}