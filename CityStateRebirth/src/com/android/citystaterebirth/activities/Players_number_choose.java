package com.android.citystaterebirth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.citystaterebirth.structure.CityApp;

public class Players_number_choose extends Activity{
	  EditText players_number;
	  Button btn_pn_next;	
	
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		  
		  ((CityApp)getApplication()).setIsLast(false);
		  super.onCreate(savedInstanceState);
		  //Toast.makeText(this, "LOL", Toast.LENGTH_LONG).show();
		  LinearLayout.LayoutParams lpMatchContent = new LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  lpMatchContent.gravity = Gravity.LEFT;
		
		  LinearLayout linLayout = new LinearLayout(this);
		  linLayout.setOrientation(LinearLayout.VERTICAL);
		  LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT); 
		  setContentView(linLayout, linLayoutParam);
		  
		  TextView tv = new TextView(this);
	      tv.setText("Выберите количество игроков");
	      tv.setLayoutParams(lpMatchContent);
	      linLayout.addView(tv);
	      
	      final Intent gameCreationInt = new Intent(this, Game_instance_initiation.class);
	     
	      for(int i=4; i<8; i++){
			  Button btn = new Button(this);
	    	  btn.setText(String.valueOf(i));
	    	  linLayout.addView(btn,lpMatchContent);
	    	  final int y = i;
	    	  
	    	  btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					gameCreationInt.putExtra("playersNumber", y);
					startActivity(gameCreationInt);
					finish();
				}
			});
	    	 
		  }

	  }
}
