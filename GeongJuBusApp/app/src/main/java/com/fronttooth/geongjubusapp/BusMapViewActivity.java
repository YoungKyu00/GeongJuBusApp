package com.fronttooth.geongjubusapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

/**
 * Created by Young on 2017-08-19.
 */

public class BusMapViewActivity extends AppCompatActivity implements MapView.MapViewEventListener {


    private MapView mMapView;
    private static final String LOG_TAG = "BusMapViewActivity";
    private FloatingActionButton fab;
    private Json mJson;
    private String mResult;
    private MapPolyline mPolyline1Points;
    private MapPolyline mPolyline2Points;
    private TextView mBusName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_mapview);

        Intent intent = getIntent();
        final BusLineVO bus = intent.getParcelableExtra("bus");
        mBusName= (TextView)findViewById(R.id.busName);
        mBusName.setText(bus.getRoute_name());
        mMapView =(MapView)findViewById(R.id.bus_map_view);
        mMapView.setMapViewEventListener(this);

        getDrawInfo(bus);

    }

    private void addPolyline2(ArrayList<DrawInfoVO> mDrawlist){
        MapPOIItem existingPOItemStart = mMapView.findPOIItemByTag(10001);
        if(existingPOItemStart != null) {
            mMapView.removePOIItem(existingPOItemStart);
        }

        MapPOIItem existingPOItemEnd = mMapView.findPOIItemByTag(10002);
        if (existingPOItemEnd != null) {
            mMapView.removePOIItem(existingPOItemEnd);
        }
            MapPolyline existingPolyline = mMapView.findPolylineByTag(2000);
            if(existingPolyline != null){
                mMapView.removePolyline(existingPolyline);
            }

        MapPOIItem poiItemStart = new MapPOIItem();
        poiItemStart.setItemName("Start");
        poiItemStart.setTag(10001);
        poiItemStart.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(mDrawlist.get(0).getX_pos()),Double.parseDouble(mDrawlist.get(0).getY_pos())));
        poiItemStart.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        poiItemStart.setShowAnimationType(MapPOIItem.ShowAnimationType.SpringFromGround);
        poiItemStart.setShowCalloutBalloonOnTouch(false);
        poiItemStart.setCustomImageAnchorPointOffset(new MapPOIItem.ImageOffset(29, 2));
        poiItemStart.setCustomImageResourceId(R.drawable.custom_poi_marker_start);
        mMapView.addPOIItem(poiItemStart);

        MapPOIItem poiItemEnd = new MapPOIItem();
        poiItemEnd.setItemName("End");
        poiItemEnd.setTag(10001);
        poiItemEnd.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(mDrawlist.get(mDrawlist.size()-1).getX_pos()),Double.parseDouble(mDrawlist.get(mDrawlist.size()-1).getY_pos())));
        poiItemEnd.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        poiItemEnd.setShowAnimationType(MapPOIItem.ShowAnimationType.SpringFromGround);
        poiItemEnd.setCustomImageResourceId(R.drawable.custom_poi_marker_end);
        poiItemEnd.setShowCalloutBalloonOnTouch(false);
        poiItemEnd.setCustomImageAnchorPointOffset(new MapPOIItem.ImageOffset(29, 2));
        mMapView.addPOIItem(poiItemEnd);


        mMapView.addPolyline(mPolyline2Points);
        mMapView.addPolyline(mPolyline1Points);
        MapPointBounds mapPointBounds = new MapPointBounds(mPolyline2Points.getMapPoints());
        int padding = 200; // px
        mMapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));





    }

    public void getDrawInfo(BusLineVO bus){
        ArrayList<DrawInfoVO> mDrawlist;
        try {
            mJson = new Json();
            CustomTask task = new CustomTask();
            mResult = task.execute(bus.getRoute_id(),"drawinfo").get();
            mDrawlist = mJson.getJsonDrawInfo(mResult);
            mPolyline2Points = new MapPolyline();
            mPolyline1Points = new MapPolyline();
            if (bus.getRoute_id().length() > 0){
                for (int i = 0; i < mDrawlist.size(); i++) {
                   if( mDrawlist.get(i).getDir_type().equals("0")){
                       mPolyline1Points.setLineColor(Color.argb(128,0,0,255));
                       mPolyline1Points.addPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(mDrawlist.get(i).getX_pos()),Double.parseDouble(mDrawlist.get(i).getY_pos())));
                    }else {
                       mPolyline2Points.addPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(mDrawlist.get(i).getX_pos()), Double.parseDouble(mDrawlist.get(i).getY_pos())));
                   }
                }
            }
            addPolyline2(mDrawlist);
        }catch(Exception ex){//Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            ex.printStackTrace();}

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
}
