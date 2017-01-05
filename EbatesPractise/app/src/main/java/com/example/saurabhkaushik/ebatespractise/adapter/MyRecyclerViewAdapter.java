package com.example.saurabhkaushik.ebatespractise.adapter;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saurabhkaushik.ebatespractise.R;
import com.example.saurabhkaushik.ebatespractise.model.Casestudy;
import com.example.saurabhkaushik.ebatespractise.model.CasestudylistModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabhkaushik on 02/12/16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<Casestudy> dataList;

    public MyRecyclerViewAdapter(CasestudylistModel model){
        dataList = model.getCasestudies();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }


    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
