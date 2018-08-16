package com.example.diyahmmt.mcatalogueuiux.nowandupadapter;

import android.view.View;

/**
 * Created by ACER on 29-07-2018.
 */

public class ClickListener implements View.OnClickListener {
    private int pos;
    private OnItemClick onItemClick;
    public ClickListener(int pos, OnItemClick onItemClick) {
        this.pos = pos;
        this.onItemClick = onItemClick;
    }

    @Override
    public void onClick(View v) {
        onItemClick.onItemClicked(v, pos);
    }

    public interface OnItemClick {
        void onItemClicked(View view, int pos);
    }
}
