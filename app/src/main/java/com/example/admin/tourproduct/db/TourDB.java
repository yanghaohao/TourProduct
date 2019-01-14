package com.example.admin.tourproduct.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TourDB extends SQLiteOpenHelper {


    public TourDB(Context context) {
        super(context, "tour.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * private int Id;
         private int ShopId;
         private String UserId;
         private String Name;
         private String Content;
         private String termOfValidity;
         private String time;
         private String userule;
         private String Status;
         private String Limit;
         private String UniqueCode;
         private String denomination;
         private double money;
         private int bitmap;
         */

        /**
         * private int Id;
         private String name;
         private String head;
         private String content;
         private String inputuser;
         private String inputdate;
         private int numberOfGiveTheThumbsUp;
         private List<BitmapsBean> Bitmaps;
         private List<DiscussBean> Discuss;
         */

        /**
         * private int Id;
         private String name;
         private String head;
         private String content;
         private String inputuser;
         private String inputdate;
         private int numberOfGiveTheThumbsUp;
         private List<BitmapsBean> Bitmaps;
         private List<DiscussBean> Discuss;
         private List<String> bitmapPath;
         */
        db.execSQL("create table user(username text,password text)");
        db.execSQL("create table place(name text,icon integer)");
        db.execSQL("create table coupon(Id integer,ShopId integer,UserId text,name text,Content text,termOfValidity text,time text" +
                ",userule text,Status text,limits text,UniqueCode text,denomination text,money text,bitmap integer)");
        db.execSQL("create table my_travel(Id integer,name text,head text,content text,inputuser text,inputdate text,numberOfGiveTheThumbsUp integer" +
                ",Bitmaps text,discuss text)");
        db.execSQL("create table draft(Id integer,name text,head text,content text,inputuser text,inputdate text,numberOfGiveTheThumbsUp integer" +
                ",Bitmaps text,discuss text)");
//        db.execSQL("create table draft(Id integer,name text,head text,content text,inputuser text,inputdate text,numberOfGiveTheThumbsUp integer" +
//                ",Bitmaps text,discuss text)");
//        db.execSQL("create table collect_travel(Id integer,name text,head text,content text,inputuser text,inputdate text,numberOfGiveTheThumbsUp integer" +
//                ",Bitmaps text,discuss text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
