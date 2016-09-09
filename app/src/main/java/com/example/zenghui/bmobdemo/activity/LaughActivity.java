package com.example.zenghui.bmobdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.BasicActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.LaughAdapter;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.model.CommonListResponse;
import com.example.zenghui.bmobdemo.model.CommonResponse;
import com.example.zenghui.bmobdemo.model.CookieCallBack;
import com.example.zenghui.bmobdemo.model.LaughInfo;
import com.example.zenghui.bmobdemo.model.LawyerInfo;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.DialogUtil;
import com.example.zenghui.bmobdemo.utils.ITask;
import com.example.zenghui.bmobdemo.views.RefreshLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zenghui on 2016/9/8.
 */
public class LaughActivity extends BasicActivity {
    RefreshLayout myRefreshListView;
    ListView listView;
    EditText star;
    int startIndex = 1;
    List<Map<String,String>> result = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laugh_layout);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) findViewById(R.id.title);
        star = (EditText) findViewById(R.id.star);
        mToolbar.setTitle("");
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText("开心一笑");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myRefreshListView = (RefreshLayout) findViewById(R.id.myRefreshListView);
        listView = (ListView) findViewById(R.id.list);
        star.setLongClickable(false);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showPicker(LaughActivity.this, "选择开始时间", new DialogListener() {
                    @Override
                    public void handle(String text) {
                        star.setText(text);
                        result.clear();
                        startIndex = 1;
                        loadOver = false;
                        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
                        Date date= null;
                        try {
                            date = simpleDateFormat.parse(text);
                            long timeStemp = date.getTime()/1000;
                            getLaugh(String.valueOf(timeStemp));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(star.getText().toString().trim())){
                    showToast("请输入开始时间");
                    return;
                }
                result.clear();
                startIndex = 1;
                loadOver = false;
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
                Date date= null;
                try {
                    date = simpleDateFormat.parse(star.getText().toString().trim());
                    long timeStemp = date.getTime()/1000;
                    getLaugh(String.valueOf(timeStemp));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        myRefreshListView.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimary));
        myRefreshListView.setColorSchemeColors(R.color.colorAccent,
                Color.GRAY,
                Color.YELLOW,
                R.color.white);
        // 设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {


                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // 更新数据
                        if (TextUtils.isEmpty(star.getText().toString().trim())){
                            showToast("请输入开始时间");
                            myRefreshListView.setRefreshing(false);
                            return;
                        }

                        if (laughAdapter == null){
                            myRefreshListView.setRefreshing(false);
                            return;
                        }
                        loadOver = false;
                        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
                        Date date= null;
                        try {
                            date = simpleDateFormat.parse(star.getText().toString().trim());
                            long timeStemp = date.getTime()/1000;
                            startIndex = 1;
                            getLaugh(String.valueOf(timeStemp));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 500);
            }
        });

        // 加载监听器
        myRefreshListView.setOnLoadListener(new RefreshLayout.OnLoadListener() {

            @Override
            public void onLoad() {

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // 更新数据
                        if (loadOver){
                            showToast("没有更多数据");
                            myRefreshListView.setLoading(false);
                            return;
                        }

                        if (TextUtils.isEmpty(star.getText().toString().trim()) || star.getText().toString().trim().equals("开始时间")){
                            showToast("请输入开始时间");
                            myRefreshListView.setLoading(false);
                            return;
                        }
                        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
                        Date date= null;
                        try {
                            date = simpleDateFormat.parse(star.getText().toString().trim());
                            long timeStemp = date.getTime()/1000;
                            getLaugh(String.valueOf(timeStemp));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000);

            }
        });
    }
    boolean loadOver = false;
    LaughAdapter laughAdapter;
    void getLaugh(String time){
        DialogUtil.showLoading(this,"获取中...");
        ITask iTask = Common.getTask("http://japi.juhe.cn");
        Call<CommonResponse> call = iTask.getLaugh(Common.LAUGH_KEY,startIndex,20,"asc",time);
        call.enqueue(new CookieCallBack<CommonResponse>(){
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                super.onResponse(call, response);
                CommonResponse commonListResponse = response.body();
                if (commonListResponse != null){
                    ArrayList<Map<String,String>> temp = (ArrayList<Map<String,String>>) commonListResponse.getResult().get("data");
                    if (temp != null && temp.size() > 0){

                        if (laughAdapter == null){
                            result.addAll(temp);
                            laughAdapter = new LaughAdapter(getBaseContext(),result);
                            listView.setAdapter(laughAdapter);
                        }else {
                            if (startIndex == 1){
                                result.clear();
                            }
                            result.addAll(temp);
                            laughAdapter.notifyDataSetChanged();
                        }
                        startIndex += 1;
                    }else {
                        loadOver = true;
                    }
                }
                myRefreshListView.setLoading(false);
                myRefreshListView.setRefreshing(false);
                DialogUtil.dimissLoading();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                super.onFailure(call, t);
                DialogUtil.dimissLoading();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
