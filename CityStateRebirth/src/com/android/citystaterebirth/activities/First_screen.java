package com.android.citystaterebirth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.citystaterebirth.R;
import com.android.citystaterebirth.database.DAO;
import com.android.citystaterebirth.functions.Game_func;

public class First_screen extends Activity{

	Button btnNewGame;
	//private Button btnSetting;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);
       
        new DAO(this);
        new Game_func();
        
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        //btnSetting = (Button) findViewById(R.id.btnSettings);
		
        OnClickListener oncb_new_game = new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(First_screen.this, Players_number_choose.class);
				startActivity(intent);
			}
        };
        btnNewGame.setOnClickListener(oncb_new_game);

	}
}