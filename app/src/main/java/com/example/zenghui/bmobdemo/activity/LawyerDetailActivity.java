package com.example.zenghui.bmobdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.model.LawyerInfo.LawyerInfoItem;
import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * Created by MMF on 2016/8/16.
 */
public class LawyerDetailActivity extends BasicActivity {

    TextView name,city, dec, mechanismName, phone, qq, address, professional, authNumber;
    ImageView head;
    private LinearLayout lytMechanismName;
    private LinearLayout lytProfessional;
    private LinearLayout lytAddress;
    private LinearLayout lytAuthNumber;
    private LinearLayout lytPhone;
    private LinearLayout lytQq;
    private LinearLayout lytDec;

private LawyerInfoItem mLawyerInfoItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lawyer_detail_layout);
        initView();
    }

    private void initView() {
        mLawyerInfoItem = (LawyerInfoItem)getIntent().getSerializableExtra("item");
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) findViewById(R.id.title);
        mToolbar.setTitle("");
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText("律师");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.name);
        city = (TextView) findViewById(R.id.city);
        dec = (TextView) findViewById(R.id.dec);
        mechanismName = (TextView) findViewById(R.id.mechanismName);
        phone = (TextView) findViewById(R.id.phone);
        qq = (TextView) findViewById(R.id.qq);
        address = (TextView) findViewById(R.id.address);
        professional = (TextView) findViewById(R.id.professional);
        authNumber = (TextView) findViewById(R.id.authNumber);
        head = (ImageView) findViewById(R.id.head);
        lytMechanismName = (LinearLayout) findViewById(R.id.lyt_mechanismName);
        lytProfessional = (LinearLayout) findViewById(R.id.lyt_professional);
        lytAddress = (LinearLayout) findViewById(R.id.lyt_address);
        lytAuthNumber = (LinearLayout) findViewById(R.id.lyt_authNumber);
        lytPhone = (LinearLayout) findViewById(R.id.lyt_phone);
        lytQq = (LinearLayout) findViewById(R.id.lyt_qq);
        lytDec = (LinearLayout) findViewById(R.id.lyt_dec);
        dec.setMovementMethod(new ScrollingMovementMethod());
        setInfo();
    }


    private void setInfo(){
        name.setText(mLawyerInfoItem.getName());
        address.setText(mLawyerInfoItem.getCity());
        dec.setText(mLawyerInfoItem.getDesp());
        phone.setText(mLawyerInfoItem.getMobile());
        professional.setText(mLawyerInfoItem.getSpec());
        authNumber.setText(mLawyerInfoItem.getIdno());
        qq.setText(mLawyerInfoItem.getQq());
        mechanismName.setText(mLawyerInfoItem.getCorp());
        Picasso picasso = Picasso.with(this);
        picasso.setLoggingEnabled(true);
        picasso.load(mLawyerInfoItem.getImg())
                .noFade()
                .into(head);

    }
}
