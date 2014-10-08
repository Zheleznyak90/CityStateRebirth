package com.android.citystaterebirth.functions;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.android.citystaterebirth.activities.gameCircle.Player_turn_invite;
import com.android.citystaterebirth.activities.gameCircle.Role_choosing;
import com.android.citystaterebirth.database.DAO;
import com.android.citystaterebirth.structure.Building;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;
import com.android.citystaterebirth.structure.Role;
import com.android.citystaterebirth.structure.comparators.PlayerCompereRole;

public class Game_func {
	DAO csDao;
	static final int DeckCap = 8;
	static final int NeededCol = 2;
	private static Game_func singleGameF;
	 
	public Game_func(){
		csDao = DAO.getDAO();
		singleGameF = this;
	}
	
	public static Game_func getSingleGF(){
		return singleGameF;
	}
	  
	public ArrayList<Role> CreateRoleDeck(){ 
		ArrayList<Role> tmpRoleDeck = new ArrayList<Role>(8);
		Cursor tmpCursor = csDao.getAllRoles();
		if(tmpCursor.moveToFirst())
			for(int i=0;i<8;i++){//Позиции в курсоре с 0, а айди ролей с 1
				tmpCursor.moveToPosition(i*2);
				tmpRoleDeck.add(new Role(tmpCursor.getInt(0),tmpCursor.getString(1),tmpCursor.getInt(/*4*/1)));
			}
		tmpCursor.close();
		return tmpRoleDeck;
	 }
	 
	 public ArrayList<Role> CreateRoleDeckTurn(ArrayList<Role> roles, int playersNum){//TODO Переделать с ассоциативным массивом
		 //Map<String, ArrayList<Role>> rolesMap = new HashMap<String, ArrayList<Role>>();
		 ArrayList<Role> tmpRolesDeck = new ArrayList<Role>(8);
		 tmpRolesDeck.addAll(roles);
		 ArrayList<Role> tmpRoleDeckTurn = new ArrayList<Role>(7);
		 
		 int rand = Common_func.randNumber(roles.size());
		 tmpRolesDeck.remove(rand);
		 
		 //ArrayList<Role> openedRoles = new ArrayList<Role>(2);
		 for(int i=0;i<8-playersNum-2;i++){
			 do{
				 rand = Common_func.randNumber(roles.size()-i-1);
			//	 log.info(roles.get(rand).getName());
			 }while(tmpRolesDeck.get(rand).getId() == 7 || tmpRolesDeck.get(rand).getId() == 8);
			 //openedRoles.add(tmpRolesDeck.get(rand));
			 tmpRoleDeckTurn.add(tmpRolesDeck.get(rand));
			 
			 tmpRolesDeck.remove(rand);
		 }
		 //rolesMap.put("openedRoles", openedRoles);
		 //rolesMap.put("availableRoles", tmpRolesDeck);
		 
		 tmpRoleDeckTurn.addAll(tmpRolesDeck);
		 return tmpRoleDeckTurn;
	 }
	 
	 public ArrayList<Building> CreateBuildingDeck(){
		 ArrayList<Building> tmpBuildingDeck = new ArrayList<Building>();
		 Cursor tmpCursor = csDao.getAllBuildings();
		 if(tmpCursor.moveToFirst())
			 for(int i=0;i<tmpCursor.getCount();i++){
				 for(int n=0;n<tmpCursor.getInt(3);n++){
					 tmpBuildingDeck.add(new Building(tmpCursor.getInt(0),tmpCursor.getString(1),tmpCursor.getInt(2),tmpCursor.getInt(4)/*,tmpCursor.getString(2)*/));//TODO del img part of constr
				 }
				 tmpCursor.moveToNext();
			 }
		 tmpCursor.close();
		 return tmpBuildingDeck;
		 
	 }
	 
	 public Building PickACard(ArrayList<Building> deck){
		 Building randomCard;
		 int rand = Common_func.randNumber(deck.size());
		 randomCard = deck.get(rand);
		 deck.remove(rand);
		 deck.trimToSize();
		 return randomCard;
	 }
	 
	 
	 //TODO USEFULL NOT GRAPHICAL INTERFACE
	 
	 /*public void setViewRoleChoise( final LinearLayout linealL, final ArrayList<Player> players, final ArrayList<Role> gameRoleDeckTurn, final int iterator_role_choose,  final Context context){
		 
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		  
	      final Intent intent_r = new Intent(context, Role_choosing.class);
	      final Intent intent_n = new Intent(context, Player_turn_invite.class);
	      
	      for(int i=0; i<gameRoleDeckTurn.size();i++){
	    	  final Role fnlRole = gameRoleDeckTurn.get(i);
	    	  Button btn = new Button(context);
	    	  btn.setText(gameRoleDeckTurn.get(i).getName());
	    	  linealL.addView(btn,lpMatchContent);
	    	  final int n = i;
	    	  btn.setOnClickListener(new View.OnClickListener() {
	    		  @Override
	    		  public void onClick(View v) {
	    			  Intent intent_tmp;
	    			  
	    		      players.get(iterator_role_choose-1).setRole(fnlRole);
	    			  gameRoleDeckTurn.remove(n);
	    			  if(iterator_role_choose<players.size()){
	    				  intent_tmp = intent_r;
	    				  intent_tmp.putExtra("gameRoleDeckTurn", gameRoleDeckTurn);
	    				  intent_tmp.putExtra("iterator_role_choose", iterator_role_choose);
		    			  
			    	  }
	    			  else{
	    				  intent_tmp = intent_n;
		    			  Collections.sort(players, new PlayerCompereRole());
	    			  }
	    			  ((CityApp)((Activity) context).getApplication()).setPlayers(players);
		    		  context.startActivity(intent_tmp);
		    		  ((Activity) context).finish();
	
	    		  
	    		  }
	    	  });
	      }
	 }*/

	 /*
	 public void setBuldingScreen( final LinearLayout buildedL, final LinearLayout inHandL, final ArrayList<Player> players,final int builded , final int iterator_turn, final Context context){
		 LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 lpMatchContent.gravity = Gravity.LEFT;
		  
		 ArrayList<Building> tmpBuildings;
		 tmpBuildings = players.get(iterator_turn).getBuildingCards();
		 //for(int i=0; i<tmpBuildings.size();i++){
		 for(Building currBuilding:tmpBuildings){
			 
			 final Button btn = new Button(context);
			 btn.setText(currBuilding.getName()+", "+currBuilding.getColor()+", "+currBuilding.getCost());
			 inHandL.addView(btn, lpMatchContent);
			 final Intent intent_refresh = new Intent(context, Player_turn_constracting.class);
			 boolean isSameBB = players.get(iterator_turn).isAlreadyBuilded(currBuilding);//TODO: Убрать холостые вызовы если уже построено здание
			 if(currBuilding.getCost()>players.get(iterator_turn).getGoldAmount() || builded!=0 || isSameBB)
				 btn.setEnabled(false);
			 else{
				 final Building tmpBuilding = currBuilding;
				 btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						players.get(iterator_turn).build(tmpBuilding, context);
						intent_refresh.putExtra("iterator_turn", iterator_turn);
						intent_refresh.putExtra("builded", builded+1);
						context.startActivity(intent_refresh);
						((Activity) context).finish();

					}
				});
			 }
		 }
		 
		 tmpBuildings = players.get(iterator_turn).getBuilded();
		 for(int i=0; i<tmpBuildings.size();i++){
			 TextView tv = new TextView(context);
			 tv.setText(tmpBuildings.get(i).getName()+", "+tmpBuildings.get(i).getColor()+", "+tmpBuildings.get(i).getCost());
		     tv.setLayoutParams(lpMatchContent);
		     buildedL.addView(tv);
		      
		 }		
	 }
	 */
	 public int getCrownedPlayer(ArrayList<Player> _players){
		  
		  int crownedPlayerId = 0;
		  for(Player pl:_players){
			  if(pl.getIsCrowned() == 1){
				  crownedPlayerId = pl.getPlayerId()-1;
				  break;
			  }
		  }
		 
		 return crownedPlayerId;
	 }
	 
	 public void setCrowned(Context _context, int _player_number){
		 ArrayList<Player> players = ((CityApp)((Activity) _context).getApplication()).getPlayers();
		 int crownedPl = getCrownedPlayer(players);
		 
		 players.get(crownedPl).setIsCrowned(0);
		 players.get(_player_number).setIsCrowned(1);
		 ((CityApp)((Activity) _context).getApplication()).setPlayers(players);
		 
	 }
	 
	public <T> ArrayList<String> createImgArr(ArrayList<T> _inpObjArr) {
		ArrayList<String> imgArr = new ArrayList<String>();
		
		for(Object iterObj:_inpObjArr){
			if(iterObj instanceof Role)
				imgArr.add(Integer.toString(((Role) iterObj).getId()));
			else if(iterObj instanceof Building){
				imgArr.add(((Building) iterObj).getId());
			}
		}
		return imgArr;
	}
	
	public ArrayList<String> createBuildArr(Player _Player){
		ArrayList<String> imgArr = new ArrayList<String>();
		for(Building currBuilding:_Player.getBuildingCards()){
			String currString;
			currString = currBuilding.getId();
			if(currBuilding.getCost()>_Player.getGoldAmount() || !_Player.isBuildAvailible() || _Player.isAlreadyBuilded(currBuilding))//TODO сделать цветным для списка постоенных
				currString += "_bw";
			imgArr.add(currString);
			}
		return imgArr;
	}

}
