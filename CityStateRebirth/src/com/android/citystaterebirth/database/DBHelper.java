package com.android.citystaterebirth.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	static final String DB_NAME = "Citadels_db";
	static final int dbVersion = 1;
	static final String ROLE_TABLE = "Role";
	
	static final String ROLE_ID = "RoleId";
	static final String ROLE_NAME = "Name";
	static final String ROLE_COLOR = "Color";
	
	static final String[][] ROLE=
	{
		{"�������","0"},{"������","0"},
		{"���","0"},{"������ �������","0"},
		{"�������","0"},{"���������","0"},
		{"������","4"},{"���������","4"},
		{"�������","3"},{"�����","3"},
		{"�����","2"},{"�������","2"},
    	{"������","0"},{"���������","0"},
    	{"���������","1"},{"��������","1"}
    };
	
	
	static final String BUILDING_TABLE = "Building";
	static final String BUILDING_COLOR_TABLE = "BuildingColor";
	
	static final String BUILDING_COLOR_ID = "ColorId";
	static final String BUILDING_COLOR_NAME = "Name";
	
	static final String[] COLOR = {"Red","Green","Blue","Yellow","Purple"};
	
	static final String BUILDING_ID = "BuildingId";
	static final String BUILDING_NAME = "Name";
	static final String BUILDING_COST = "Cost";
	static final String BUILDING_NUMBER = "Number";
	//static final String BUILDING_COLOR_KEY = "ColorId";

	ArrayList<String[][]> BUILDING_LIST;
	
	static final int COLORS_NUMBER = 4;
	
	static String[][] RED_BUILDINGS = 
	{
		{"�������� �����","1","3"},
		{"������","2","3"},
		{"������� ����","3","3"},
		{"��������","5","2"}
	};
	static String[][] GREEN_BUILDING = 
	{
		{"�������","1","4"},
		{"�����","2","3"},
		{"�����","2","4"},
		{"����","3","3"},
		{"������","4","3"},
		{"������","5","2"}
	};
	static String[][] BLUE_BUILDING =
	{
		{"����","1","3"},
		{"�������","2","3"},
		{"���������","3","3"},
		{"�����","5","2"}
	};
	static String[][] YELLOW_BUILDING = 
	{
		{"��������","3","5"},
		{"�����","4","4"},
		{"�������","5","3"}
	};
	static String[][] PURPLE_BUILDING;//TODO ���������� ��������
	
	public DBHelper(Context context) {
		  super(context, DB_NAME, null ,dbVersion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE IF NOT EXISTS "+ROLE_TABLE+" (" +
				ROLE_ID+" INT PRIMARY KEY NOT NULL, " +
				ROLE_NAME+" TEXT NOT NULL, " +
				ROLE_COLOR+"INT NOT NULL);");
		
		String tempDbString = "INSERT INTO "+ROLE_TABLE+" VALUES ";
			
		for (int i=0;i<ROLE.length;i++){
			int n=i+1;
			tempDbString += "("+n+",\""+ROLE[i][0]+"\","+ROLE[i][1]+"),";
		}
		tempDbString = tempDbString.substring(0, tempDbString.length()-1)+";";
		db.execSQL(tempDbString);
		
		db.execSQL("CREATE TABLE IF NOT EXISTS "+BUILDING_COLOR_TABLE +"("+
					BUILDING_COLOR_ID+" INT PRIMARY KEY NOT NULL, " +
					BUILDING_COLOR_NAME+" TEXT);");
				
		db.execSQL("CREATE TABLE IF NOT EXISTS "+BUILDING_TABLE+"(" +
					BUILDING_ID+" INT PRIMARY KEY NOT NULL, " +
					BUILDING_NAME+ " TEXT NOT NULL, "+
					BUILDING_COST+" INT NOT NULL, " +			
					BUILDING_NUMBER+" INT NOT NULL, " +
					BUILDING_COLOR_ID+" INT NOT NULL);");
		tempDbString = "INSERT INTO "+BUILDING_COLOR_TABLE+" VALUES ";
		
		for (int i=0;i<5;i++){
			int n=i+1;
			tempDbString += "("+n+",\""+COLOR[i]+"\"),";
		}
		tempDbString = tempDbString.substring(0, tempDbString.length()-1)+";";
		db.execSQL(tempDbString);
		
		BUILDING_LIST = new ArrayList<String[][]>(5);
		
		BUILDING_LIST.add(RED_BUILDINGS);
		BUILDING_LIST.add(GREEN_BUILDING);
		BUILDING_LIST.add(BLUE_BUILDING);
		BUILDING_LIST.add(YELLOW_BUILDING);
		
		tempDbString = "INSERT INTO "+BUILDING_TABLE+" VALUES ";
		String[][] tmpString;
		int id = 1;
		for(int i=1;i<COLORS_NUMBER+1;i++){
			tmpString = BUILDING_LIST.get(i-1);
			for (int k=0;k<tmpString.length;k++){
				tempDbString += "("+id+", \""+tmpString[k][0]+"\", "+tmpString[k][1]+", "+tmpString[k][2]+", "+ i+"),";
				id++;
			}
		}
		tempDbString = tempDbString.substring(0, tempDbString.length()-1)+";";
		db.execSQL(tempDbString);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int NewV) {
		db.execSQL("DROP TABLE IF EXISTS "+ROLE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS "+BUILDING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS "+BUILDING_COLOR_TABLE);
		onCreate(db);
		
	}


}
