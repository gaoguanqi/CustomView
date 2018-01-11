package com.maple.smaple.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/10.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private Context mContext;
    private List<String> mData;

    private OnItemClickLitener mOnItemClickLitener;

    public HomeAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setData(mData.get(position));
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.llContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0:mData.size();
    }



    public void setData(List<String> data) {
        this.mData  = data;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_ll_content)
        LinearLayout llContent;
        @BindView(R.id.item_textView)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(String data) {
            textView.setText(data);
        }
    }
}
