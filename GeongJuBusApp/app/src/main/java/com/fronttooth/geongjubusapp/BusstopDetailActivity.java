package com.fronttooth.geongjubusapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Young on 2017-06-13.
 */

public class BusstopDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;
    private boolean mIsTheTitleVisible          = false;

    private Toolbar mToolbar;
    private TextView mBusstopTitleName;
    private TextView mBusstopName;
    private TextView mSbstop_id;
    private AppBarLayout mAppbar;
    private Menu menu;
    private Button mMapButton;
    private RecyclerView mRecyclerView;
    private RemainBusAdapter mRemainBusAdapter;
    private LinearLayoutManager mLayoutManager;
    private static BstopVO bstop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_busstopdetail);


        Intent intent = getIntent();
         bstop = intent.getParcelableExtra("bstop");

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRemainBusAdapter = new RemainBusAdapter(this);
        mRecyclerView.setAdapter(mRemainBusAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppbar = (AppBarLayout) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mAppbar.addOnOffsetChangedListener(this);
        mBusstopTitleName = (TextView)findViewById(R.id.busstopTitleName);
        mSbstop_id =(TextView)findViewById(R.id.sbsstop_id);
        mBusstopName=(TextView)findViewById(R.id.busstopName);
        mBusstopTitleName.setText(bstop.getBstop_name());
        mSbstop_id.setText(bstop.getSbstop_id());
        mBusstopName.setText(bstop.getBstop_name());
        mMapButton = (Button)findViewById(R.id.map_button);
        mMapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(BusstopDetailActivity.this, BstopMapViewActivity.class);
                intent.putExtra("bstop", bstop);
                startActivity(intent);
            }
        });
        setSupportActionBar(mToolbar);

        fetchLineList(bstop);

        startAlphaAnimation(mBusstopTitleName, 0, View.INVISIBLE);
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
            Intent intent = new Intent(BusstopDetailActivity.this, BstopMapViewActivity.class);
            intent.putExtra("bstop", bstop);
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
                startAlphaAnimation(mBusstopTitleName, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mBusstopTitleName, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }


    private void fetchLineList(BstopVO bstop){
        Json mjson;
        ArrayList<ArrivalBusVO> mRemainTimeBusArray;
        String mResult;
        try {
            mjson = new Json();
            CustomTask task = new CustomTask();
            mResult = task.execute(bstop.getBstop_id(),"realtime").get();
            mRemainTimeBusArray = mjson.getJsonRealtime(mResult);
            if (bstop.getBstop_id().length() > 0){
                for (int i = 0; i < mRemainTimeBusArray.size(); i++) {
                    mRemainBusAdapter.add(mRemainTimeBusArray.get(i));
                }
            }
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}
    }

}
