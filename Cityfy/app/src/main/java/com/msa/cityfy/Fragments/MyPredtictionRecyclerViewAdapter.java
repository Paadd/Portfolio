package com.msa.cityfy.Fragments;

import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.msa.cityfy.Activities.MainActivity;
import com.msa.cityfy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.msa.cityfy.Activities.PollutionActivity.AQISEV;
import static com.msa.cityfy.Activities.PollutionActivity.airQualityIndex;
import static com.msa.cityfy.Activities.PollutionActivity.button;
import static com.msa.cityfy.Activities.wetestingbois.aqi;


public class MyPredtictionRecyclerViewAdapter extends RecyclerView.Adapter<MyPredtictionRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> mValues;
    private final ArrayList<Integer> aqis;




    public MyPredtictionRecyclerViewAdapter(ArrayList<String> items, ArrayList<Integer> aqi) {
        mValues = items;
        aqis = aqi;




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mContentView.setText(mValues.get(position));

        //TODO MAKE COLOR AND PRETTY IF aqi  IS COLOR (<VAR AND > VAR)

        //Integer boy[]= new Integer[aqis.size()];



            if(aqi.get(position)> 0 && aqi.get(position)<= 50)
            {

                holder.bar.setBackgroundColor(Color.rgb(0,240,0));

                int x=aqi.get(position)*2;
                holder.bar.setLayoutParams(new LinearLayout.LayoutParams(x,20));

            }


        if(aqi.get(position)>= 50 && aqi.get(position)<= 100){
            holder.bar.setBackgroundColor((Color.rgb(245,245,10)));
            int x=aqi.get(position)*2;
            holder.bar.setLayoutParams(new LinearLayout.LayoutParams(x,20));
        }

        if(aqi.get(position)>= 100 && aqi.get(position)<= 150){
        holder.bar.setBackgroundColor(Color.rgb(245,165,0));
            int x=aqi.get(position)*2;
            holder.bar.setLayoutParams(new LinearLayout.LayoutParams(x,20));
    }

        if(aqi.get(position)>= 150 && aqi.get(position)<= 200) {
            holder.bar.setBackgroundColor(Color.rgb(245,0,0));
            int x=aqi.get(position)*2;
            holder.bar.setLayoutParams(new LinearLayout.LayoutParams(x,20));
        }


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final Button bar;



        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            bar = view.findViewById(R.id.bar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}