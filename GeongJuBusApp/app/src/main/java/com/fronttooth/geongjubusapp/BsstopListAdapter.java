package com.fronttooth.geongjubusapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Young on 2017-05-31.
 */

public class BsstopListAdapter extends BaseAdapter {

    private ArrayList<BstopVO> bstopList = new ArrayList<BstopVO>();

    public BsstopListAdapter() {
    }

    @Override
    public int getCount() {
        return bstopList.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return bstopList.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_bstop, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.bstop_name);
        TextView idView = (TextView) convertView.findViewById(R.id.bstop_id);

        BstopVO bstopvo = bstopList.get(position);

        nameView.setText(bstopvo.getBstop_name());
        idView.setText(bstopvo.getSbstop_id());

        return convertView;
    }

    public void addItem(BstopVO list) {
        BstopVO bstop = new BstopVO();

        bstop.setBstop_name(list.getBstop_name());
        bstop.setBstop_id(list.getBstop_id());
        bstop.setSbstop_id(list.getSbstop_id());
        bstop.setBstop_routes(list.getBstop_routes());
        bstop.setGpsx(list.getGpsx());
        bstop.setGpsy(list.getGpsy());
        bstopList.add(bstop);

    }

    public void clear() {
        bstopList.clear();
    }

    public BstopVO getList(int position){
        return bstopList.get(position);
    }


}
