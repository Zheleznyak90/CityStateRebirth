package com.android.citystaterebirth.activities.gameCircle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.citystaterebirth.functions.Game_func;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;
import com.android.citystaterebirth.structure.Role;

public class Game_circle_begining extends Activity{
	  ArrayList<Role> gameRoleDeckTurn;
	  ArrayList<Role> openedRoleTurn;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		   
		  super.onCreate(savedInstanceState);
		  
		  LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  
		  //Intent intent = getIntent();
		  
		  ArrayList<Role> gameRoleDeck = ((CityApp)getApplication()).getRoleDeck();
		  ArrayList<Player> players = ((CityApp)getApplication()).getPlayers();
		  
		  /*gameFunction = new Game_func(this);
		  
		  Collections.sort(players, new PlayerCompareId());
		  int crownedPlayerId = gameFunction.getCrownedPlayer(players);
		  
		  tmpPlayerList = new ArrayList<Player>(players.size());
		  for(int i = 0; i<players.size();i++){
			  if(players.size()-(crownedPlayerId+i)>0)
				  tmpPlayerList.add(players.get(crownedPlayerId+i));
			  else 
				  tmpPlayerList.add(players.get(Math.abs(players.size()-(crownedPlayerId+i))));
		  }*/
		  ((CityApp)getApplication()).newTurnRefresh();
		  
		  gameRoleDeckTurn = Game_func.getSingleGF().CreateRoleDeckTurn(gameRoleDeck, players.size());
		  openedRoleTurn = new ArrayList <Role>();
		  
		  for (int i=0; i<8-players.size()-2;i++){
			  openedRoleTurn.add(gameRoleDeckTurn.get(0));
			  gameRoleDeckTurn.remove(0);
		  }
		  gameRoleDeckTurn.trimToSize();
		  
		  final Intent intent_next = new Intent(Game_circle_begining.this, Role_choosing.class);
		  
		  ((CityApp)getApplication()).setOpenedRoles(openedRoleTurn);
		  //((CityApp)getApplication()).setPlayers(tmpPlayerList);
		  
		  intent_next.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
		  
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  TextView tv = new TextView(this);
		  tv.setText(((CityApp)getApplication()).getCurrPlayer().getPlayerName()+" выбери себе персонажа");
		  tv.setLayoutParams(lpMatchContent);
		  linL.addView(tv);
		  
		  linL.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction()==MotionEvent.ACTION_DOWN)
						  startActivity(intent_next);
					  finish();
					  return true;
				}
		  });
	  }
}

