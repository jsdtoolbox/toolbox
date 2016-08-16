package com.example.zenghui.bmobdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zenghui.bmobdemo.R;
import com.example.zenghui.bmobdemo.spinnerwheel.AbstractWheel;
import com.example.zenghui.bmobdemo.spinnerwheel.adapters.AbstractWheelTextAdapter;


public class SpinnerGroupAdapter extends AbstractWheelTextAdapter {
    // Countries names
    private String[] group;
    AbstractWheel list;
    // Countries flags

    /**
     * Constructor
     */
    public SpinnerGroupAdapter(Context context, String group[], AbstractWheel list) {
        super(context, R.layout.spinner_item, NO_RESOURCE);
        setItemTextResource(R.id.tvSpinner);
        this.group = group;
        this.list = list;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);

        ImageView img = (ImageView) view.findViewById(R.id.imgHead);
        if (index == 0) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);
        }

        TextView textView = (TextView) view.findViewById(R.id.tvSpinner);
        textView.setText(group[index]);
        return view;
    }

    @Override
    public int getItemsCount() {
        return group.length;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return group[index];
    }
}