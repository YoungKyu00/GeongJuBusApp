package com.fronttooth.geongjubusapp;

import android.content.Context;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Young on 2017-05-14.
 */

public class SearchPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private FragmentManager mFragmentManager;
    private Map<Integer, String> mFragmentTags;
    String key;

    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentTags = new HashMap<Integer, String>();
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchBusFragment.newInstance(key);
            case 1:
                return SearchBsstopFragment.newInstance(key);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getItemPosition(Object object) {
        if(object instanceof SearchBsstopFragment){
            ((SearchBsstopFragment)object).updateKey(key);
        }else if(object instanceof SearchBusFragment){
            ((SearchBusFragment)object).updateKey(key);
        }
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            mFragmentTags.put(position, tag);
        }
        return object;
    }

    public Fragment getFragment(int position) {
        Fragment fragment = null;
        String tag = mFragmentTags.get(position);
        if (tag != null) {
            fragment = mFragmentManager.findFragmentByTag(tag);
        }
        return fragment;
    }

    public void updateKey(String mkey){
        key = mkey;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "버스";
            case 1:
                return "정류장";
        }
        return null;
    }

}

