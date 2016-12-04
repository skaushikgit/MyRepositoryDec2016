package com.example.saurabhkaushik.skeletonapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saurabhkaushik.skeletonapplication.Models.CaseStudyModel;
import com.example.saurabhkaushik.skeletonapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabhkaushik on 03/12/16.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.CustomeViewHolder> {
    List<CaseStudyModel> caseList = null;

    public MyRecycleViewAdapter(List<CaseStudyModel> list) {
        caseList = new ArrayList<>();
        this.caseList = list;
    }

    class CustomeViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        public CustomeViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.myRecyclerViewRowId);
        }
    }

    @Override
    public CustomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        CustomeViewHolder viewHolder = new CustomeViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomeViewHolder holder, int position) {
        if(caseList != null) {
            holder.textView.setText((caseList.get(position).getName()+""));
        }
    }


    @Override
    public int getItemCount() {
        return caseList.size();
    }
}
