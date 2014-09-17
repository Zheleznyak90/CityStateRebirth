package com.android.citystaterebirth.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.citystaterebirth.activities.gameCircle.Game_circle_begining;
import com.android.citystaterebirth.functions.Game_func;
import com.android.citystaterebirth.structure.Building;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;
import com.android.citystaterebirth.structure.Role;

public class Game_instance_initiation extends Activity {
	//Logger log = Logger.getLogger(Game_circle_begining.class.getName());
	  
	ArrayList<Role> gameRoleDeck;
	ArrayList<Building> gameBuildingDeck;
	  
	ArrayList<Player> players;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		int playersNumber = intent.getIntExtra("playersNumber", 4);
		//Player.setApp((CityApp)getApplication());//TODO вы€снить адекватно ли
		
		gameRoleDeck = Game_func.getSingleGF().CreateRoleDeck();
		((CityApp)getApplication()).setRoleDeck(gameRoleDeck);
		 
		gameBuildingDeck = Game_func.getSingleGF().CreateBuildingDeck();
		((CityApp)getApplication()).setBuildingDeck(gameBuildingDeck);
		
		players = new ArrayList<Player>(playersNumber);

		int isC = 0;
		for(int i=1;i<=playersNumber;i++){
			if(i==0)
				isC = 1;
			else
				isC = 0;
			players.add(new Player(i, "Player "+i, gameBuildingDeck , isC));
		}
		
		intent = new Intent(this, Game_circle_begining.class);
		
		((CityApp)getApplication()).setPlayers(players);
		//Intent testInt = new Intent(this, Act_visual_list_creation.class);
		//startActivity(testInt);
		
		startActivity(intent);
		finish();


	}
}
