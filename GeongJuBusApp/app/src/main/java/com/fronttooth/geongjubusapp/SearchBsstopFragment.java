package com.fronttooth.geongjubusapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Young on 2017-06-13.
 */

public class SearchBsstopFragment extends ListFragment {

    BsstopListAdapter adapter;
    String result;
    String mkey;
    String type = "bstop";

    public static SearchBsstopFragment newInstance(String key){
        Bundle args = new Bundle();
        args.putString("BUSSTOPKEY",key);
        SearchBsstopFragment fragment = new SearchBsstopFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstaceState){

        adapter = new BsstopListAdapter();
        setListAdapter(adapter);
        mkey = getArguments().getString("BUSSTOPKEY");
        return super.onCreateView(inflater, container, savedInstaceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("아이템 :",""+adapter.getItem(position));
        BstopVO vo;
        vo = adapter.getList(position);
        Intent intent = new Intent(getActivity(), BusstopDetailActivity.class);
        intent.putExtra("bstop", vo);
        startActivity(intent);



        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        fetchList();
        adapter.notifyDataSetChanged();

    }

    public void addItem(BstopVO list) {
        adapter.addItem(list) ;
    }

    public void fetchList(){
        try {
            ArrayList<BstopVO> bsstopArray;
            Json json = new Json();
            CustomTask task = new CustomTask();
            result = task.execute(mkey,type).get();
            bsstopArray = json.getJsonBsstop(result);
            if (mkey.length() > 0){
                for (int i = 0; i < bsstopArray.size(); i++) {
                    adapter.addItem(bsstopArray.get(i));
                }
            }
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}
    }

    public void updateKey(String key){
        this.mkey = key;
    }

}
