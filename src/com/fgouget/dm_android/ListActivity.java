package com.fgouget.dm_android;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.fgouget.dm_android.lieu.Data_Download;
import com.fgouget.dm_android.lieu.Lieu;
import com.fgouget.dm_android.lieu.LieuAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivity extends Activity {
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list);
		
		
		
		
		//Log.i("parse", listItem.toString());
		LieuAdapter adapter = new LieuAdapter(this, MainActivity.listItem);

		
		
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
		getMenuInflater().inflate(R.menu.activity_list, menu);
		return true;
	}

}
