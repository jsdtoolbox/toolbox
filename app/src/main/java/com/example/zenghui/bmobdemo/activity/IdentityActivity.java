package com.example.zenghui.bmobdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.BasicActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.model.CookieCallBack;
import com.example.zenghui.bmobdemo.model.CommonResponse;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.DialogUtil;
import com.example.zenghui.bmobdemo.utils.ITask;
import com.example.zenghui.bmobdemo.utils.IdcardValidator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zenghui on 2016/9/7.
 */
public class IdentityActivity extends BasicActivity{

    TextView tv_address,tv_date,tv_sexy,tv_indentity;
    LinearLayout lyt_search_result;
    EditText identity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identity_layout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) findViewById(R.id.title);
        mToolbar.setTitle("");
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText("律师");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lyt_search_result = (LinearLayout) findViewById(R.id.lyt_search_result);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_sexy = (TextView) findViewById(R.id.tv_sexy);
        tv_indentity = (TextView) findViewById(R.id.tv_indentity);
        identity = (EditText) findViewById(R.id.identity);

        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new IdcardValidator().isValidatedAllIdcard(identity.getText().toString().trim())){
                    getIdentityInfo(identity.getText().toString().trim());
                }else {
                    showToast("请输入正确的身份证");
                }
            }
        });
    }


    void getIdentityInfo(final String identity){
        DialogUtil.showLoading(this, "获取中...");
        ITask iTask = Common.getTask("http://apis.juhe.cn");
        Call<CommonResponse> call = iTask.getIdentity(Common.IDENTITY_KEY,identity);
        call.enqueue(new CookieCallBack<CommonResponse>(){
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                super.onResponse(call, response);
                CommonResponse identityInfo = response.body();
                if (identityInfo != null && identityInfo.getResultcode().equals("200")){
                    lyt_search_result.setVisibility(View.VISIBLE);
                    tv_address.setText((String) identityInfo.getResult().get("area"));
                    tv_date.setText((String)identityInfo.getResult().get("birthday"));
                    tv_sexy.setText((String)identityInfo.getResult().get("sex"));
                    tv_indentity.setText(identity);
                }
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
