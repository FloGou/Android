package com.fgouget.dm_android;

import com.fgouget.dm_android.lieu.Lieu;
import com.fgouget.dm_android.R;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class LieuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lieu);
		TextView nom = (TextView)findViewById(R.id.textView_nom);
		TextView quartier = (TextView)findViewById(R.id.textView_quartier);
		TextView secteur = (TextView)findViewById(R.id.textView_secteur);
		TextView description = (TextView)findViewById(R.id.textView_description);
		ImageView image = (ImageView)findViewById(R.id.imageView1);

		Bundle extras = getIntent().getExtras();
		if( extras != null) {
			Lieu monLieu = (Lieu) extras.getParcelable("monLieu");
			if (monLieu != null){
				nom.setText(monLieu.getNom());
				quartier.setText(monLieu.getQuartier());
				secteur.setText(monLieu.getSecteur());
				Spanned monHtml = Html.fromHtml(monLieu.getInformations());
				
				description.setText(monHtml.toString(), BufferType.SPANNABLE);
				
				
				Toast.makeText(getApplication(), monLieu.getNom(), Toast.LENGTH_SHORT).show();
			}
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lieu, menu);
		return true;
	}

}
