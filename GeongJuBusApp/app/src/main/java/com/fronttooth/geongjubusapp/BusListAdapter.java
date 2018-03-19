package com.fronttooth.geongjubusapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Young on 2017-07-29.
 */

public class BusListAdapter extends BaseAdapter {

    private ArrayList<BusLineVO> busList = new ArrayList<BusLineVO>();


    public BusListAdapter() {
    }

    @Override
    public int getCount() {
        return busList.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return busList.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_busline, parent, false);
        }

        TextView busName = (TextView) convertView.findViewById(R.id.route_name);
        TextView routeTypeName = (TextView) convertView.findViewById(R.id.route_type_name);

        BusLineVO busvo = busList.get(position);
        busName.setText(busvo.getRoute_name());
        routeTypeName.setText(busvo.getEnd_name());

        return convertView;
    }

    public void addItem(BusLineVO list) {
        BusLineVO bus = new BusLineVO();

        bus.setRoute_id(list.getRoute_id());
        bus.setRoute_name(list.getRoute_name());
        bus.setEnd_name(list.getEnd_name());
        bus.setRoute_type(list.getRoute_type());
        bus.setRoute_type_name(list.getRoute_type_name());

        busList.add(bus);
    }

    public void clear() {
        busList.clear();
    }

    public BusLineVO getList(int position){
        return busList.get(position);
    }


}
