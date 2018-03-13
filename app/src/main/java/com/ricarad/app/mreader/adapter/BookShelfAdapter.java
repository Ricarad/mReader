package com.ricarad.app.mreader.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricarad.app.mreader.R;
import com.ricarad.app.mreader.bean.Book;


import java.util.List;

/**
 * Created by dongdong on 2018/2/28.
 */

public class BookShelfAdapter extends ArrayAdapter {
    private int resourceId;
    private Context context;
    public BookShelfAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        Book book = (Book)getItem(position);
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder.mImageView = (ImageView)view.findViewById(R.id.item_iv_bookimg);
            viewHolder.mTextView = (TextView)view.findViewById(R.id.item_tv_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mTextView.setText(book.getName());
        viewHolder.mImageView.setImageResource(book.getImageId());

        return view;
    }
}

class ViewHolder{
    ImageView mImageView;
    TextView mTextView;
}
