package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 16/8/13.
 */
public class LawyerAdapter extends BaseAdapter{

    List<Map<String,String>> list;
    Context context;
    public LawyerAdapter(Context context,List<Map<String,String>> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lawyer_item,null);
            viewHolder = new ViewHolder();
            viewHolder.city = (TextView) convertView.findViewById(R.id.tvCity);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Map<String,String> map = list.get(position);

        viewHolder.city.setText(map.get("city")+"-");
        viewHolder.name.setText(map.get("name").substring(0,map.get("name").length()-2));
        return convertView;
    }

    class ViewHolder{
        TextView city,name;
    }
}
