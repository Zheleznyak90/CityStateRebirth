package com.android.citystaterebirth.activities.gameCircle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//TODO кол-во золота и карт

import com.android.citystaterebirth.R;
import com.android.citystaterebirth.structure.Building;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;

public class Player_turn_action_choosing extends Activity {
	
	Button btnChooseGold;
	Button btnChooseCards;
	
	TextView tvMoney;
	TextView tvPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.action_choose);
	    
	    btnChooseGold = (Button) findViewById(R.id.btn_gold);
	    btnChooseCards = (Button) findViewById(R.id.btn_cards);
	    
	    tvMoney = (TextView) findViewById(R.id.tv_money);
	    tvPlans = (TextView) findViewById(R.id.tv_plans);
	    
		Intent intent = getIntent();
		
		final Player currPlayer = ((CityApp)getApplication()).getCurrPlayer();
		
		final int iterator_turn = intent.getIntExtra("iterator_turn", 0);
		final ArrayList<Building> gameBuildingDeck = ((CityApp)getApplication()).getBuildingDeck();
		
		//currPlayer.addExtraCoints();
		//TODO show addiotional coins this turn
		tvMoney.append(String.valueOf(currPlayer.getGoldAmount()));
		tvPlans.append(String.valueOf(currPlayer.getBuildingCards().size()));
		  
		final Intent next_intent = new Intent(this, Player_turn_constracting.class);
		next_intent.putExtra("iterator_turn", iterator_turn);
		
	    OnClickListener oncl_choice_sc = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_cards:
					currPlayer.pickTwoCards(gameBuildingDeck);
					((CityApp)getApplication()).modifyCurrentPlayer(currPlayer);
					((CityApp)getApplication()).setBuildingDeck(gameBuildingDeck);
					startActivity(next_intent);
					finish();
					
					break;

				case R.id.btn_gold:
					currPlayer.pickTwoCoins();
					((CityApp)getApplication()).modifyCurrentPlayer(currPlayer);
					startActivity(next_intent);
					finish();
			    		
					break;

				default:
					break;
				}
			}
			
		};
		btnChooseCards.setOnClickListener(oncl_choice_sc);
		btnChooseGold.setOnClickListener(oncl_choice_sc);
	    
    }
}
