package com.example.zenghui.bmobdemo.fragments;

/**
 * Created by zenghui on 16/4/29.
 */

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.HomeFragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerFragment extends BasicFragment {
    Resources resources;
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine;
    private TextView tvTabNew, tvTabHot,tvTabThree;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    public  int num = 3 ;

    String[] titleArr;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.first_fragment, null);
        resources = getResources();
//        TranslateAnimation animation = new TranslateAnimation(0, offset, 0, 0);
//        tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
//        animation.setFillAfter(true);
//        animation.setDuration(10);
//        ivBottomLine.startAnimation(animation);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitWidth(rootView);
        InitTextView(rootView);

        mPager = (ViewPager) rootView.findViewById(R.id.vPager);
        mPager.setAdapter(new HomeFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mPager.setCurrentItem(0);
    }

    private void InitTextView(View parentView) {


        tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);
        tvTabHot = (TextView) parentView.findViewById(R.id.tv_tab_2);
        tvTabThree = (TextView) parentView.findViewById(R.id.tv_tab_3);

        tvTabNew.setTextColor(resources.getColor(R.color.top_title_blue_theme));
        tvTabHot.setTextColor(resources.getColor(R.color.top_title_blue_theme));
        tvTabThree.setTextColor(resources.getColor(R.color.top_title_blue_theme));

        if (fragmentsList.size() == 1){
            tvTabNew.setOnClickListener(new MyOnClickListener(0));
            tvTabNew.setText(titleArr[0]);
            tvTabHot.setVisibility(View.GONE);
            tvTabThree.setVisibility(View.GONE);
        }else if (fragmentsList.size() == 2){
            tvTabNew.setText(titleArr[0]);
            tvTabHot.setText(titleArr[1]);
            tvTabNew.setOnClickListener(new MyOnClickListener(0));
            tvTabHot.setOnClickListener(new MyOnClickListener(1));
            tvTabThree.setVisibility(View.GONE);
        }else if (fragmentsList.size() == 3){
            tvTabNew.setText(titleArr[0]);
            tvTabHot.setText(titleArr[1]);
            tvTabThree.setText(titleArr[2]);
            tvTabNew.setOnClickListener(new MyOnClickListener(0));
            tvTabHot.setOnClickListener(new MyOnClickListener(1));
            tvTabThree.setOnClickListener(new MyOnClickListener(2));
        }

    }

    public void InitViewPager(ArrayList<Fragment> fragmentsList, String[] titleArr) {
        this.fragmentsList = fragmentsList;
        this.titleArr = titleArr;
        num = fragmentsList.size();

    }

    int avg;
    private void InitWidth(View parentView) {
        ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
        bottomLineWidth = getResources().getDimensionPixelSize(R.dimen.public_space_value_40);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = ((screenW / num - bottomLineWidth) / 2);
        avg = (screenW / num);
        ivBottomLine.setX(offset);

    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
//            switch (arg0) {
//                case 0:
//                    if (currIndex == 1) {
//                        animation = new TranslateAnimation(avg, 0, 0, 0);
//                    }else if(currIndex == 2){
//                        animation = new TranslateAnimation(avg*2, 0, 0, 0);
//                    }
//
//                    break;
//                case 1:
//                    if (currIndex == 0) {
//                        animation = new TranslateAnimation(0, avg, 0, 0);
//                    }else if(currIndex == 2){
//                        animation = new TranslateAnimation(avg*2, avg, 0, 0);
//                    }
//
//                    break;
//                case 2:
//                    if (currIndex == 0) {
//                        animation = new TranslateAnimation(0, avg*2, 0, 0);
//                    }else if(currIndex == 1){
//                        animation = new TranslateAnimation(avg, avg*2, 0, 0);
//                    }
//
//                    break;
//            }
            animation = new TranslateAnimation(avg*currIndex, avg*arg0, 0, 0);
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}