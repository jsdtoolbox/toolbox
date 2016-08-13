package com.example.zenghui.bmobdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by zenghui on 16/8/9.
 */
public class BasicActivity extends AppCompatActivity {

    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show() ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
}
