package com.android.citystaterebirth.structure;

import java.util.ArrayList;
import java.util.Collections;

import com.android.citystaterebirth.functions.Game_func;
import com.android.citystaterebirth.structure.comparators.PlayerCompareId;

import android.app.Application;

public class CityApp extends Application {

	private ArrayList<Building> gameBuildingDeck;
	
	private ArrayList<Role> gameRoleDeck; 
	private ArrayList<Role> openedRoleDeck; 
	
	private ArrayList<Player> players;
	
	private boolean isLastTurn = false;
	
	private int currPlayerNumber = 0;
	
	private final int DEFAULT_MAX_BUILDING = 2;
	private int maxBuilding = DEFAULT_MAX_BUILDING;
	
	private static int FIRST_FINISHED_EXTRA_POINTS = 4;
	private static int NOT_FIRST_FINISHED_EXTRA_POINTS = 2;

	
	public void setGlobalVar(ArrayList<Building> _gameBuildingDeck, ArrayList<Role> _gameRoleDeck, ArrayList<Player> _players){
		gameBuildingDeck = _gameBuildingDeck;
		gameRoleDeck = _gameRoleDeck;
		players = _players;
		
		maxBuilding = DEFAULT_MAX_BUILDING;
		currPlayerNumber = 0;
	};
	
	public ArrayList<Building> getBuildingDeck(){
		return gameBuildingDeck;
	}
	
	public void setBuildingDeck(ArrayList<Building> _gameBuildingDeck){
		gameBuildingDeck = _gameBuildingDeck;
	}
	
	public ArrayList<Role> getRoleDeck(){
		return gameRoleDeck;
	}
	
	public void setRoleDeck(ArrayList<Role> _gameRoleDeck){
		gameRoleDeck = _gameRoleDeck;
	}
	
	public ArrayList<Role> getOpenedRoles(){
		return openedRoleDeck;
	}
	
	public void setOpenedRoles(ArrayList<Role> _openedRoleDeck){
		openedRoleDeck = _openedRoleDeck;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public void setPlayers(ArrayList<Player> _players){
		players = _players;
	}
	
	public boolean getIsLast(){
		return isLastTurn;
	}
	
	public void setIsLast(boolean _isLast){
		isLastTurn = _isLast; 
	}
	
	public int getMaxBuilding(){
		return maxBuilding;
	}
	
	public void setMaxBuilding(int _maxBuilding){
		maxBuilding = _maxBuilding; 
	}
	
	public int getCurrPlayerNumber(){
		return currPlayerNumber;
	}
	
	public void setCurrPlayerNumber(int _currPlayerNumber){
		currPlayerNumber = _currPlayerNumber; 
	}
	
	public Player getCurrPlayer(){
		return players.get(currPlayerNumber);
	}
	
	public void modifyCurrentPlayer(Player _player){
		players.set(currPlayerNumber, _player);
	}
	
	public void newTurnRefresh(){
		for (Player pl:players){
			pl.refreshAtNewTurn();
		}
		
		Collections.sort(players, new PlayerCompareId());
		int crownedPlayerId = Game_func.getSingleGF().getCrownedPlayer(players);
		  
		ArrayList<Player> tmpPlayersList = new ArrayList<Player>(players.size());
		for(int i = 0; i<players.size();i++){
			if(players.size()-(crownedPlayerId+i)>0)
				tmpPlayersList.add(players.get(crownedPlayerId+i));
			else 
				tmpPlayersList.add(players.get(Math.abs(players.size()-(crownedPlayerId+i))));
		}
		players = tmpPlayersList;
		
		currPlayerNumber = 0;
	}
	
	public void currPlayerBuild(int _constrIndex){
		Player currPlayer = getCurrPlayer();
		currPlayer.build(_constrIndex);
		
		boolean isLastTurnLocal = currPlayer.getBuilded().size() >= maxBuilding;
		
		if(isLastTurn == false && isLastTurnLocal){
			currPlayer.addExtraPoints(FIRST_FINISHED_EXTRA_POINTS);
			isLastTurn = true;
		}
		else if( isLastTurn && isLastTurnLocal)
			currPlayer.addExtraPoints(NOT_FIRST_FINISHED_EXTRA_POINTS);

	}
	
}
