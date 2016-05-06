package com.murphy.lifecoaching;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2/20/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<ItemSlideMenu> listItems;

    public SlidingMenuAdapter(Context mContext, List<ItemSlideMenu> listItems) {
        this.mContext = mContext;
        this.listItems = listItems;
    }
    @Override
    public int getCount(){
        return listItems.size();
    }
    @Override
    public Object getItem(int position){
        return listItems.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = View.inflate(mContext, R.layout.drawer_item, null);
        ImageView img = (ImageView)v.findViewById(R.id.item_img);
        TextView tv = (TextView)v.findViewById(R.id.item_title);

        ItemSlideMenu item = listItems.get(position);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());
        return v;
    }
}
