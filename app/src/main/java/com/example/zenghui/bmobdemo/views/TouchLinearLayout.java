package com.example.zenghui.bmobdemo.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.listener.DialogListener;


/**
 * Created by cashbus on 6/24/16.
 */
public class TouchLinearLayout extends LinearLayout {
    public TouchLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    long touchTime = 0;

    public boolean touchAble = true, showPressed = false, ontouchArea = true, isStop = false;

    public boolean isTouchAble() {
        return touchAble;
    }

    public void setTouchAble(boolean touchAble) {
        this.touchAble = touchAble;
    }

    public TouchLinearLayout(Context context) {
        super(context);
    }

    public TouchLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    DialogListener handleDialogListener;

    public void setHandleDialogListener(DialogListener handleDialogListener) {
        this.handleDialogListener = handleDialogListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (!isTouchAble()) {
            return super.dispatchTouchEvent(ev);
        }

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            touchTime = System.currentTimeMillis();
            showPressed = false;
            ontouchArea = true;
            setBackgroundResource(R.color.profile_item_press);

            return true;
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                setBackgroundResource(R.color.profile_item_press);
        } else {
            ontouchArea = false;
            if (System.currentTimeMillis() - touchTime <= 400 && ev.getAction() == MotionEvent.ACTION_UP) {

                if (handleDialogListener != null){
                    handleDialogListener.handle("");
                }

            }
                setBackgroundResource(R.color.white);
            touchTime = 0;
        }
        return super.dispatchTouchEvent(ev);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                setBackgroundResource(R.color.profile_item_press);
                showPressed = true;
                touchTime = 0;
            } else {
                setBackgroundResource(R.color.white);
            }

        }
    };
}
