package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by zenghui on 16/8/13.
 */
public class LawyerAdapter extends BaseAdapter {

    List<Map<String, String>> list;
    Context context;
    Picasso picasso;

    public LawyerAdapter(Context context, List<Map<String, String>> list) {
        this.context = context;
        this.list = list;
        picasso = Picasso.with(context);
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lawyer_item, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.ivHead);
            viewHolder.corp = (TextView) convertView.findViewById(R.id.tvCorp);
            viewHolder.spec = (TextView) convertView.findViewById(R.id.tvSpec);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Map<String, String> map = list.get(position);
        picasso.setLoggingEnabled(true);
        picasso.load(map.get("img"))
                .noFade()
                .into(viewHolder.img);
        viewHolder.corp.setText("公司：" + map.get("corp"));
        viewHolder.spec.setText("职业能力："+map.get("spec"));
        viewHolder.name.setText(map.get("name").substring(0, map.get("name").length() - 2));
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView corp, name, spec;
    }
}
