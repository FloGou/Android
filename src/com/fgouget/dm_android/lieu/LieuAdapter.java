package com.fgouget.dm_android.lieu;

import java.util.List;

import com.fgouget.dm_android.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LieuAdapter extends BaseAdapter {

	List<Lieu> liste_lieux;
	LayoutInflater inflater;
	Context context;
	private final static ImageLoader imageLoader = ImageLoader.getInstance();
	
	public LieuAdapter(Context context, List<Lieu> objects){
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		inflater = LayoutInflater.from(context);
		liste_lieux = objects;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return liste_lieux.size();
	}

	@Override
	public Lieu getItem(int position) {
		// TODO Auto-generated method stub
		return liste_lieux.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return liste_lieux.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.list_item_layout, null);
		TextView tvNom = (TextView) convertView.findViewById(R.id.textView_nom);
		TextView tvQuartier = (TextView) convertView.findViewById(R.id.textView_quartier);
		TextView tvSecteur = (TextView) convertView.findViewById(R.id.textView_secteur);
		ImageView image_small = (ImageView) convertView.findViewById(R.id.imageView1);
		Lieu lieu = this.getItem(position);
		Log.i("parse", lieu.getQuartier());
		tvNom.setText(lieu.getNom());
		tvQuartier.setText(lieu.getQuartier());
		tvSecteur.setText(lieu.getSecteur());

		Log.i("parse", lieu.getSmall_image_url());
		String smallImage = lieu.getSmall_image_url();
		imageLoader.displayImage(smallImage, image_small);
		
		return convertView;
	}

}
