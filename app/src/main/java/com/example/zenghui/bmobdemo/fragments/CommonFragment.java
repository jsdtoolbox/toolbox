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
        GrildItemInfo grildItemInfo = new GrildItemInfo();
        grildItemInfo.setDecribe("号码归属地");
        grildItemInfo.setKey(Common.PHONE_ADDRESS_KEY);
        grildItemInfo.setImgSource(R.mipmap.phone_address);
        list.add(grildItemInfo);

        grildItemInfo = new GrildItemInfo();
        grildItemInfo.setDecribe("律师");
        grildItemInfo.setKey(Common.LAWYER_KEY);
        grildItemInfo.setImgSource(R.mipmap.lawyer);
        list.add(grildItemInfo);

        grildItemInfo = new GrildItemInfo();
        grildItemInfo.setDecribe("ip地址");
        grildItemInfo.setKey(Common.IP_KEY);
        grildItemInfo.setImgSource(R.mipmap.ip_address);
        list.add(grildItemInfo);

        grildItemInfo = new GrildItemInfo();
        grildItemInfo.setDecribe("身份证查询");
        grildItemInfo.setKey(Common.IDENTITY_KEY);
        grildItemInfo.setImgSource(R.mipmap.identity);
        list.add(grildItemInfo);

        grildItemInfo = new GrildItemInfo();
        grildItemInfo.setDecribe("邮编地址");
        grildItemInfo.setKey(Common.POSTCODE_KEY);
        grildItemInfo.setImgSource(R.mipmap.postcode);
        list.add(grildItemInfo);

        grildItemInfo = new GrildItemInfo();
        grildItemInfo.setDecribe("开心一笑");
        grildItemInfo.setKey(Common.LAUGH_KEY);
        grildItemInfo.setImgSource(R.mipmap.laugh);
        list.add(grildItemInfo);
        gridView.setAdapter(new GrildViewAdapter(getActivity(),list));
    }

}
