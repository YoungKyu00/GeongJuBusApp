package com.fronttooth.geongjubusapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Young on 2017-07-29.
 */

public class BusDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;
    private boolean mIsTheTitleVisible          = false;

    private Toolbar mToolbar;
    private TextView mBusTitleName;
    private TextView mBusName;
    private TextView mSbstop_id;
    private AppBarLayout mAppbar;
    private Menu menu;
    private Button mMapButton;
    private RecyclerView recyclerView;
    private BusLineListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static BusLineVO bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_busdetail);

        Intent intent = getIntent();
        bus = intent.getParcelableExtra("bus");

        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BusLineListAdapter(this);
        recyclerView.setAdapter(mAdapter);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppbar = (AppBarLayout) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mAppbar.addOnOffsetChangedListener(this);
        mBusTitleName = (TextView)findViewById(R.id.busTitleName);
        mSbstop_id =(TextView)findViewById(R.id.bus_id);
        mBusName=(TextView)findViewById(R.id.busName);
        mBusTitleName.setText(bus.getRoute_name());
        mSbstop_id.setText(bus.getEnd_name()+" 방면");
        mBusName.setText(bus.getRoute_name());
        mMapButton = (Button)findViewById(R.id.map_button);
        mMapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(BusDetailActivity.this, BusMapViewActivity.class);
                intent.putExtra("bus", bus);
                startActivity(intent);
            }
        });
        setSupportActionBar(mToolbar);

        fetchLineList(bus);
        startAlphaAnimation(mBusTitleName, 0, View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is presentgetMenuInflater().inflate(R.menu.menu_scrolling, menu);
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    public void showOverflowMenu(boolean showMenu){
        if(menu == null)
            return;
        menu.setGroupVisible(R.id.menu_group, showMenu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            Intent intent = new Intent(BusDetailActivity.this, BusMapViewActivity.class);
            intent.putExtra("bus", bus);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        // handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
        showOverflowMenu(mIsTheTitleVisible );

    }
    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mBusTitleName, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mBusTitleName, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void fetchLineList(BusLineVO bus){
        ArrayList<BusLineListVO> mBusLineListVO;
        String mResult;
        Json json;
        try {
            json = new Json();
            CustomTask task = new CustomTask();
            mResult = task.execute(bus.getRoute_id(),"list").get();
            mBusLineListVO = json.getJsonBusLine(mResult);
            Log.i("asdaas",""+mResult);
            if (bus.getRoute_id().length() > 0){
                for (int i = 0; i < mBusLineListVO.size(); i++) {
                    mAdapter.add(mBusLineListVO.get(i));
                }
            }
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}
    }
}

