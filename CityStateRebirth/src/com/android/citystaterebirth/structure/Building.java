package com.android.citystaterebirth.structure;

import android.os.Parcel;
import android.os.Parcelable;

public class Building implements Parcelable{
	private int buildingId;
	private String buildingName;
	private int buildingCost;
	private int buildingColor;
	//private String buildingImg;
	//private Building_special buildingSpecial;
	
	public Building(int _id, String _name, int _cost, int _color/*, String img*/){
		buildingId = _id;
		buildingName = _name;
		buildingCost = _cost;
		buildingColor = _color;
		//buildingImg = img;
		//buildingSpecial = new Building_special();
	}
	
	public String getName(){
		return buildingName;
	}
	
	public String getId(){
		return String.valueOf(buildingId);
	}
	
	public int getCost(){
		return buildingCost;
	}
	
	public int getColorInt(){
		return buildingColor;
	}
	
	public String getColor(){
		String color ="";
		switch (buildingColor) {
		case 1:
			color = "Красный";
			break;
		case 2:
			color = "Зеленый";
			break;
		case 3:
			color = "Синий";
			break;
		case 4:
			color = "Желтый";
			break;
		case 5:
			color = "Фиолетовый";
			break;
		}
		return color;
	}
	
	public int describeContents() {
	    return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(buildingId);
		parcel.writeString(buildingName);
		parcel.writeInt(buildingCost);
		parcel.writeInt(buildingColor);
		//parcel.writeString(buildingImg);
		//TODO add data for BuildingSpecial
	}
	
	public static final Parcelable.Creator<Building> CREATOR = new Parcelable.Creator<Building>() {
	    public Building createFromParcel(Parcel parcel) {
	    	return new Building(parcel);
	    }
	
	    public Building[] newArray(int size) {
	    	return new Building[size];
	    }
	};
	
	Building(Parcel parcel)
	{
		buildingId = parcel.readInt();
		buildingName = parcel.readString();
		buildingCost = parcel.readInt();
		buildingColor = parcel.readInt();
		//buildingImg = parcel.readString();
	//	BuildingSpecial = new Building_special();
	}
}



