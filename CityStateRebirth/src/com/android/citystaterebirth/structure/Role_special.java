package com.android.citystaterebirth.structure;

import java.util.logging.Logger;

import android.widget.Button;

public class Role_special implements Runnable{//TODO �������� ��������������
	
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
		case 1://�������
			
			break;
		case 3://���
			
			break;
		case 5://�������
			
			break;
		case 7://������
			
			break;
		case 9://�������
			
			break;
		case 11://�����
			
			break;
		case 13://������
			//currPlayer.pickTwoCards();
			//currPlayer.setMaxBuildingTurn(3);
			//TODO ret
			break;
		case 15://���������
			
			break;

		}
	}
}
