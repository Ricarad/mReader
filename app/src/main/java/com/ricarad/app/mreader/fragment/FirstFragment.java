package com.ricarad.app.mreader.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.ricarad.app.mreader.R;
import com.ricarad.app.mreader.adapter.BookShelfAdapter;
import com.ricarad.app.mreader.bean.Book;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by dongdong on 2018/3/17.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    private GridView bookshelf;
   // private Button leftbt;
   // private Button rightbt;
    private FloatingActionButton fab;
    private List<Book> bookList = new ArrayList<Book>();
    private BookShelfAdapter bsAdapter;
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    private BufferedReader br;
    String []names = new String[]{"奥古斯都之路之穿石伍迪","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1","目录1",};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.layout_first, container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookshelf = (GridView)getActivity().findViewById(R.id.layout_first_gv_bookshelf);
        //leftbt = (Button)getActivity().findViewById(R.id.head_bt_left);
        //rightbt = (Button)getActivity().findViewById(R.id.head_bt_right);
        //leftbt.setOnClickListener(this);
        //rightbt.setOnClickListener(this);
        fab = (FloatingActionButton)getActivity().findViewById(R.id.layout_first_fab);
        fab.setOnClickListener(this);
        initData();
        bsAdapter = new BookShelfAdapter(getContext(),R.layout.item,bookList);
        bookshelf.setAdapter(bsAdapter);
    }
    public void initData(){
        for (int i = 0;i<names.length;i++){
            Book book = new Book(R.mipmap.book_default,names[i]);
            bookList.add(book);
        }

    }
    protected void loadDataFromDb(){

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.head_bt_left:{}break;
            case R.id.head_bt_right:{
            }break;
            case R.id.layout_first_fab:{
                new LFilePicker()
                        .withActivity(getActivity())
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
