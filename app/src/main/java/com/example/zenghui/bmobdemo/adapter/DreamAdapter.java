package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.utils.DialogUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 2016/9/7.
 */
public class DreamAdapter extends BaseAdapter{
    List<Object> list;
    Context context;
    public DreamAdapter(Context context,List<Object> list){
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
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.dream_item,null);
            viewHolder = new ViewHolder();
            viewHolder.describe = (TextView) convertView.findViewById(R.id.describe);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Map<String,Object> data = (Map<String, Object>) list.get(position);
        viewHolder.describe.setText((String)data.get("des"));
        viewHolder.title.setText((String)data.get("title"));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showListDialog(context,(String)data.get("title"),(List<Object>)data.get("list"),false,null);
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView title,describe;
    }
}
