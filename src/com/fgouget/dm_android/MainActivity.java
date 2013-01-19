package com.fgouget.dm_android;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.fgouget.dm_android.lieu.Data_Download;
import com.fgouget.dm_android.lieu.Lieu;
import com.fgouget.dm_android.lieu.LieuAdapter;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	
	public static ArrayList<Lieu> listItem;
	private TabHost tabHost;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		
		Data_Download downloader = new Data_Download(this);
		FutureTask<ArrayList<Lieu>> task = new FutureTask<ArrayList<Lieu>>(downloader);
		
		
		Thread t1 = new Thread(task);
		t1.start();
		
		try {
			listItem = task.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
			tabHost = getTabHost();
			
			TabSpec tabSpec = tabHost.newTabSpec("Liste").setIndicator("Liste").setContent(new Intent(this,ListActivity.class));
			tabHost.addTab(tabSpec);
			//tabSpec = tabHost.newTabSpec("Favoris").setIndicator("Favoris").setContent(new Intent(this,Favoris.class));
			//tabHost.addTab(tabSpec);
			tabSpec= tabHost.newTabSpec("Carte").setIndicator("Carte").setContent(new Intent(this,MyMapActivity.class));
			tabHost.addTab(tabSpec);
		


		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
