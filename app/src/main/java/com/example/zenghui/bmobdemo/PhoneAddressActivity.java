package com.example.zenghui.bmobdemo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.model.CookieCallBack;
import com.example.zenghui.bmobdemo.model.PhoneResponse;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.ITask;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zenghui on 16/8/11.
 */
public class PhoneAddressActivity extends BasicActivity{
    private Toolbar mToolbar;
    private TextView mTitle;
    EditText phone;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_address_layout);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.title);
        mToolbar.setTitle("");
        mTitle.setText("归属地查询");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phone = (EditText) findViewById(R.id.phone);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ITask iTask = Common.getTask(Common.DOMAIN);
                Call<PhoneResponse> call = iTask.getPhoneAddress(phone.getText().toString().trim(),Common.PHONE_ADDRESS_KEY);
                call.enqueue(new CookieCallBack<PhoneResponse>(){
                    @Override
                    public void onResponse(Call<PhoneResponse> call, Response<PhoneResponse> response) {
                        super.onResponse(call, response);
                        Log.d("","ddddd");
                    }

                    @Override
                    public void onFailure(Call<PhoneResponse> call, Throwable t) {
                        super.onFailure(call, t);
                    }
                });
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
