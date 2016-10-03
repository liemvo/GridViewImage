package com.android.vad.gridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.vad.gridview.R;
import com.android.vad.gridview.model.ImageObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by liemvo on 10/3/16.
 */
public class MyGridAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<ImageObject> imageObjects;

    private LayoutInflater mLayoutInflate;


    public MyGridAdapter (Context context, ArrayList<ImageObject> imageObjects){
        this.context = context;
        this.imageObjects = imageObjects;

        this.mLayoutInflate = LayoutInflater.from(context);
    }

    public int getCount() {
        if(imageObjects != null) return  imageObjects.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(imageObjects != null && imageObjects.size() > position) return  imageObjects.get(position);

        return null;
    }

    @Override
    public long getItemId(int position) {
        if(imageObjects != null && imageObjects.size() > position) return  imageObjects.get(position).getId();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = mLayoutInflate.inflate(R.layout.imageitem, parent,
                    false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageObject imageObject = (ImageObject) getItem(position);
        if(imageObject != null) {
            Glide
                    .with(context)
                    .load(imageObject.getImageUrl())
                    .centerCrop()
                    .crossFade()
                    .into(viewHolder.imageView);
        }

        return convertView;
    }

    private class ViewHolder {
        public ImageView imageView;
    }
}
