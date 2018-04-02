package com.sonhoai.sonho.quanlythuchi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String DBNAME = "ThuChi.db";
    public static String THUCHI_TABLE_NAME = "ThuChi";
    public static String THUCHI_ID = "id";
    public static String THUCHI_CONTENT = "Content";
    public static String THUCHI_AMOUNT = "amount";
    public static String THUCHI_TYPE = "type";
    public static String THUCHI_CREATETABLE = "CREATE TABLE IF NOT EXISTS "+THUCHI_TABLE_NAME+" ("
            + THUCHI_ID + " INTEGER PRIMARY KEY autoincrement, "
            + THUCHI_CONTENT + " TEXT, "
            + THUCHI_AMOUNT +" INTEGER, "
            + THUCHI_TYPE + " INTEGER)";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public long add(List<ThuChi> thuChi){
        SQLiteDatabase db = getWritableDatabase();
        int count=0;
        ContentValues contentValues;
        for(ThuChi thuChis:thuChi){
            contentValues=new ContentValues();
            contentValues.put(DBHelper.THUCHI_CONTENT,thuChis.getContent());
            contentValues.put(DBHelper.THUCHI_AMOUNT,thuChis.getAmount());
            contentValues.put(DBHelper.THUCHI_TYPE,thuChis.getType());
            if(db.insert(DBHelper.THUCHI_TABLE_NAME,null,contentValues)!=-1)
                count++;
        }
        return count;
    }
    public List<ThuChi> getData(){
        SQLiteDatabase db = getReadableDatabase();
        List<ThuChi> thuChiList = new ArrayList<>();
        //table, ds cac column, va cac dieu kien khac
        Cursor cursor = db.query(
                DBHelper.THUCHI_TABLE_NAME,
                new String[]{DBHelper.THUCHI_ID,DBHelper.THUCHI_CONTENT,DBHelper.THUCHI_AMOUNT,DBHelper.THUCHI_TYPE},
                null,null,null,null,null);
        while (cursor.moveToNext()){
            thuChiList.add(new ThuChi(
               cursor.getInt(cursor.getColumnIndex(DBHelper.THUCHI_ID)),
               cursor.getString(cursor.getColumnIndex(DBHelper.THUCHI_CONTENT)),
               cursor.getInt(cursor.getColumnIndex(DBHelper.THUCHI_AMOUNT)),
               cursor.getInt(cursor.getColumnIndex(DBHelper.THUCHI_TYPE))
            ));
        }
        return thuChiList;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(THUCHI_CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
