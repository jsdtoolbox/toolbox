package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.model.ListInfo;

import java.util.List;

/**
 * Created by zenghui on 2016/8/3.
 */
public class ListAdapter extends BaseAdapter{

    List<Object> listInfos;
    Context context;
    boolean prominentLast;
    public ListAdapter(Context context, List<Object> listInfos, boolean prominentLast){
        this.listInfos = listInfos;
        this.context = context;
        this.prominentLast = prominentLast;
    }

    @Override
    public int getCount() {
        return listInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return listInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.left = (TextView) convertView.findViewById(R.id.left);
            viewHolder.right = (TextView) convertView.findViewById(R.id.right);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String listInfo = (String) listInfos.get(position);

        viewHolder.left.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.public_textsize_value_13));
        viewHolder.right.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.public_textsize_value_13));


        viewHolder.left.setText(listInfo);
        return convertView;
    }

    class  ViewHolder{

        TextView left;
        TextView right;

    }
}
