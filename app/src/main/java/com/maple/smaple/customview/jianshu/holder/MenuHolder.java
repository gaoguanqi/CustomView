package com.maple.smaple.customview.jianshu.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maple.smaple.customview.R;
import com.maple.smaple.customview.jianshu.MenuAdapter;
import com.maple.smaple.customview.jianshu.bean.MenuBean;
import com.maple.smaple.customview.jianshu.interfaces.OnItemClickLitener;
import com.maple.smaple.customview.jianshu.interfaces.OnMenuItemClickLitener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/11.
 */

public class MenuHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_menu_recyclerView)
    RecyclerView mRecyclerView;

    private MenuAdapter mAdapter;

    public MenuHolder(Context context, final OnItemClickLitener onItemClickLitener, View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mAdapter = new MenuAdapter(context);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new OnMenuItemClickLitener() {

            @Override
            public void onItemClick(String name) {
                onItemClickLitener.onMenuItemClick(name);
            }
        });
    }

    public void setData(List<MenuBean> data) {
        mAdapter.setData(data);
    }
}
