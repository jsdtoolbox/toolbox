package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.model.LaughInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 2016/9/8.
 */
public class LaughAdapter extends BaseAdapter{

    List<Map<String,String>> laughInfos;
    Context context;
    public LaughAdapter( Context context,List<Map<String,String>> laughInfos){
        this.context = context;
        this.laughInfos = laughInfos;
    }
    @Override
    public int getCount() {
        return laughInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return laughInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            textView = (TextView) convertView.findViewById(R.id.left);
            convertView.setTag(textView);
        }else {
            textView = (TextView) convertView.getTag();
        }
        textView.setText(laughInfos.get(position).get("content"));
        return convertView;
    }
}
