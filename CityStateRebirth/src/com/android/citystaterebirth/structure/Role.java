package com.android.citystaterebirth.structure;

import android.os.Parcel;
import android.os.Parcelable;

public class Role implements Parcelable{
	//Logger log = Logger.getLogger(Role.class.getName());
	 
	private int roleId;
	private String roleName;
	private int roleColor;
	
	public Role(int _roleId, String _roleName, int _roleColor){
		roleId = _roleId;
		roleName = _roleName;
		roleColor = _roleColor;
	}
	
	public String getName(){
		return roleName;
	}
	
	public int getId(){
		return roleId;
	}
	
	public int getRoleColor(){
		return roleColor;
	}
	
	public int describeContents() {
		    return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(roleId);
		parcel.writeString(roleName);
		parcel.writeInt(roleColor);
	}
	
	public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>() {
	    public Role createFromParcel(Parcel parcel) {
	    	return new Role(parcel);
	    }

	    public Role[] newArray(int size) {
	    	return new Role[size];
	    }
	};
	
	Role(Parcel parcel)
	{
		roleId = parcel.readInt();
		roleName = parcel.readString();
		roleColor = parcel.readInt();
	}
			
}

