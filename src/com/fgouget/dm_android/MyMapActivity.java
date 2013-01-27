package com.fgouget.dm_android;

import java.util.List;

import com.fgouget.dm_android.lieu.Lieu;
import com.fgouget.dm_android.lieu.MyItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;


public class MyMapActivity extends MapActivity {
	private List<Overlay> mapOverlays;
	public static  MapView mapView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map);
		mapView = (MapView)findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		MyLocationOverlay myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		mapView.getOverlays().add(myLocation);
		myLocation.enableMyLocation();
		mapView.getController().setZoom(19);
		mapView.getController().setCenter(MainActivity.listItem.get(0).getGeoPoint());
		setOverlay();
	}

	
	public void setOverlay(){
		mapOverlays = mapView.getOverlays();
		Drawable drawable1 = this.getResources().getDrawable(R.drawable.ic_launcher);
		MyItemizedOverlay itemizedOverlay1 = new MyItemizedOverlay(drawable1);
		for (int i=0; i<MainActivity.listItem.size(); i++){
			Lieu l = MainActivity.listItem.get(i);
			OverlayItem ov = new OverlayItem(l.getGeoPoint(), l.getNom(), l.getInformations());
			itemizedOverlay1.addOverlay(ov);
		}
		mapOverlays.add(itemizedOverlay1);
		mapView.postInvalidate();
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
