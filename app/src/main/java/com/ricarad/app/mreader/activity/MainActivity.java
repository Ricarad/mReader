package com.ricarad.app.mreader.activity;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.ricarad.app.mreader.R;
import com.ricarad.app.mreader.bean.Book;
import com.ricarad.app.mreader.adapter.BookShelfAdapter;


import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private GridView bookshelf;
    private Button leftbt;
    private Button rightbt;
    private List<Book> bookList = new ArrayList<Book>();
    private BookShelfAdapter bsAdapter;
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    private BufferedReader br;
    String []names = new String[]{"奥古斯都之路之穿石伍迪","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bookshelf = (GridView)findViewById(R.id.main_gv_bookshelf);
        leftbt = (Button)findViewById(R.id.head_bt_left);
        rightbt = (Button)findViewById(R.id.head_bt_right);
        leftbt.setOnClickListener(this);
        rightbt.setOnClickListener(this);
        initData();
        bsAdapter = new BookShelfAdapter(MainActivity.this,R.layout.item,bookList);
        bookshelf.setAdapter(bsAdapter);
    }

    public void initData(){
        for (int i = 0;i<names.length;i++){
           // Book book = new Book(imgs[i],names[i]);
           Book book = new Book(R.mipmap.book_default,names[i]);
           bookList.add(book);
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.head_bt_left:{}break;
            case R.id.head_bt_right:{
                new LFilePicker()
                        .withActivity(MainActivity.this)
                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                        .withMutilyMode(true)
                        .withStartPath("/storage")//指定初始显示路径
                        .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                        .withFileFilter(new String[]{".txt"})
                        .withTitle("导入txt")
                        .withFileSize(500 * 1024)//指定文件大小为500K
                        .start();
            }break;
        }
    }
    protected void loadDataFromDb(){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                //如果是文件选择模式，需要获取选择的所有文件的路径集合
                //List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);//Constant.RESULT_INFO == "paths"
                List<String> list = data.getStringArrayListExtra("paths");
                //Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
                for(String path : list){
                    File f = new File(path);
                    String name = f.getName();
                    Book book = new Book(00,name,path);
                }

                //如果是文件夹选择模式，需要获取选择的文件夹路径
                //String path = data.getStringExtra("path");
                //makeText(getApplicationContext(), "选中的路径为" + path, Toast.LENGTH_SHORT).show();
            }
        }

    }
}

