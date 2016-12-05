package com.example.saurabhkaushik.skeletonapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.saurabhkaushik.skeletonapplication.Fragments.HomeFragment;
import com.example.saurabhkaushik.skeletonapplication.Models.CaseStudyModel;
import com.example.saurabhkaushik.skeletonapplication.R;

public class HomeActivity extends FragmentActivity implements HomeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayoutId, HomeFragment.newInstance("First", "Second"), "rageComicList")
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(CaseStudyModel caseStudyModel) {
        Toast.makeText(this, "Activity Data from Fragment: \n"+ caseStudyModel.getName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WebViewAcitivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("icon", caseStudyModel.getIcon());
        bundle.putString("name", caseStudyModel.getName());
        bundle.putString("url", caseStudyModel.getUrl());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
