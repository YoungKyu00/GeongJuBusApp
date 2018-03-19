package com.fronttooth.geongjubusapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Young on 2017-08-07.
 */

public class BusLineListAdapter extends RecyclerView.Adapter<BusLineListAdapter.ViewHolder> {

    private ArrayList<BusLineListVO> buslinelist = new ArrayList<BusLineListVO>();
    private Context context;
    private boolean run_dir = false;
    private int po = 1000;


    public BusLineListAdapter (Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_buslinelist,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mNameText.setText(buslinelist.get(position).getBstop_name());
        holder.mIdText.setText(buslinelist.get(position).getSbstop_id());
        if(position == 0){holder.mImageView.setImageResource(R.drawable.line_s);
        }else if(getItemCount()-1 == position){holder.mImageView.setImageResource(R.drawable.line_e);

        }else {holder.mImageView.setImageResource(R.drawable.line_m);}

        if(run_dir==false) {
            if (buslinelist.get(position).getRud_dir().equals("1")) {
                run_dir = true;
                po = position;
                holder.mImageView.setImageResource(R.drawable.line_dir);
            }
        }
        if(po==position){
            holder.mImageView.setImageResource(R.drawable.line_dir);
        }
        holder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BusstopDetailActivity.class);
                i.putExtra("bstop",fetchBsstop(buslinelist.get(position).getBstop_name()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buslinelist.size();
    }


    public void add(BusLineListVO list) {
        buslinelist.add(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mNameText;
         private TextView mIdText;
        private ImageView mImageView;
        private CardView cv;


        public ViewHolder(View view){
            super(view);
            mNameText = (TextView)view.findViewById(R.id.bstop_name);
            mIdText = (TextView)view.findViewById(R.id.bstop_id);
            mImageView=(ImageView)view.findViewById(R.id.imageView);
            cv = (CardView)view.findViewById(R.id.cv);
        }
    }

    public BstopVO fetchBsstop(String mKey){

        BstopVO bstop = new BstopVO();
        try {
            ArrayList<BstopVO> bstopArray;
            Json json = new Json();
            CustomTask task = new CustomTask();
            String result = task.execute(mKey,"bstop").get();
            bstopArray = json.getJsonBsstop(result);
            if (mKey.length() > 0){
                bstop = bstopArray.get(0);
            }
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}
        return bstop;
    }


}

