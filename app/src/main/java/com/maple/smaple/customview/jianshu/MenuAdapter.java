package com.maple.smaple.customview.jianshu;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maple.smaple.customview.R;
import com.maple.smaple.customview.jianshu.bean.MenuBean;
import com.maple.smaple.customview.jianshu.holder.MenuHolder;
import com.maple.smaple.customview.jianshu.interfaces.OnMenuItemClickLitener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/11.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHodler> {
    private Context mContext;
    private List<MenuBean> mData;


    public MenuAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<MenuBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    private OnMenuItemClickLitener mOnMenuItemClickLitener;

    public void setOnItemClickLitener(OnMenuItemClickLitener onMenuItemClickLitener) {
        this.mOnMenuItemClickLitener = onMenuItemClickLitener;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.item_cl)
        ConstraintLayout cl;
        @BindView(R.id.item_menu_iv)
        ImageView iv;
        @BindView(R.id.item_menu_tv)
        TextView tv;

        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final MenuBean menuBean) {
            tv.setText(menuBean.getName());
            Glide.with(mContext).load(menuBean.getImage()).into(iv);
            if (null != mOnMenuItemClickLitener) {
                cl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnMenuItemClickLitener.onItemClick(menuBean.getName());
                    }
                });
            }
        }
    }
}
