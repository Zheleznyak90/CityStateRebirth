package com.android.citystaterebirth.activities.support;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.citystaterebirth.R;
import com.android.citystaterebirth.activities.Players_number_choose;
import com.android.citystaterebirth.structure.CityApp;
import com.android.citystaterebirth.structure.Player;

public class Stats extends Activity {
	ArrayList<Player> players;
	TableLayout tableL;
	Button btnBack;
	Button btnNewGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		players = ((CityApp)getApplication()).getPlayers();
		int isGameRes = intent.getIntExtra("isGameRes", 0);
		setContentView(R.layout.results);
		
		tableL = (TableLayout) findViewById(R.id.result_table);
		
		for(int i=0;i<players.size();i++){
			TableRow tRow = new TableRow(this);
			String[] tmpStr = players.get(i).getDataForTable();
			for(int n=0; n<tmpStr.length;n++){
				TextView tv = new TextView(this);
				tv.setText(tmpStr[n]);
				tRow.addView(tv);
			}
			tableL.addView(tRow);
		}
		
		btnBack = (Button) findViewById(R.id.btnBackRes);
		if(isGameRes != 0){
			btnNewGame = (Button) findViewById(R.id.btnNewGame);
			
			btnBack.setEnabled(false);
			btnBack.setVisibility(View.INVISIBLE);
			
			btnNewGame.setEnabled(true);
			btnNewGame.setVisibility(View.VISIBLE);
			
			final Intent next_intent = new Intent(this, Players_number_choose.class);
			
			btnNewGame.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(next_intent);
					finish();
				
				}
			});
		}
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
