package com.fronttooth.geongjubusapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by Young on 2017-07-27.
 */

public class BstopMapViewActivity extends AppCompatActivity implements MapView.OpenAPIKeyAuthenticationResultListener {


    private MapView mapView;
    private MapPOIItem marker;
    private static final String LOG_TAG = "BstopMapViewActivity";
    private MapPoint point;
    private AppBarLayout actionBar;
    private Toolbar toolbar;
    private TextView busstopName;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);

        Intent intent = getIntent();
        final BstopVO bstop = intent.getParcelableExtra("bstop");

        point = MapPoint.mapPointWithGeoCoord(Double.valueOf(bstop.getGpsx()),Double.valueOf(bstop.getGpsy()));
        mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        marker = new MapPOIItem();
        marker.setItemName(bstop.getBstop_name());
        marker.setMapPoint(point);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mapView.addPOIItem(marker);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.valueOf(bstop.getGpsx()),Double.valueOf(bstop.getGpsy())),1,true);

        busstopName=(TextView)findViewById(R.id.busstopTitleName);
        busstopName.setText(bstop.getBstop_name());

        toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.argb(119,0,0,255));
        setSupportActionBar(toolbar);


    }


    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {
        Log.i(LOG_TAG,	String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));
    }
}
