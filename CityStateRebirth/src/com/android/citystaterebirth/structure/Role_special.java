package com.android.citystaterebirth.structure;

import java.util.logging.Logger;

import android.widget.Button;

public class Role_special implements Runnable{//TODO заняться синхронизацией
	
	Player currPlayer;
	Button spl_btn;
	
	public Role_special(Player _currPlayer, Button _spl_btn) {
		currPlayer = _currPlayer;
		spl_btn = _spl_btn;
	}
	public void run(){
		currPlayer.addExtraCoints();
		spl_btn.setEnabled(true);
		
		switch (currPlayer.getPlayerRoleId()) {
		case 1://Ассасин
			
			break;
		case 3://Вор
			
			break;
		case 5://Чародей
			
			break;
		case 7://Король
			
			break;
		case 9://Епископ
			
			break;
		case 11://Купец
			
			break;
		case 13://Зодчий
			//currPlayer.pickTwoCards();
			//currPlayer.setMaxBuildingTurn(3);
			//TODO ret
			break;
		case 15://кондотьер
			
			break;

		}
	}
}
