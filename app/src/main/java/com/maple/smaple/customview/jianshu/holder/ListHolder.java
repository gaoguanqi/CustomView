package com.maple.smaple.customview.jianshu.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maple.smaple.customview.R;
import com.maple.smaple.customview.jianshu.bean.ListBean;
import com.maple.smaple.customview.jianshu.interfaces.OnItemClickLitener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/11.
 */

public class ListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_ll)
    LinearLayout ll;
    @BindView(R.id.item_list_tv_title)
    TextView tvTitle;
    @BindView(R.id.item_list_tv_desc)
    TextView tvDesc;
    @BindView(R.id.item_list_iv)
    ImageView iv;

    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;
    public ListHolder(Context context, OnItemClickLitener onItemClickLitener, View itemView) {
        super(itemView);
        this.mContext = context;
        this.mOnItemClickLitener = onItemClickLitener;
        ButterKnife.bind(this,itemView);
    }

    public void setData(final ListBean listBean) {
        tvTitle.setText(listBean.getTitle());
        tvDesc.setText(listBean.getDesc());
        Glide.with(mContext).load(listBean.getImage()).into(iv);

        if(null != mOnItemClickLitener){
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onListItemClick(listBean.getTitle());
                }
            });
        }
    }


}
