package com.example.saurabhkaushik.skeletonapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saurabhkaushik.skeletonapplication.Adapters.MyRecycleViewAdapter;
import com.example.saurabhkaushik.skeletonapplication.Models.CaseStudyListModel;
import com.example.saurabhkaushik.skeletonapplication.Models.CaseStudyModel;
import com.example.saurabhkaushik.skeletonapplication.R;
import com.example.saurabhkaushik.skeletonapplication.Services.HttpHandler;
import com.example.saurabhkaushik.skeletonapplication.Services.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String urlString = "https://api.myjson.com/bins/2ukm9";
    MyRecycleViewAdapter adapter;
    JSONParser jsonParser;
    HttpHandler httpHandler;
    RecyclerView recyclerView;
    CaseStudyListModel caseStudyModelList;
    List<CaseStudyModel> caseList = null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {}


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        jsonParser = new JSONParser();
        httpHandler = new HttpHandler();
        caseStudyModelList = new CaseStudyListModel();
//        caseList = new ArrayList<>();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecycleViewAdapter(caseStudyModelList.getList());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(new MyRecycleViewAdapter.onItemClickListnerz() {
            @Override
            public void onItemClick(CaseStudyModel item) {
                Toast.makeText(getActivity(), item.getName(), Toast.LENGTH_LONG).show();
                if (mListener != null) {
                    mListener.onFragmentInteraction(item);
                }
            }
        });

        new MyAsynctask().execute(urlString);
        return view;
    }

//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(CaseStudyModel caseStudyModel);
    }

    public void parseJSONData(String data) {
        caseList = caseStudyModelList.getList();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("casestudies");
            for (int i=0; i<jsonArray.length(); i++) {
                CaseStudyModel caseStudyModel = new CaseStudyModel();
                JSONObject obj = (JSONObject) jsonArray.get(i);
                caseStudyModel.setName(obj.getString("name"));
                caseStudyModel.setIcon(obj.getString("icon"));
                caseStudyModel.setIcon(obj.getString("url"));
                caseList.add(caseStudyModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyAsynctask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String urlString = "";
            String result = "";
            if (params.length >0 ) {
                urlString = params[0];
                result = jsonParser.makeHttpRequest(urlString);
                //httpHandler not working
//                result = httpHandler.makeServiceCall(urlString);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            parseJSONData(s);
            if (caseList.size() > 0) {
                //To notify adapter for data change
                adapter.notifyDataSetChanged();
                //To refresh recyclerView
                recyclerView.invalidate();
            }
            Log.e("Result", s);
        }
    }
}
