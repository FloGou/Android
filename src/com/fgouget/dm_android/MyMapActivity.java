package com.fgouget.dm_android;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


public class MyMapActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map);
		MapView mapView = (MapView)findViewById(R.id.map_view);
		
		MyLocationOverlay myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		mapView.getOverlays().add(myLocation);
		myLocation.enableMyLocation();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_map, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
