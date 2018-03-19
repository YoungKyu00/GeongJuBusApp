package com.fronttooth.geongjubusapp;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Young on 2017-06-13.
 */

public class SearchBusFragment extends ListFragment {


    BusListAdapter adapter;
    String result;
    String mkey;

    public static SearchBusFragment newInstance(String key){
        Bundle args = new Bundle();
        args.putString("BUSLINEKEY",key);
        SearchBusFragment fragment = new SearchBusFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstaceState){

        adapter = new BusListAdapter();
        setListAdapter(adapter);
        mkey = getArguments().getString("BUSLINEKEY");
        return super.onCreateView(inflater, container, savedInstaceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("아이템 :",""+adapter.getItem(position));
        BusLineVO vo = adapter.getList(position);
        Intent intent = new Intent(getActivity(), BusDetailActivity.class);
        intent.putExtra("bus", vo);
        startActivity(intent);

        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        adapter.notifyDataSetChanged();
        fetchList();

    }

    public void addItem(BusLineVO list) {
        adapter.addItem(list) ;
    }

    public void fetchList(){
        Json json = new Json();
        ArrayList<BusLineVO> busArray;
        try {
            CustomTask task = new CustomTask();
            result = task.execute(mkey,"bus").get();
            Log.i("busname",result);
            busArray = json.getJsonBus(result);
            if (mkey.length() > 0){
                for (int i = 0; i < busArray.size(); i++) {
                    adapter.addItem(busArray.get(i));
                    Log.i("dda",busArray.get(i).getRoute_name());

                }
            }
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}
    }

    public void updateKey(String key){
        this.mkey = key;
    }


}
