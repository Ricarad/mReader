package com.ricarad.app.mreader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ricarad.app.mreader.MyApplication;

import static java.security.AccessController.getContext;

/**
 * Created by dongdong on 2018/3/2.
 */

public class MyDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "book.db";
    public Context mContext;

    public static final String CREATE_BOOK_LIST = "create table book (" +
            "id integer primary key autoincrement," +
            "book_name text," +
            "book_path text)";
    public static final String CREATE_CHAPTER = "create table chapter (" +
            "id integer primary key autoincrement," +
            "book_name text," +
            "chapter_paragraph_position integer," +
            "chapter_byte_position integer," +
            "chapter_name)";

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    public MyDbHelper(){
        super(MyApplication.getContext(),NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_LIST);
        db.execSQL(CREATE_CHAPTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL(CREATE_BOOK_LIST);
            db.execSQL(CREATE_CHAPTER);
        }
    }
}
