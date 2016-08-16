package com.example.zenghui.bmobdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.BasicActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.model.CookieCallBack;
import com.example.zenghui.bmobdemo.model.PhoneResponse;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.DialogUtil;
import com.example.zenghui.bmobdemo.utils.ITask;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zenghui on 16/8/11.
 */
public class PhoneAddressActivity extends BasicActivity {
    private Toolbar mToolbar;
    private TextView mTitle;
    private EditText phone;
    private Button btnSearch;
    private TextView tvPhone, tvCity;
    private TextView tvEmail, tvOperator;
    private TextView tvPrompt;
    private RelativeLayout rlPrompt;
    private LinearLayout lytSearchResult;

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


        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvOperator = (TextView) findViewById(R.id.tv_operator);
        tvPrompt = (TextView) findViewById(R.id.tv_prompt);
        rlPrompt = (RelativeLayout) findViewById(R.id.rl_prompt);
        lytSearchResult = (LinearLayout) findViewById(R.id.lyt_search_result);

        phone = (EditText) findViewById(R.id.phone);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Common.isPhoneValid(phone.getText().toString().trim())) {
                    showToast("请输入正确的手机号");
                    return;
                }

                DialogUtil.showLoading(PhoneAddressActivity.this, "查询中...");
                ITask iTask = Common.getTask(Common.DOMAIN);
                Call<PhoneResponse> call = iTask.getPhoneAddress(phone.getText().toString().trim(), Common.PHONE_ADDRESS_KEY);
                call.enqueue(new CookieCallBack<PhoneResponse>() {
                    @Override
                    public void onResponse(Call<PhoneResponse> call, Response<PhoneResponse> response) {
                        super.onResponse(call, response);
                        Log.d("", "ddddd");
                        PhoneResponse phoneResponse = response.body();
                        if (phoneResponse != null) {
                            if (phoneResponse.getResultcode().equals("200")) {
                                rlPrompt.setVisibility(View.GONE);
                                lytSearchResult.setVisibility(View.VISIBLE);
                                tvPhone.setText(phone.getText().toString());
                                tvCity.setText(phoneResponse.getResult().get("province") + "-" + phoneResponse.getResult().get("city"));
                                tvEmail.setText(phoneResponse.getResult().get("areacode") + "  " + phoneResponse.getResult().get("zip"));
                                tvOperator.setText(phoneResponse.getResult().get("company") + "  " + phoneResponse.getResult().get("card"));
                            } else {
                                rlPrompt.setVisibility(View.VISIBLE);
                                lytSearchResult.setVisibility(View.GONE);
                                showToast("数据异常");
                                tvPrompt.setText("数据出错啦！换个号码试试~~");
//                                tvPhone.setText(phone.getText().toString());
//                                tvCity.setText("数据异常");
                            }
                        } else {
                            rlPrompt.setVisibility(View.VISIBLE);
                            lytSearchResult.setVisibility(View.GONE);
                            showToast("数据异常");
                            tvPrompt.setText("数据出错啦！换个号码试试~~");
//                            tvPhone.setText(phone.getText().toString());
//                            tvCity.setText("数据异常");
                        }
                        DialogUtil.dimissLoading();
                    }

                    @Override
                    public void onFailure(Call<PhoneResponse> call, Throwable t) {
                        super.onFailure(call, t);
                        DialogUtil.dimissLoading();
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
