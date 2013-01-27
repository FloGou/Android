package com.fgouget.dm_android;

import com.fgouget.dm_android.lieu.Lieu;
import com.fgouget.dm_android.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class LieuActivity extends Activity {

	private Lieu monLieu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lieu);
		TextView nom = (TextView)findViewById(R.id.textView_nom);
		TextView quartier = (TextView)findViewById(R.id.textView_quartier);
		TextView secteur = (TextView)findViewById(R.id.textView_secteur);
		TextView description = (TextView)findViewById(R.id.textView_description);
		CheckBox favoris = (CheckBox)findViewById(R.id.checkBox1);
		ImageView image = (ImageView)findViewById(R.id.imageView1);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		

		Bundle extras = getIntent().getExtras();
		if( extras != null) {
			monLieu = (Lieu) extras.getParcelable("monLieu");
			if (monLieu != null){
				nom.setText(monLieu.getNom());
				quartier.setText(monLieu.getQuartier());
				secteur.setText(monLieu.getSecteur());
				Spanned monHtml = Html.fromHtml(monLieu.getInformations());
				
				description.setText(monHtml.toString(), BufferType.SPANNABLE);
				
				
				Toast.makeText(getApplication(), monLieu.getNom(), Toast.LENGTH_SHORT).show();
			}
			
		}
		
		String smallImage = monLieu.getImage_url();
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
		imageLoader.displayImage(smallImage, image);
		
		
		favoris.setChecked(prefs.contains(monLieu.getNom()));

		favoris.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				
				Editor e = prefs.edit();
				if (prefs.contains(monLieu.getNom())){
					e.remove(monLieu.getNom());
				}
				else {
				e.putBoolean(monLieu.getNom(), true);
				}
				if (e.commit()){
					Toast.makeText(getApplicationContext(), "Favoris modifié", Toast.LENGTH_SHORT).show();
				}
				
			}
			
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lieu, menu);
		return true;
	}

}
