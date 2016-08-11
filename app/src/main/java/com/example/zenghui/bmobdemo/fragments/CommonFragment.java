package com.example.zenghui.bmobdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.GrildViewAdapter;
import com.example.zenghui.bmobdemo.model.GrildItemInfo;
import com.example.zenghui.bmobdemo.utils.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenghui on 16/8/10.
 */
public class CommonFragment extends BasicFragment{

    View rootView;
    GridView gridView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.common_fragment,null);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = (GridView) rootView.findViewById(R.id.gridView);

        List<GrildItemInfo> list = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            GrildItemInfo grildItemInfo = new GrildItemInfo();
            grildItemInfo.setDecribe("天气");
            grildItemInfo.setKey(Common.PHONE_ADDRESS_KEY);
            grildItemInfo.setImgSource(R.mipmap.wather);
            list.add(grildItemInfo);
        }
        gridView.setAdapter(new GrildViewAdapter(getActivity(),list));
    }

}
