package com.example.zenghui.bmobdemo.fragments;


import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by zenghui on 16/4/6.
 */
public class BasicFragment extends Fragment {

    public void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

}
