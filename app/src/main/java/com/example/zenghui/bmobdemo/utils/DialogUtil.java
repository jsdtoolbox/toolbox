package com.example.zenghui.bmobdemo.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.ListAdapter;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.model.ListInfo;
import com.example.zenghui.bmobdemo.views.BmobDialog;

import java.util.List;

/**
 * Created by zenghui on 16/8/10.
 */
public class DialogUtil {

    public static void showListDialog(final Context con, String title, final List<ListInfo> listInfos, final boolean prominentLast, final DialogListener dialogListener){
        final Dialog mDialog = new Dialog(con, R.style.CashBusDialog);
        mDialog.setContentView(R.layout.list_layout);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        TextView titleTv = (TextView) mDialog.findViewById(R.id.title);
        TextView iGotIt = (TextView) mDialog.findViewById(R.id.iGotIt);
        final ListView listView = (ListView) mDialog.findViewById(R.id.list);

        titleTv.setText(title);

        listView.setAdapter(new ListAdapter(con,listInfos,prominentLast));

        listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                listView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int size = con.getResources().getDimensionPixelSize(R.dimen.public_space_value_200);
                if (listView.getHeight() > size) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) listView.getLayoutParams();
                    layoutParams.height = size;
                    listView.setLayoutParams(layoutParams);
                    listView.setAdapter((new ListAdapter(con,listInfos,prominentLast)));
                }
            }
        });

        if (dialogListener != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mDialog.dismiss();
                    dialogListener.handle(listInfos.get(position).getLeft());
                }
            });
        }
        iGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.show();
    }
    public static BmobDialog bmobDialog;
    public static void showLoading(Context context,String content){

        if (bmobDialog != null && bmobDialog.isShowing()){
            return;
        }
        bmobDialog = new BmobDialog(context, content);
        bmobDialog.setCancelable(false);
        bmobDialog.show();
    }

    public static void dimissLoading(){

        if (bmobDialog != null && bmobDialog.isShowing()){
            bmobDialog.dismiss();
        }

    }
}
