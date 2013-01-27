package com.fgouget.dm_android;

import java.util.ArrayList;

import com.fgouget.dm_android.lieu.Lieu;
import com.fgouget.dm_android.lieu.LieuAdapter;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FavorisActivity extends Activity {
	private ArrayList<Lieu> favoris;
	private LieuAdapter adapter;
	private SharedPreferences prefs ;

	protected void onStart(){
		super.onStart();
		Log.i("prefs", "show");
		favoris.clear();
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		for (int i=0; i<MainActivity.listItem.size(); i++){
			if (prefs.contains(MainActivity.listItem.get(i).getNom())){
				favoris.add(MainActivity.listItem.get(i));
			}
		}
		adapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		favoris = new ArrayList<Lieu>();
		
		
		adapter = new LieuAdapter(this, favoris);
		
		
		ListView listView = (ListView)findViewById(R.id.listView1);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				Intent monIntent = new Intent( parent.getContext(), LieuActivity.class);
				Lieu selectionne = (Lieu)parent.getItemAtPosition(position);
				monIntent.putExtra("monLieu", selectionne);
				startActivity(monIntent);
			}
			
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_favoris, menu);
		return true;
	}

}
