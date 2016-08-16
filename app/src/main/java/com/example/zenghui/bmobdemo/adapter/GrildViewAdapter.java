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

import com.example.zenghui.bmobdemo.LawyerActivity;
import com.example.zenghui.bmobdemo.PhoneAddressActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.model.GrildItemInfo;
import com.example.zenghui.bmobdemo.model.ListInfo;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.DialogUtil;
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

        final GrildItemInfo grildItemInfo = list.get(position);
        viewHolder.img.setBackgroundResource(grildItemInfo.getImgSource());
        viewHolder.tv.setText(grildItemInfo.getDecribe());

        ((TouchLinearLayout)convertView).setHandleDialogListener(new DialogListener() {
            @Override
            public void handle(String text) {
                if (grildItemInfo.getKey().equals(Common.PHONE_ADDRESS_KEY)) {
                    context.startActivity(new Intent(context, PhoneAddressActivity.class));
                }else if (grildItemInfo.getKey().equals(Common.LAWYER_KEY)){
                    DialogUtil.spinnerWheelDialog(context, "选择城市", false, context.getResources().getStringArray(R.array.province_item), new DialogListener() {
                        @Override
                        public void handle(String text) {
                            Intent intent = new Intent(context, LawyerActivity.class);
                            intent.putExtra("city",text.split("-")[1]);
                            context.startActivity(new Intent(context, LawyerActivity.class));
                        }
                    });
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView tv;
    }
}
