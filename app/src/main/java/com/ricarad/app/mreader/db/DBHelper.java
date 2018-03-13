package com.ricarad.app.mreader.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ricarad.app.mreader.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongdong on 2018/3/2.
 */

public class DBHelper {
    private static DBHelper instance;
    private SQLiteDatabase db;
    private DBHelper(){
       db = new MyDbHelper().getWritableDatabase();
    }
    public static DBHelper getInstance(){
        if(instance == null){
            synchronized(DBHelper.class){
                if(instance == null){
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public List<Book> getAllBook(){
        //Cursor cursor = db.rawQuery("SELECT * FROM book order by access_time desc",null);
        Cursor cursor = db.query("book",null,null,null,null,null,null);
        List<Book> list = new ArrayList<>();
        Book book;
        while (cursor.moveToNext()){
            book = new Book();
            book.setName(cursor.getString(cursor.getColumnIndex("book_name")));
            book.setPath(cursor.getString(cursor.getColumnIndex("book_path")));;
            list.add(book);
        }
        cursor.close();
        return list;
    }

    public void saveBook(Book book){
        ContentValues values = new ContentValues();
        values.put("book_name",book.getName());
        values.put("book_path",book.getPath());
        db.insert("book",null,values);
    }
    public void saveBook(final List<Book> books){
       new Thread(new Runnable() {
           @Override
           public void run() {
               for (Book book : books){
                   saveBook(book);
               }
           }
       });
    }

}
