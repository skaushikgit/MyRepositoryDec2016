package com.example.saurabhkaushik.ebatespractise.activity;

import android.os.AsyncTask;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.util.Log;
import android.widget.Toast;

import com.example.saurabhkaushik.ebatespractise.Fragments.MainFragment;
import com.example.saurabhkaushik.ebatespractise.R;
import com.example.saurabhkaushik.ebatespractise.Services.JSONParser;
import com.example.saurabhkaushik.ebatespractise.adapter.MyRecyclerViewAdapter;
import com.example.saurabhkaushik.ebatespractise.model.Casestudy;
import com.example.saurabhkaushik.ebatespractise.model.CasestudylistModel;
import com.example.saurabhkaushik.ebatespractise.rest.ApiClient;
import com.example.saurabhkaushik.ebatespractise.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "";
    JSONParser jsonParser = null;
    JSONObject result = null;
    Gson gson;
    ArrayList<Casestudy> casestudylist;
    CasestudylistModel caseStudyListModel;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casestudylist = new ArrayList<>();
        caseStudyListModel = new CasestudylistModel();
        gson = new Gson();
        jsonParser = new JSONParser();
        new MyAsyncTask().execute(ApiClient.BASE_URL);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void parseJsonData(JSONObject jsonObject) {
        try {
            JSONArray arr = jsonObject.getJSONArray("casestudies");
            for (int i=0; i<arr.length(); i++) {
                JSONObject obj = (JSONObject) arr.get(i);
                Casestudy casestudy = new Casestudy();
                casestudy.setName(obj.get("name").toString());
                casestudy.setIcon(obj.get("icon").toString());
                casestudy.setUrl(obj.get("url").toString());
                casestudylist.add(casestudy);
            }
            caseStudyListModel.setCasestudies(casestudylist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject json = null;
            if(params.length > 0) {
                try {
                    json = jsonParser.makeHttpRequest(params[0], "GET");
                    if(json != null) {
                        parseJsonData(json);
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(caseStudyListModel);
            recyclerView.setAdapter(myRecyclerViewAdapter);
        }
    }

}
