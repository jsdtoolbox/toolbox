package com.example.zenghui.bmobdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.adapter.DreamAdapter;
import com.example.zenghui.bmobdemo.model.CommonListResponse;
import com.example.zenghui.bmobdemo.model.CommonResponse;
import com.example.zenghui.bmobdemo.model.CookieCallBack;
import com.example.zenghui.bmobdemo.utils.Common;
import com.example.zenghui.bmobdemo.utils.DialogUtil;
import com.example.zenghui.bmobdemo.utils.ITask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cashbus on 6/6/16.
 */
public class DebitFragment extends BasicFragment{

    View rootView;
    EditText dreamEdt;
    ListView dreams;
    Button btnSearch;
    DreamAdapter dreamAdapter;
    List<Object> dreamList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_debit,null);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dreamEdt = (EditText) rootView.findViewById(R.id.dream);
        dreams = (ListView) rootView.findViewById(R.id.dreams);
        btnSearch = (Button) rootView.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(dreamEdt.getText().toString().trim())){
                    showToast("请输入关键字");
                    return;
                }
                DialogUtil.showLoading(getActivity(),"获取中...");

                ITask iTask = Common.getTask("http://v.juhe.cn");
                Call<CommonListResponse> call = iTask.getDreams(Common.DREAM_KEY,dreamEdt.getText().toString().trim(),1);
                call.enqueue(new CookieCallBack<CommonListResponse>(){
                    @Override
                    public void onResponse(Call<CommonListResponse> call, Response<CommonListResponse> response) {

                        CommonListResponse commonResponse = response.body();
                        if (commonResponse != null){
                            dreamList =  commonResponse.getResult();

                            if (dreamList != null && dreamList.size() > 0){
//                                if (dreamAdapter == null){
//                                    dreamAdapter = new DreamAdapter(getActivity(),dreamList);
//                                    dreams.setAdapter(dreamAdapter);
//                                }else {
//                                    dreamAdapter.notifyDataSetChanged();
//                                }
                                dreamAdapter = new DreamAdapter(getActivity(),dreamList);
                                dreams.setAdapter(dreamAdapter);
                            }else {
                                showToast("查询无结果");
                            }
                        }else {
                            showToast("查询无结果");
                        }
                        DialogUtil.dimissLoading();
                    }

                    @Override
                    public void onFailure(Call<CommonListResponse> call, Throwable t) {
                        super.onFailure(call, t);
                        DialogUtil.dimissLoading();
                    }
                });
            }
        });
    }
}
