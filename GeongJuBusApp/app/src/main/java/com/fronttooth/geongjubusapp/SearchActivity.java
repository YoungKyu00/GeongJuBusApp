package com.fronttooth.geongjubusapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import static android.R.attr.editable;
import static android.R.id.edit;

/**
 * Created by Young on 2017-05-05.
 */

public class SearchActivity extends AppCompatActivity {


    private ViewPager vp;
    private SearchPagerAdapter svp;
    PagerSlidingTabStrip tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        svp = new SearchPagerAdapter(getSupportFragmentManager());

        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(svp);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(vp);

        EditText editTextFilter = (EditText) findViewById(R.id.editText);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Fragment fragment1 = svp.getFragment(0);
                Fragment fragment2 = svp.getFragment(1);
                svp.updateKey(s.toString());
                svp.notifyDataSetChanged();
                if (fragment1 != null && fragment2 != null) {
                    fragment1.onResume();
                    fragment2.onResume();
                }
            }
        });
    }
}
