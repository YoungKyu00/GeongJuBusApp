package com.fronttooth.geongjubusapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Young on 2017-08-16.
 */

public class RemainBusAdapter extends RecyclerView.Adapter<RemainBusAdapter.ViewHolder> {

    private ArrayList<ArrivalBusVO> mRemainTimeBusArray = new ArrayList<ArrivalBusVO>();
    private Context context;

    public RemainBusAdapter(Context context){
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bstop_bus,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.busName.setText(mRemainTimeBusArray.get(position).getRouteName());
        holder.remainTime.setText(mRemainTimeBusArray.get(position).getRemainTime());
        if(!mRemainTimeBusArray.get(position).getRemainBstop().equals("null")) {
            holder.remainBus.setText(mRemainTimeBusArray.get(position).getRemainBstop());
        }
        holder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BusDetailActivity.class);
                i.putExtra("bus",fetchBus(mRemainTimeBusArray.get(position).getRouteName()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRemainTimeBusArray.size();
    }

    public void add(ArrivalBusVO list) {
        mRemainTimeBusArray.add(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView busName;
        public TextView remainTime;
        public TextView remainBus;
        public CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            busName = (TextView)itemView.findViewById(R.id.busName);
            remainTime= (TextView)itemView.findViewById(R.id.remainTime);
            remainBus = (TextView)itemView.findViewById(R.id.remainBus);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }

    }

    public BusLineVO fetchBus(String mKey){

        BusLineVO bus = new BusLineVO();
        try {
            ArrayList<BusLineVO> busArray;
            Json json = new Json();
            CustomTask task = new CustomTask();
            String result = task.execute(mKey,"bus").get();
            busArray = json.getJsonBus(result);
            if (mKey.length() > 0){
                bus = busArray.get(0);
            }
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}
        return bus;
    }
}
