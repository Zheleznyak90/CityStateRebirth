package com.android.citystaterebirth.activities.gameCircle;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.citystaterebirth.R;
import com.android.citystaterebirth.activities.support.Card_gallery;
import com.android.citystaterebirth.activities.support.Stats;
import com.android.citystaterebirth.functions.Game_func;
import com.android.citystaterebirth.structure.Building;
import com.android.citystaterebirth.structure.CardShowing;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;
import com.android.citystaterebirth.structure.comparators.PlayerComparePoints;

public class Player_turn_constracting extends Activity implements CardShowing{
	//Logger log = Logger.getLogger(Game_circle_begining.class.getName());
	
	private TextView cointsTV;
	private Button observeBtn;
	private Button nextTurnBtn;
	private Button plans;
	private Button alreadyBuilt;
	private Button roleSpecial;
	
	private ArrayList<Player> players;
	private boolean isLastTurn;
	private int iterator_turn;//TODO refactor to isLast in players coll
	//Role_special special;
	private Player currPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.constracting);
	    
	 	Intent intent = getIntent();
		 
	    currPlayer = ((CityApp)getApplication()).getCurrPlayer();
	    
	 	players = ((CityApp)getApplication()).getPlayers();
	 	isLastTurn = ((CityApp)getApplication()).getIsLast();
		iterator_turn = intent.getIntExtra("iterator_turn", 0);
		
		cointsTV = (TextView) findViewById(R.id.moneyAmount);
		
		observeBtn = (Button) findViewById(R.id.btn_players_obs);
		nextTurnBtn = (Button) findViewById(R.id.btn_end_turn);
		plans = (Button) findViewById(R.id.btn_plans);
		alreadyBuilt = (Button) findViewById(R.id.btn_already_built);
		roleSpecial = (Button) findViewById(R.id.btn_special);
		
	    //special = new Role_special(currPlayer, roleSpecial);
	    //Thread specialThread = new Thread(special);
	    //specialThread.start();
		
		final Intent next_intent_cycle = new Intent(this, Player_turn_invite.class);
		final Intent next_intent_new_turn = new Intent(this, Game_circle_begining.class);
		final Intent next_intent_obs = new Intent(this, Stats.class);
		//final Intent next_intent_plans = new Intent(this, Act_building_list_scroll.class);
		final Intent next_intent_game_results = new Intent(this, Stats.class);
				
		
		OnClickListener ocl = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent next_intent = new Intent();
				
				switch (v.getId()) {
				
					case R.id.btn_special:
						
						break;
						
					case R.id.btn_already_built:
						cardShowFragmentAdd(currPlayer.getBuilded());
						/*next_intent = next_intent_plans;
						next_intent.putExtra("isBuidedList", true);
						startActivity(next_intent);
						*/
						break;	
					case R.id.btn_plans:
						cardShowFragmentAdd(currPlayer.getBuildingCards());
						/*
						next_intent = next_intent_plans;
						startActivity(next_intent);
						*/
						break;
						
					case R.id.btn_players_obs:
						next_intent = next_intent_obs;
						startActivity(next_intent);
						
						break;
					case R.id.btn_end_turn:
						
						if(iterator_turn<players.size()-1){
							next_intent = next_intent_cycle;
							((CityApp)getApplication()).setCurrPlayerNumber(iterator_turn+1);
						}
						else{
							next_intent = next_intent_new_turn;
							if(isLastTurn){
								Collections.sort(players, new PlayerComparePoints());
								next_intent = next_intent_game_results;
								next_intent.putExtra("isGameRes", 1);
								startActivity(next_intent);
								finish();
								try {
									finalize();
								} catch (Throwable e) {
									e.printStackTrace();
									//log.info("exeption throwed");
								}
							}
						}
						startActivity(next_intent);
						finish();
						
						break;
				}
				
			}
		};

		nextTurnBtn.setOnClickListener(ocl);
		observeBtn.setOnClickListener(ocl);
		plans.setOnClickListener(ocl);
		alreadyBuilt.setOnClickListener(ocl);
		
	}
	
	@Override
	protected void onResume() {
		cointsTV.setText(String.valueOf(((CityApp)getApplication()).getCurrPlayer().getGoldAmount()));

		super.onResume();
	}
	
	private void cardShowFragmentAdd(ArrayList<Building> _buildDeck){
		LinearLayout linL = new LinearLayout(this);
		linL.setOrientation(LinearLayout.VERTICAL);
		LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		setContentView(linL,linLParams);
		linL.setId(2121212121);
		
	    Fragment roleShow = new Card_gallery();
	    FragmentTransaction fTrans = getFragmentManager().beginTransaction();
			
	    Bundle picIds = new Bundle();
	    picIds.putString("Modifier", "building_");
	    picIds.putStringArrayList("Ids", Game_func.getSingleGF().createImgArr(_buildDeck));
	    picIds.putBoolean("isBNeeded", true);
	    roleShow.setArguments(picIds);
			
	    fTrans.add(linL.getId(), roleShow);
	    fTrans.addToBackStack(null);
	      fTrans.commit();
	}

	@Override
	public void onGalleryClose(int _elementId) {
		// TODO Auto-generated method stub
		
	}
	
}
