package com.android.citystaterebirth.activities.gameCircle;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.citystaterebirth.activities.support.Card_gallery;
import com.android.citystaterebirth.functions.Game_func;
import com.android.citystaterebirth.structure.CardShowing;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;
import com.android.citystaterebirth.structure.Role;
import com.android.citystaterebirth.structure.comparators.PlayerCompereRole;

public class Role_choosing extends Activity implements CardShowing{
	private ArrayList<Role> openedRoleTurn;
	private ArrayList<Role> gameRoleDeckTurn;
	private ArrayList<Player> players;
	private int iterator_role_choose;
	private LinearLayout linL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		  
		  Intent intent = getIntent();
		  
		  players = ((CityApp)getApplication()).getPlayers();
		  openedRoleTurn = ((CityApp)getApplication()).getOpenedRoles();
		  gameRoleDeckTurn = intent.getParcelableArrayListExtra("gameRoleDeckTurn");
		  iterator_role_choose = intent.getIntExtra("iterator_role_choose", 0)+1;
		
		  linL = new LinearLayout(this);
		  linL.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  setContentView(linL,linLParams);
		  linL.setId(2121212121);
		  
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
		  String openedRoles = "Открытые роли: ";
		  for(int i=0; i<openedRoleTurn.size();i++)
			  openedRoles += openedRoleTurn.get(i).getName()+", ";
		  		  
		  openedRoles = openedRoles.substring(0, openedRoles.length()-2)+".";
	      
		  TextView tv = new TextView(this);
	      tv.setText(players.get(iterator_role_choose-1).getPlayerName()+" выбери роль.");
	      tv.setLayoutParams(lpMatchContent);
	      linL.addView(tv);
	      
	      tv = new TextView(this);
	      tv.setText(openedRoles);
	      tv.setLayoutParams(lpMatchContent);
	      linL.addView(tv);

	      cardShowFragmentAdd();
		  //Game_func.getSingleGF().setViewRoleChoise(linL, players, gameRoleDeckTurn, iterator_role_choose, this);
		
	}
	
	private void cardShowFragmentAdd(){
	      Fragment roleShow = new Card_gallery();
	      FragmentTransaction fTrans = getFragmentManager().beginTransaction();
			
	      Bundle picIds = new Bundle();
	      picIds.putString("Modifier", "role_");
	      picIds.putStringArrayList("Ids", Game_func.getSingleGF().createImgArr(gameRoleDeckTurn));
	      picIds.putBoolean("isBNeeded", true);
	      roleShow.setArguments(picIds);
			
	      fTrans.add(linL.getId(), roleShow);
	      fTrans.addToBackStack(null);
	      fTrans.commit();
	}

	@Override
	public void onGalleryClose(int _elementId) {
		Intent nextIntent;
		
		players.get(iterator_role_choose-1).setRole(gameRoleDeckTurn.get(_elementId));
		gameRoleDeckTurn.remove(_elementId);
		
		if(iterator_role_choose<players.size()){
			nextIntent = new Intent(this, Role_choosing.class);
			nextIntent.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
			nextIntent.putExtra("iterator_role_choose", iterator_role_choose);
			  
    	}
		else{
			nextIntent = new Intent(this, Player_turn_invite.class);
			Collections.sort(players, new PlayerCompereRole());
		}
		startActivity(nextIntent);
		finish();
		
	}
}
