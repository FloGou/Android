package com.fgouget.dm_android.lieu;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import com.fgouget.dm_android.MainActivity;
import com.fgouget.dm_android.MyMapActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem>{

	private ArrayList<OverlayItem> myOverlays ;
	
	public MyItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		myOverlays = new ArrayList<OverlayItem>();
		populate();
	}

	
	public void addOverlay(OverlayItem overlay){
		myOverlays.add(overlay);
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {

		return myOverlays.get(i);
	}

	protected boolean onTap(int i){
		
		Lieu l = MainActivity.listItem.get(i);
		EditText editText = new EditText(MainActivity.context);
		GeoPoint point = l.getGeoPoint();
		editText.setText(l.getNom());
		LayoutParams screenLP;
		screenLP = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT,
				point,
				LayoutParams.TOP_LEFT);
		
		MyMapActivity.mapView.addView(editText, screenLP);
		return true;
	}
	
	
	@Override
	public int size() {
		return myOverlays.size();
	}

}
