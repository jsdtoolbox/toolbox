package com.example.zenghui.bmobdemo.utils;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.ListAdapter;
import com.example.zenghui.bmobdemo.adapter.SpinnerGroupAdapter;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.model.ListInfo;
import com.example.zenghui.bmobdemo.spinnerwheel.AbstractWheel;
import com.example.zenghui.bmobdemo.spinnerwheel.OnWheelScrollListener;
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



    static String[] rightArr = null;
    @TargetApi(Build.VERSION_CODES.M)
    public static void spinnerWheelDialog(final Context con,
                                          boolean cancelable, final String[] leftArr, final DialogListener handle) {
        final Dialog mDialog = new Dialog(con, R.style.CashBusDialog);
        mDialog.setContentView(R.layout.spinnerwheel_layout);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(cancelable);
        mDialog.getWindow().setLayout((int) (Common.screem_width * 0.8), (int) (Common.screem_height * 0.4));
        final AbstractWheel left = (AbstractWheel) mDialog.findViewById(R.id.left);
        final AbstractWheel right = (AbstractWheel) mDialog.findViewById(R.id.right);


        final int[] status = new int[]{1};

        Button confirm = (Button) mDialog.findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status[0] == 0) {
                    return;
                }

                if (rightArr == null || rightArr.length == 0) {
                    handle.handle(leftArr[left.getCurrentItem()]);
                } else {
                    handle.handle(leftArr[left.getCurrentItem()] +"-"+ rightArr[right.getCurrentItem()]);

                }
                rightArr = null;
                mDialog.dismiss();

            }
        });

        left.setCyclic(false);
        left.setVisibleItems(5);
        left.setCurrentItem(1);
        left.setViewAdapter(new SpinnerGroupAdapter(con, leftArr,left));

        left.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {
                status[0] = 0;
            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {


                switch (left.getCurrentItem()) {

                    case 0:
                        rightArr = con.getResources().getStringArray(R.array.beijin_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));
                        break;

                    case 1:
                        rightArr = con.getResources().getStringArray(R.array.tianjin_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, con.getResources().getStringArray(R.array.tianjin_province_item),right));
                        break;

                    case 2:
                        rightArr = con.getResources().getStringArray(R.array.heibei_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 3:
                        rightArr = con.getResources().getStringArray(R.array.shanxi_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 4:
                        rightArr = con.getResources().getStringArray(R.array.neimenggu_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 5:
                        rightArr = con.getResources().getStringArray(R.array.liaoning_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 6:
                        rightArr = con.getResources().getStringArray(R.array.jilin_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;


                    case 7:
                        rightArr = con.getResources().getStringArray(R.array.heilongjiang_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 8:
                        rightArr = con.getResources().getStringArray(R.array.shanghai_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 9:
                        rightArr = con.getResources().getStringArray(R.array.jiangsu_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 10:
                        rightArr = con.getResources().getStringArray(R.array.zhejiang_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 11:
                        rightArr = con.getResources().getStringArray(R.array.anhui_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 12:
                        rightArr = con.getResources().getStringArray(R.array.fujian_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 13:
                        rightArr = con.getResources().getStringArray(R.array.jiangxi_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));


                        break;

                    case 14:
                        rightArr = con.getResources().getStringArray(R.array.shandong_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 15:
                        rightArr = con.getResources().getStringArray(R.array.henan_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 16:
                        rightArr = con.getResources().getStringArray(R.array.hubei_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 17:
                        rightArr = con.getResources().getStringArray(R.array.hunan_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 18:
                        rightArr = con.getResources().getStringArray(R.array.guangdong_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;

                    case 19:
                        rightArr = con.getResources().getStringArray(R.array.guangxi_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 20:
                        rightArr = con.getResources().getStringArray(R.array.hainan_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 21:
                        rightArr = con.getResources().getStringArray(R.array.chongqing_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 22:
                        rightArr = con.getResources().getStringArray(R.array.sichuan_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 23:
                        rightArr = con.getResources().getStringArray(R.array.guizhou_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 24:
                        rightArr = con.getResources().getStringArray(R.array.yunnan_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 25:
                        rightArr = con.getResources().getStringArray(R.array.xizang_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 26:
                        rightArr = con.getResources().getStringArray(R.array.shanxi2_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 27:
                        rightArr = con.getResources().getStringArray(R.array.gansu_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 28:
                        rightArr = con.getResources().getStringArray(R.array.qinghai_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 29:
                        rightArr = con.getResources().getStringArray(R.array.linxia_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 30:
                        rightArr = con.getResources().getStringArray(R.array.xinjiang_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 31:
                        rightArr = con.getResources().getStringArray(R.array.hongkong_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 32:
                        rightArr = con.getResources().getStringArray(R.array.aomen_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                    case 33:
                        rightArr = con.getResources().getStringArray(R.array.taiwan_province_item);
                        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));

                        break;
                }

                if (rightArr.length > 0) {
                    right.setCurrentItem(0);
                }

                status[0] = 1;


            }
        });

        right.setCyclic(false);
        right.setVisibleItems(5);
        rightArr = con.getResources().getStringArray(R.array.beijin_province_item);
        right.setViewAdapter(new SpinnerGroupAdapter(con, rightArr,right));
        right.setCurrentItem(0);
        mDialog.show();
    }
}
