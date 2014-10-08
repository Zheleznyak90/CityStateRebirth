package com.android.citystaterebirth.structure;

import java.util.ArrayList;

import com.android.citystaterebirth.functions.Game_func;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable{
	//Logger log = Logger.getLogger(Player.class.getName());
	private int playerId;
	 	
	private String playerName;
	private int coinsNumber;
	private int points;
	private Role turnRole;
	private ArrayList<Building> inHandBuildings;
	private ArrayList<Building> builded;

	private int isCrowned ;
	private int builtThisTurn;
	private int maxBuildingTurn;
	
	//Game_func gameFunction;
	//private int maxBuilding = 2;
	//private static int FIRST_FINISHED_EXTRA_POINTS = 4;
	//private static int NOT_FIRST_FINISHED_EXTRA_POINTS = 2;
		
	public Player(int _id, String _pName, ArrayList<Building> _buildingDeck, int _isC){
		playerId = _id;
		inHandBuildings = new ArrayList<Building>();
		builded = new ArrayList<Building>();
		
		playerName = _pName;
		coinsNumber = 2;
		points = 0;
		for(int i=0;i<4;i++)
			inHandBuildings.add(Game_func.getSingleGF().PickACard(_buildingDeck));
		isCrowned = _isC;
	}
	
	public void setRole(Role _ChoicedRole){
		turnRole = _ChoicedRole;
	}
	
	public void addExtraPoints(int _points){
		points += _points;
	}
	
	public void pickTwoCards(ArrayList<Building> _buildingDeck){
		for(int i=0;i<2;i++)
			inHandBuildings.add(Game_func.getSingleGF().PickACard(_buildingDeck));
	}
	
	public void pickTwoCoins(){
		coinsNumber += 2;
	}
	
	public String getPlayerRole(){
		return turnRole.getName();
	}
	
	public int getPlayerRoleId(){
		return turnRole.getId();
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public int getGoldAmount(){
		return coinsNumber;
	}
	
	public ArrayList<Building> getBuilded()	{
		return builded;
	}
	
	public ArrayList<Building> getBuildingCards()	{
		return inHandBuildings;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public int getPlayerPoints(){
		return points;
	}
	
	public String[] getDataForTable(){
		String [] tmpStr = {playerName, String.valueOf(builded.size()), String.valueOf(inHandBuildings.size()), String.valueOf(coinsNumber), String.valueOf(points)};
		return tmpStr;
	}
	
	public void addExtraCoints(){
		int roleColor = turnRole.getRoleColor();
		if(roleColor != 0)
			for(int i = 0; i<builded.size(); i++){
				if(roleColor == builded.get(i).getColorInt())
					coinsNumber++;
			}
	}
	
	public boolean build(int _buildingIndex){
		Building constr = inHandBuildings.get(_buildingIndex);
		points += constr.getCost();
		coinsNumber -= constr.getCost();
		builded.add(constr);
		inHandBuildings.remove(constr);
		builtThisTurn++;

		return false;
	}

	public boolean isAlreadyBuilded(Building _building){//Да, да я знаю, что build неправильный глагол
		boolean isBuilded = false;
		for(Building isBuilt:builded){
			if(isBuilt.getId() == _building.getId()){//Сравниваем полученный объект здания со список построенных
				isBuilded = true;
				break;
			}
		}
			
		return isBuilded;
	}
	
	public int getIsCrowned(){
		return isCrowned;
	}
	
	public void setIsCrowned(int _isCr){
		isCrowned = _isCr;
	}
	
	public void refreshAtNewTurn(){
		builtThisTurn = 0;
		maxBuildingTurn = 1;
	}
	
	public boolean isBuildAvailible(){
		return builtThisTurn<maxBuildingTurn;
	}
	
	public int describeContents() {
	    return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(playerId);
		
		parcel.writeString(playerName);
		parcel.writeInt(coinsNumber);
		parcel.writeInt(points);
		
		parcel.writeInt(isCrowned);
		turnRole.writeToParcel(parcel, flags);
		
		parcel.writeTypedList(inHandBuildings);
		parcel.writeTypedList(builded);
	}
	
	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
	    public Player createFromParcel(Parcel parcel) {
	    	return new Player(parcel);
	    }
	
	    public Player[] newArray(int size) {
	    	return new Player[size];
	    }
	};
	
	Player(Parcel parcel){
		inHandBuildings = new ArrayList<Building>();
		builded = new ArrayList<Building>();
		
		playerId = parcel.readInt();
				
		playerName = parcel.readString();
		coinsNumber = parcel.readInt();
		points = parcel.readInt();
		isCrowned = parcel.readInt();
		turnRole = new Role(parcel);
		
		parcel.readTypedList(inHandBuildings, Building.CREATOR);
		parcel.readTypedList(builded, Building.CREATOR);
		
	}

	
}