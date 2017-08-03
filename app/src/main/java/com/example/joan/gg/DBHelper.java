package com.example.joan.gg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joan on 2016/10/3.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String _DB_NAME = "MyDatabases.db";
    public static final String TABLE_JP_STORE ="TABLE_JP_STORE";//ok
    public static final String TABLE_STORE_ENG ="TABLE_STORE_ENG";//ok
    public static final String TABLE_JP_TRAVEL="TABLE_JP_TRAVEL";//沒用到
    public static final String TABLE_PLAY_KOERA="TABLE_PLAY_KOERA";//ok
    public static final String TABLE_TRAVEL_POT ="TABLE_TRAVEL_POT";//ok

    public static final String TABLE_SLEEP_ENG ="TABLE_SLEEP_ENG";//ok
    public static final String TABLE_SLEEP_CH ="TABLE_SLEEP_CH";//ok
    public static final String TABLE_SLEEP_JP ="TABLE_SLEEP_JP";//ok

    public static final String TABLE_POLICE="POLICE";//ok
    public static final String TABLE_FIRE_STATION ="TABLE_FIRE_STATION";//ok

    public static final String TABLE_PARK="PARK";//ok

    public static final String TABLE_STOLEN_BIKE="TABLE_STOLEN_BIKE";//ok
    public static final String TABLE_ATTACK ="TABLE_ATTACK";//ok
    //in version 2
    public static final String TABLE_STOLEN_SCOOTER="TABLE_STOLEN_SCOOTER";//ok
    public static final String TABLE_STOLEN_CAR="TABLE_STOLEN_CAR";//ok


    public static final int _DB_VERSION = 3;

    public static final String NAME_CHINESE="name_chinese";
    public static final String LATITUDE_="latitude";
    public static final String LONGITUDE_="longitude";
    public static final String TEL="tel";
    public static final String OPENTIME="opentime";
    public static final String Toldescribe="Toldescribe";
    public static final String Web="web";
    public static final String ADDRESS="ADDRESS";
    public static final String SurplusSpace="SurplusSpace";
    public static final String totalSpace="totalSpace";



    public DBHelper(Context context) {
        super(context, _DB_NAME, null, _DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE "+TABLE_JP_STORE+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"
                +OPENTIME+" TEXT," +Toldescribe+" TEXT,"+ADDRESS+" TEXT,"+Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_JP_TRAVEL+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"
                +OPENTIME+" TEXT," +Toldescribe+" TEXT,"+ADDRESS+" TEXT,"+Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_SLEEP_ENG+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"
                +Toldescribe+" TEXT,"+ADDRESS+" TEXT,"+Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_ATTACK+" ( _ID INTEGER PRIMARY KEY," +LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE)");

        db.execSQL( "CREATE TABLE "+TABLE_TRAVEL_POT+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"+ADDRESS+" TEXT,"
                +Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_SLEEP_CH+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"+ADDRESS+" TEXT,"
                +Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_FIRE_STATION+" ( _ID INTEGER PRIMARY KEY," +LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE)");

        db.execSQL( "CREATE TABLE "+TABLE_STORE_ENG+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"
                +Toldescribe+" TEXT,"+ADDRESS+" TEXT,"+Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_PLAY_KOERA+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"+OPENTIME+" TEXT,"
                +Toldescribe+" TEXT,"+ADDRESS+" TEXT,"+Web+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_POLICE+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"+ADDRESS+" TEXT)");

        db.execSQL( "CREATE TABLE "+TABLE_PARK+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                +" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"+ADDRESS+" TEXT,"+Toldescribe+" TEXT,"+totalSpace+" INTEGER,"+SurplusSpace+" INTEGER)");

        db.execSQL( "CREATE TABLE "+TABLE_STOLEN_BIKE+" ( _ID INTEGER PRIMARY KEY," +LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newone) {
        if(old<2)
        {
            db.execSQL( "CREATE TABLE "+TABLE_STOLEN_SCOOTER+" ( _ID INTEGER PRIMARY KEY," +LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE)");
            db.execSQL( "CREATE TABLE "+TABLE_STOLEN_CAR+" ( _ID INTEGER PRIMARY KEY," +LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE)");
            db.execSQL( "CREATE TABLE "+TABLE_SLEEP_JP+" ( _ID INTEGER PRIMARY KEY," +NAME_CHINESE
                    +" TEXT,"+TEL+" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"
                    +Toldescribe+" TEXT,"+ADDRESS+" TEXT,"+Web+" TEXT)");
        }

        if(old<3)
        {
            db.execSQL( "CREATE TABLE "+TABLE_PARK+" ( F INTEGER PRIMARY KEY," +NAME_CHINESE
                    +" TEXT,"+LATITUDE_+" DOUBLE,"+LONGITUDE_+" DOUBLE,"+ADDRESS+" TEXT,"+Toldescribe+" TEXT,"+totalSpace+" INTEGER,"+SurplusSpace+" INTEGER)");
        }
    }


}
