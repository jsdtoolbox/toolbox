package com.example.zenghui.bmobdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.BasicActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.LawyerAdapter;
import com.example.zenghui.bmobdemo.model.CookieCallBack;
import com.example.zenghui.bmobdemo.model.LawyerInfo;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.DialogUtil;
import com.example.zenghui.bmobdemo.utils.ITask;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zenghui on 16/8/13.
 */
public class LawyerActivity extends BasicActivity {

    TextView name,dec,mechanismName,phone,qq,address,professional,authNumber;
    ImageView head;
    ListView listView;
    String city;
    boolean isCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lawyer_layout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) findViewById(R.id.title);
        mToolbar.setTitle("");
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText("律师");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        city = getIntent().getStringExtra("city");
        isCity = getIntent().getBooleanExtra("isCity",true);
//        name = (TextView) findViewById(R.id.name);
//        dec = (TextView) findViewById(R.id.dec);
//        mechanismName = (TextView) findViewById(R.id.mechanismName);
//        phone = (TextView) findViewById(R.id.phone);
//        qq = (TextView) findViewById(R.id.qq);
//        address = (TextView) findViewById(R.id.address);
//        professional = (TextView) findViewById(R.id.professional);
//        authNumber = (TextView) findViewById(R.id.authNumber);
//        head = (ImageView) findViewById(R.id.head);
        listView = (ListView) findViewById(R.id.list);
        getLawyer();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//             setInfo(result.get(position));
            }
        });

//        dec.setMovementMethod(new ScrollingMovementMethod());
    }

    List<Map<String,String>> result;
    void getLawyer(){

        DialogUtil.showLoading(this,"获取中...");
        ITask iTask = Common.getTask("http://op.juhe.cn");
        Call<LawyerInfo> call;
        if (isCity) {
            call = iTask.getLawyer("json", 0, 20, city, Common.LAWYER_KEY);
        }else {
            call = iTask.getProLawyer("json", 0, 20, city, Common.LAWYER_KEY);
        }
        call.enqueue(new CookieCallBack<LawyerInfo>(){

            @Override
            public void onResponse(Call<LawyerInfo> call, Response<LawyerInfo> response) {
                super.onResponse(call, response);
                LawyerInfo lawyerInfo = response.body();
                if (lawyerInfo != null && lawyerInfo.getResult() != null&& lawyerInfo.getResult().size() > 0) {
                    result = lawyerInfo.getResult();
                    listView.setAdapter(new LawyerAdapter(LawyerActivity.this, result));
//                    setInfo(lawyerInfo.getResult().get(0));
                }
                DialogUtil.dimissLoading();
            }

            @Override
            public void onFailure(Call<LawyerInfo> call, Throwable t) {
                super.onFailure(call, t);
                DialogUtil.dimissLoading();
            }
        });

    }


    void setInfo(Map<String,String> map){
        name.setText(map.get("name"));
        address.setText(map.get("city"));
        dec.setText(map.get("desp"));
        phone.setText(map.get("mobile"));
        professional.setText(map.get("spec"));
        authNumber.setText(map.get("idno"));
        qq.setText(map.get("qq"));
        mechanismName.setText(map.get("corp"));
        Picasso picasso = Picasso.with(this);
        picasso.setLoggingEnabled(true);
        picasso.load(map.get("img"))
                .noFade()
                .into(head);

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
