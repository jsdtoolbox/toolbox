package com.example.zenghui.bmobdemo.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.BasicActivity;
import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.fragments.CommonFragment;
import com.example.zenghui.bmobdemo.fragments.DebitFragment;
import com.example.zenghui.bmobdemo.fragments.ViewPagerFragment;
import com.example.zenghui.bmobdemo.listener.DialogListener;
import com.example.zenghui.bmobdemo.model.ListInfo;
import com.example.zenghui.bmobdemo.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BasicActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;
    ArrayList<Fragment> fragments;
    ViewPagerFragment fragment;
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvTitle = (TextView) findViewById(R.id.title);
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("");
//        setSupportActionBar(mToolbar);
        //第一：默认初始化
//        Bmob.initialize(this, "1aa22fd76dd576e17837b77ea658e4c4");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);



        fragmentManager = getSupportFragmentManager();
//        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
//        ((RadioButton)radioGroup.findViewById(R.id.imgOne)).setChecked(true);

        transaction = fragmentManager.beginTransaction();

//        tvTitle.setText("筛选");
        fragment = new ViewPagerFragment();
        fragments = new ArrayList<>();
        fragments.add(new CommonFragment());
        fragments.add(new DebitFragment());
        fragments.add(new DebitFragment());
        fragment.InitViewPager(fragments,new String[]{"常用工具","公募筹款","债权转让"});
        transaction.replace(R.id.contentView, fragment);
        transaction.commit();
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.imgOne:
//
//                        transaction = fragmentManager.beginTransaction();
//                        transaction.replace(R.id.contentView, fragment);
//                        transaction.commit();
//                        break;
//                    case R.id.imgTwo:
//
//                        transaction = fragmentManager.beginTransaction();
//                        Fragment personFragment = new DebitFragment();
//                        transaction.replace(R.id.contentView, personFragment);
//                        transaction.commit();
//                        break;
//                }
//
//            }
//        });


//        Person p2 = new Person();
//        p2.setName("lucky");
//        p2.setAddress("北京海淀");
//        p2.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId,BmobException e) {
//                if(e==null){
//                    showToast("添加数据成功，返回objectId为："+objectId);
//                }else{
//                    showToast("创建数据失败：" + e.getMessage());
//                }
//            }
//        });
//        List<ListInfo> listInfos = new ArrayList<>();
//        for (int i = 0;i< 10;i++){
//            ListInfo listInfo = new ListInfo();
//            listInfo.setLeft("ssds");
//            listInfo.setRight("dsfcsdv");
//            listInfos.add(listInfo);
//        }
//
//        DialogUtil.showListDialog(this,"测试",listInfos,false,new DialogListener(){
//            @Override
//            public void handle(String text) {
//
//            }
//        });
    }


}
