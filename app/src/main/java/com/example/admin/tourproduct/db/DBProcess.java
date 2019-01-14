package com.example.admin.tourproduct.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.entry.Place;
import com.example.admin.tourproduct.entry.TravelsNote;
import com.example.admin.tourproduct.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBProcess {

    private Context mContext;
    private SQLiteDatabase database;

    public DBProcess(Context context) {
        mContext = context;
        database = new TourDB(context).getWritableDatabase();
    }

    /**
     *  优惠券的增删改查
     * @param coupon
     */
    //Id integer,ShopId integer,UserId text,Content text,termOfValidity text,time text" +
    //                ",userule text,Status text,limits text,UniqueCode text,denomination text,money text,bitmap integer
    public void addCoupon(Delicacy.EntityBean.CouponListBean coupon) {
        LogUtil.e("yanghao");
        //db.execSQL("create table user(username text,password text)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", coupon.getId());
        contentValues.put("ShopId", coupon.getShopId());
        contentValues.put("UserId", coupon.getUserId());
        contentValues.put("name", coupon.getName());
        contentValues.put("Content", coupon.getContent());
        contentValues.put("termOfValidity", coupon.getTermOfValidity());
        contentValues.put("time", coupon.getTime());
        contentValues.put("userule", coupon.getUserule());
        contentValues.put("Status", coupon.getStatus());
        contentValues.put("limits", coupon.getLimit());
        contentValues.put("UniqueCode", coupon.getUniqueCode());
        contentValues.put("denomination", coupon.getDenomination());
        contentValues.put("money", coupon.getMoney()+"");
        contentValues.put("bitmap", coupon.getBitmap());
        database.insert("coupon", null, contentValues);
    }

    public void deleteCoupon(String Id) {
        database.delete("coupon", "Id=?", new String[]{Id});
    }

    public void updateCoupon (Delicacy.EntityBean.CouponListBean coupon) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", coupon.getId());
        contentValues.put("ShopId", coupon.getShopId());
        contentValues.put("UserId", coupon.getUserId());
        contentValues.put("name", coupon.getName());
        contentValues.put("Content", coupon.getContent());
        contentValues.put("termOfValidity", coupon.getTermOfValidity());
        contentValues.put("time", coupon.getTime());
        contentValues.put("userule", coupon.getUserule());
        contentValues.put("Status", coupon.getStatus());
        contentValues.put("limits", coupon.getLimit());
        contentValues.put("UniqueCode", coupon.getUniqueCode());
        contentValues.put("denomination", coupon.getDenomination());
        contentValues.put("money", coupon.getMoney()+"");
        contentValues.put("bitmap", coupon.getBitmap());
        database.update("coupon", contentValues, "Id=?", new String[]{String.valueOf(coupon.getId())});
    }

    public Delicacy.EntityBean.CouponListBean findCoupon(String id) {
        Cursor cursor = database.rawQuery("select * from coupon where Id =?", new String[]{id});
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        return new Delicacy.EntityBean.CouponListBean(cursor.getInt(cursor.getColumnIndex("Id"))
                ,cursor.getInt(cursor.getColumnIndex("ShopId")),cursor.getString(cursor.getColumnIndex("UserId"))
                ,cursor.getString(cursor.getColumnIndex("name"))
                ,cursor.getString(cursor.getColumnIndex("Content")),cursor.getString(cursor.getColumnIndex("termOfValidity"))
                ,cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("userule"))
                ,cursor.getString(cursor.getColumnIndex("Status")),cursor.getString(cursor.getColumnIndex("limits"))
                ,cursor.getString(cursor.getColumnIndex("UniqueCode")),cursor.getString(cursor.getColumnIndex("denomination"))
                ,Double.valueOf(cursor.getString(cursor.getColumnIndex("money"))),cursor.getInt(cursor.getColumnIndex("bitmap")));
    }

    public List<Delicacy.EntityBean.CouponListBean> findAllCoupon() {
        List<Delicacy.EntityBean.CouponListBean> list = new ArrayList();
        Cursor cursor = database.rawQuery("select * from coupon", null);
        if (cursor == null) {
            LogUtil.i("user", "cursor!=null");
        } else {
            while (cursor.moveToNext()) {
                list.add(new Delicacy.EntityBean.CouponListBean(cursor.getInt(cursor.getColumnIndex("Id"))
                        ,cursor.getInt(cursor.getColumnIndex("ShopId")),cursor.getString(cursor.getColumnIndex("UserId"))
                        ,cursor.getString(cursor.getColumnIndex("name"))
                        ,cursor.getString(cursor.getColumnIndex("Content")),cursor.getString(cursor.getColumnIndex("termOfValidity"))
                        ,cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("userule"))
                        ,cursor.getString(cursor.getColumnIndex("Status")),cursor.getString(cursor.getColumnIndex("limits"))
                        ,cursor.getString(cursor.getColumnIndex("UniqueCode")),cursor.getString(cursor.getColumnIndex("denomination"))
                        ,Double.valueOf(cursor.getString(cursor.getColumnIndex("money"))),cursor.getInt(cursor.getColumnIndex("bitmap"))));
            }
        }
        return list;
    }

    /**
     * 我的游记的增删改查
     * @param travel
     */
    //coupon(Id integer,name text,head text,content text,inputuser text,inputdate text,numberOfGiveTheThumbsUp integer" +
    //                ",Bitmaps text,discuss text)"
    public void addMyTravel(TravelsNote.EntityBean travel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", travel.getId());
        contentValues.put("name", travel.getName());
        contentValues.put("head", travel.getHead());
        contentValues.put("content", travel.getContent());
        contentValues.put("inputuser", travel.getInputuser());
        contentValues.put("inputdate", travel.getInputdate());
        contentValues.put("numberOfGiveTheThumbsUp", travel.getNumberOfGiveTheThumbsUp());
        StringBuffer bitmap = new StringBuffer();
        for (int i = 0;i<travel.getBitmaps().size();i++){
            bitmap.append(travel.getBitmaps().get(i));
        }
        contentValues.put("Bitmaps", bitmap.toString());
        contentValues.put("discuss", "");
        database.insert("my_travel", null, contentValues);
    }


    public void deleteMyTravel(int Id){
        database.delete("my_travel", "Id=?", new String[]{String.valueOf(Id)});
    }

    public void updateMytravel (TravelsNote.EntityBean travel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", travel.getId());
        contentValues.put("name", travel.getName());
        contentValues.put("head", travel.getHead());
        contentValues.put("content", travel.getContent());
        contentValues.put("inputuser", travel.getInputuser());
        contentValues.put("inputdate", travel.getInputdate());
        contentValues.put("numberOfGiveTheThumbsUp", travel.getNumberOfGiveTheThumbsUp());
        StringBuffer bitmap = new StringBuffer();
        for (int i = 0;i<travel.getBitmaps().size();i++){
            bitmap.append(travel.getBitmaps().get(i));
        }
        contentValues.put("Bitmaps", bitmap.toString());
        contentValues.put("discuss", "");
        database.update("my_travel", contentValues, "Id=?", new String[]{String.valueOf(travel.getId())});
    }

    public TravelsNote.EntityBean findMytravel(String id) {
        Cursor cursor = database.rawQuery("select * from my_travel where Id =?", new String[]{id});
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        String[] bitmap = cursor.getString(cursor.getColumnIndex("Bitmaps")).split(",");
        return new TravelsNote.EntityBean(cursor.getInt(cursor.getColumnIndex("Id"))
                ,cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("head"))
                ,cursor.getString(cursor.getColumnIndex("content")),cursor.getString(cursor.getColumnIndex("inputuser"))
                ,cursor.getString(cursor.getColumnIndex("inputdate")),cursor.getInt(cursor.getColumnIndex("numberOfGiveTheThumbsUp"))
                ,Arrays.asList(bitmap),null);
    }

    public List<TravelsNote.EntityBean> findAllMytravel() {
        List<TravelsNote.EntityBean> list = new ArrayList();
        Cursor cursor = database.rawQuery("select * from my_travel", null);
        if (cursor == null) {
            LogUtil.i("user", "cursor!=null");
        } else {
            while (cursor.moveToNext()) {
                String[] bitmap = cursor.getString(cursor.getColumnIndex("Bitmaps")).split(",");
                list.add(new TravelsNote.EntityBean(cursor.getInt(cursor.getColumnIndex("Id"))
                        ,cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("head"))
                        ,cursor.getString(cursor.getColumnIndex("content")),cursor.getString(cursor.getColumnIndex("inputuser"))
                        ,cursor.getString(cursor.getColumnIndex("inputdate")),cursor.getInt(cursor.getColumnIndex("numberOfGiveTheThumbsUp"))
                        ,Arrays.asList(bitmap),null));
            }
        }
        return list;
    }

    /**
     * 草稿箱的增删改查
     * @param travel
     */
    //coupon(Id integer,name text,head text,content text,inputuser text,inputdate text,numberOfGiveTheThumbsUp integer" +
    //                ",Bitmaps text,discuss text)"
    public void addDraft(TravelsNote.EntityBean travel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", travel.getId());
        contentValues.put("name", travel.getName());
        contentValues.put("head", travel.getHead());
        contentValues.put("content", travel.getContent());
        contentValues.put("inputuser", travel.getInputuser());
        contentValues.put("inputdate", travel.getInputdate());
        contentValues.put("numberOfGiveTheThumbsUp", travel.getNumberOfGiveTheThumbsUp());
        StringBuffer bitmap = new StringBuffer();
        for (int i = 0;i<travel.getBitmaps().size();i++){
            bitmap.append(travel.getBitmaps().get(i));
        }
        contentValues.put("Bitmaps", bitmap.toString());
        contentValues.put("discuss", "");
        database.insert("draft", null, contentValues);
    }


    public void deleteDraft(int Id){
        database.delete("draft", "Id=?", new String[]{String.valueOf(Id)});
    }

    public void updateDraft (TravelsNote.EntityBean travel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", travel.getId());
        contentValues.put("name", travel.getName());
        contentValues.put("head", travel.getHead());
        contentValues.put("content", travel.getContent());
        contentValues.put("inputuser", travel.getInputuser());
        contentValues.put("inputdate", travel.getInputdate());
        contentValues.put("numberOfGiveTheThumbsUp", travel.getNumberOfGiveTheThumbsUp());
        StringBuffer bitmap = new StringBuffer();
        for (int i = 0;i<travel.getBitmaps().size();i++){
            bitmap.append(travel.getBitmaps().get(i));
        }
        contentValues.put("Bitmaps", bitmap.toString());
        contentValues.put("discuss", "");
        database.update("draft", contentValues, "Id=?", new String[]{String.valueOf(travel.getId())});
    }

    public TravelsNote.EntityBean findDraft(String id) {
        Cursor cursor = database.rawQuery("select * from draft where Id =?", new String[]{id});
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        String[] bitmap = cursor.getString(cursor.getColumnIndex("Bitmaps")).split(",");
        return new TravelsNote.EntityBean(cursor.getInt(cursor.getColumnIndex("Id"))
                ,cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("head"))
                ,cursor.getString(cursor.getColumnIndex("content")),cursor.getString(cursor.getColumnIndex("inputuser"))
                ,cursor.getString(cursor.getColumnIndex("inputdate")),cursor.getInt(cursor.getColumnIndex("numberOfGiveTheThumbsUp"))
                ,Arrays.asList(bitmap),null);
    }

    public List<TravelsNote.EntityBean> findAllDraft() {
        List<TravelsNote.EntityBean> list = new ArrayList();
        Cursor cursor = database.rawQuery("select * from draft", null);
        if (cursor == null) {
            LogUtil.i("user", "cursor!=null");
        } else {
            while (cursor.moveToNext()) {
                String[] bitmap = cursor.getString(cursor.getColumnIndex("Bitmaps")).split(",");
                list.add(new TravelsNote.EntityBean(cursor.getInt(cursor.getColumnIndex("Id"))
                        ,cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("head"))
                        ,cursor.getString(cursor.getColumnIndex("content")),cursor.getString(cursor.getColumnIndex("inputuser"))
                        ,cursor.getString(cursor.getColumnIndex("inputdate")),cursor.getInt(cursor.getColumnIndex("numberOfGiveTheThumbsUp"))
                        ,Arrays.asList(bitmap),null));
            }
        }
        return list;
    }
    /**
     * 关闭数据库
     */
    public void close(){
        database.close();
    }

    /**
     * 用户的增删改查
     * @param username
     */
    public void deleteUser(String username) {
        database.delete("user", "username=?", new String[]{username});
    }

    public void addPlace(Place place) {
        LogUtil.e("yanghao");
        //db.execSQL("create table user(username text,password text)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", place.getName());
        contentValues.put("icon", place.getIcon());
        database.insert("place", null, contentValues);
    }

    public List<Place> findLikePlace(String name) {
        List<Place> list = new ArrayList();
        Cursor cursor = database.rawQuery("select * from place where name like?", new String[]{"%" + name + "%"});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(new Place(cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(cursor.getColumnIndex("icon"))));
            }
        }
        return list;
    }

    public Place findPlace(String name) {
        Cursor cursor = database.rawQuery("select * from place where name =?", new String[]{name});
        if (cursor == null || !cursor.moveToNext()) {
            return null;
        }
        return new Place(cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(cursor.getColumnIndex("icon")));
    }

    public boolean haveThing(){
        Place place = findPlace("");
        return place==null;
    }

//    public boolean havePhone(String phone){
//        User user = findUser(phone);
//        return user==null;
//    }
}
