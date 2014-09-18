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
	  private ArrayList<Role> gameRoleDeckTurn;
	  private ArrayList<Role> openedRoleTurn;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		   
		  super.onCreate(savedInstanceState);
		  
		  final LinearLayout linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  //linL.setId(2121212121);
		  
		  ArrayList<Role> gameRoleDeck = ((CityApp)getApplication()).getRoleDeck();
		  ArrayList<Player> players = ((CityApp)getApplication()).getPlayers();
		  
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
					if (event.getAction()==MotionEvent.ACTION_DOWN){
						startActivity(intent_next);
					}
					finish();
					return true;
				}
		  });
	  }
	  

}

