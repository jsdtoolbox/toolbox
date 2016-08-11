package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zenghui.bmobdemo.PhoneAddressActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.model.GrildItemInfo;
import com.example.zenghui.bmobdemo.views.TouchLinearLayout;

import java.util.List;

/**
 * Created by zenghui on 16/8/10.
 */
public class GrildViewAdapter extends BaseAdapter{

    private Context context;
    private List<GrildItemInfo> list;

   public GrildViewAdapter(Context context,List<GrildItemInfo> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grild_item,null);

            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setBackgroundResource(list.get(position).getImgSource());
        viewHolder.tv.setText(list.get(position).getDecribe());

        ((TouchLinearLayout)convertView).setHandleDialogListener(new DialogListener() {
            @Override
            public void handle(String text) {
                context.startActivity(new Intent(context, PhoneAddressActivity.class));
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView tv;
    }
}
