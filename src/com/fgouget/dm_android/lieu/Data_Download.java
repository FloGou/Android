package com.fgouget.dm_android.lieu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fgouget.dm_android.lieu.Lieu;

public class Data_Download implements Callable<ArrayList<Lieu>> {

	private ArrayList<Lieu> listItem;
	private Context context;


	
	
	public Data_Download(Context c){
		this.context = (Context)c;
		this.listItem = new ArrayList<Lieu>();
	}
	

	@Override
	public ArrayList<Lieu> call(){

		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://cci.corellis.eu/pois.php");

		try {
			HttpResponse response = httpclient.execute(httpget);
				if (response != null) {
					String line = "";
					InputStream inputstream = response.getEntity().getContent();
					line = convertStreamToString(inputstream);
					Log.i("parse", "reading feed");
					JSONObject jsonObject = new JSONObject(line);
					JSONArray jsonArray = jsonObject.getJSONArray("results");
					Log.i("parse", "Number of entries: "+ jsonArray.length());					
					for (int i = 0; i<jsonArray.length(); i++){
						JSONObject myObject = jsonArray.getJSONObject(i);
						HashMap<String, Object> currentMap = new HashMap<String, Object>();
						Iterator<String> keys = myObject.keys();
							while(keys.hasNext()){
								String currentKey = keys.next();
								currentMap.put(currentKey, myObject.get(currentKey)); 
							}
						Lieu l = new Lieu(currentMap);
						listItem.add(l);
					}
				
				} 
				else {
					
					//Toast.makeText(context, "Unable to complete your request", Toast.LENGTH_SHORT).show();
				}
	} catch (ClientProtocolException e){
	//	progressDialog.dismiss();
		Log.i("parse", "Client Protocol Exception");
		//Toast.makeText(context, "Caught ClientProtocolException",Toast.LENGTH_SHORT).show();
	} catch (IOException e){
	//	progressDialog.dismiss();
		Log.i("parse", e.getMessage().toString());
		//Toast.makeText(context, "Caught IOException",Toast.LENGTH_SHORT).show();
	} catch (Exception e){

	//	progressDialog.dismiss();
		Log.i("parse", e.getMessage().toString());
		//Toast.makeText(context, "erreur" ,Toast.LENGTH_SHORT).show();
	}
	Log.i("parse", listItem.toString());
	//progressDialog.dismiss();
	return listItem;
	}
	
	
	
	
	private String convertStreamToString(InputStream is){
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try {
			while((line = rd.readLine()) != null){
				total.append(line);
			}
		} catch (Exception e) {
			Toast.makeText(context,  "Stream Exception", Toast.LENGTH_SHORT).show();
		}
		return total.toString();
	}
	
	
	}


