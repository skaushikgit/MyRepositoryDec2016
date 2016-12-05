package com.example.saurabhkaushik.skeletonapplication.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.saurabhkaushik.skeletonapplication.Fragments.WebViewFragment;
import com.example.saurabhkaushik.skeletonapplication.R;

public class WebViewAcitivity extends AppCompatActivity implements WebViewFragment.OnFragmentInteractionListener{
    Context context;
    WebView webView;
    String url = null;
    String icon = "https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_acitivity);


        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        url = bundle.getString("url");
//        icon = bundle.getString("icon");

        WebViewFragment webViewFragment = WebViewFragment.newInstance(icon, url);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.topFragmentContainerId, webViewFragment)
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
