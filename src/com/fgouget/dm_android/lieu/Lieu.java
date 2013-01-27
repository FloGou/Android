package com.fgouget.dm_android.lieu;

import java.util.HashMap;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Lieu implements Parcelable{

	private int id;
	private String icon_id;
	private Float lon;
	private String categorie_id;
	private String small_image_url;
	private String image_url;
	private String quartier;
	private String informations;
	private String nom;
	private Float lat;
	private String secteur;

	
	public Lieu(HashMap<String, Object> map){
		this.id = (Integer)map.get("id");
		this.icon_id = (String)map.get("icon_id");
		this.lon = Float.valueOf((String) map.get("lon"));
		this.categorie_id = (String) map.get("categorie_id");
		this.small_image_url = (String) map.get("small_image");
		this.image_url = (String) map.get("image");
		this.quartier = (String) map.get("quartier");
		this.informations = (String) map.get("informations");
		this.nom = (String) map.get("nom");
		this.lat = Float.valueOf((String) map.get("lat"));
		this.secteur = (String) map.get("secteur");
			
	}

	public Lieu(Parcel in) {
		this.id = in.readInt();
		this.icon_id = in.readString();
		this.lon = in.readFloat();
		this.categorie_id = in.readString();
		this.small_image_url = in.readString();
		this.image_url = in.readString();
		this.quartier  = in.readString();
		this.informations = in.readString();
		this.nom = in.readString();
		this.lat = in.readFloat();
		this.secteur = in.readString();
	}

	
	public GeoPoint getGeoPoint(){
		Log.i("parse", String.valueOf(this.lon));
		return new GeoPoint((int)(this.lat * 1E6), (int)(this.lon * 1E6));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcon_id() {
		return icon_id;
	}

	public void setIcon_id(String icon_id) {
		this.icon_id = icon_id;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public String getCategorie_id() {
		return categorie_id;
	}

	public void setCategorie_id(String categorie_id) {
		this.categorie_id = categorie_id;
	}

	public String getSmall_image_url() {
		return small_image_url;
	}

	public void setSmall_image_url(String small_image_url) {
		this.small_image_url = small_image_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.icon_id);
		dest.writeFloat(this.lon);
		dest.writeString(this.categorie_id);
		dest.writeString(this.small_image_url);
		dest.writeString(this.image_url);
		dest.writeString(this.quartier);
		dest.writeString(this.informations);
		dest.writeString(this.nom);
		dest.writeFloat(this.lat);
		dest.writeString(this.secteur);
		
	}

	
	public static final Parcelable.Creator<Lieu> CREATOR = new Parcelable.Creator<Lieu>(){
		public Lieu createFromParcel(Parcel in){
			return new Lieu(in);
		}
		
		public Lieu[] newArray(int size){
			return new Lieu[size];
		}
		
	};
	
	
}
