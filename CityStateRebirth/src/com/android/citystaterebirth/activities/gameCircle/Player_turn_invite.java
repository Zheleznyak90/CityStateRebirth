package com.android.citystaterebirth.activities.gameCircle;

import java.util.logging.Logger;

import com.android.citystaterebirth.functions.Game_func;
import com.android.citystaterebirth.structure.Player;
import com.android.citystaterebirth.structure.CityApp;

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

public class Player_turn_invite extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		LinearLayout linL = new LinearLayout(this);
		linL.setOrientation(LinearLayout.VERTICAL);
		LayoutParams linLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		setContentView(linL,linLParams);
		  
		
		Player currPlayer = ((CityApp)getApplication()).getCurrPlayer();
		int iterator_turn = ((CityApp)getApplication()).getCurrPlayerNumber();//TODO загнать в клиентскую часть
		//intent.getIntExtra("iterator_turn", 0);
		  
		LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lpMatchContent.gravity = Gravity.LEFT;
		  
		TextView tv = new TextView(this);
		tv.setText("Сейчас ход - "+currPlayer.getPlayerName()+". Он "+currPlayer.getPlayerRole());
		tv.setLayoutParams(lpMatchContent);
		linL.addView(tv);
		  
		final Intent next_intent = new Intent(this, Player_turn_action_choosing.class);
		next_intent.putExtra("iterator_turn", iterator_turn);

		linL.setOnTouchListener(new OnTouchListener() {
				
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN)
					startActivity(next_intent);
				finish();
			    return true;
				}
		});
		if(currPlayer.getPlayerRoleId() == 7)
			Game_func.getSingleGF().setCrowned(this, iterator_turn);//TODO занести в roleSpecial
	}
}
