package com.android.citystaterebirth.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO {
	private DBHelper CS_dbh;
	private SQLiteDatabase CS_db;
	private static DAO singleDAO;
	//Logger log = Logger.getLogger(Citadels_dao.class.getName());
	
	public DAO(Context context){
		CS_dbh = new DBHelper(context);
		CS_db = CS_dbh.getWritableDatabase();
		singleDAO = this;
	}
	
	public static DAO getDAO(){
		return singleDAO;
	}
	
	public Cursor getAllRoles(){
		Cursor cursor;
		cursor = CS_db.query(CS_dbh.ROLE_TABLE,
				null, null, null, null, null, null);
		/*		if(cursor.moveToFirst())
			log.info(cursor.getString(0));
		do{
		log.info(cursor.getString(1));
		}while(cursor.moveToNext());*/
		return cursor;
	}
	
	public void onDestroy(){
		CS_dbh.close();
		CS_db.close();
	}
	
	public Cursor getAllBuildings(){
		Cursor cursor;
		cursor = CS_db.query(CS_dbh.BUILDING_TABLE,
				null, null, null, null, null, null);
		return cursor;
	}

}
