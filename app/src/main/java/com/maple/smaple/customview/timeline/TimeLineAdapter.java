package com.maple.smaple.customview.timeline;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maple.smaple.customview.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/22.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {


    private Context mContext;
    private List<TimeLineBean> mData;

    public TimeLineAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_time_line, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<TimeLineBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_iv_icon)
        ImageView itemIvIcon;
        @BindView(R.id.item_view_line)
        View itemViewLine;
        @BindView(R.id.item_tv_title)
        TextView itemTvTitle;
        @BindView(R.id.item_tv_date)
        TextView itemTvDate;
        @BindView(R.id.item_tv_content)
        TextView itemTvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(TimeLineBean data) {
            itemTvTitle.setText(data.getTitle());
            itemTvDate.setText(data.getTime());
            itemTvContent.setText(data.getContent());

            if(data.isLast()){
                itemViewLine.setVisibility(View.GONE);
               Glide.with(mContext).load(R.drawable.eye_open).into(itemIvIcon);
                itemTvTitle.setTextColor(Color.parseColor("#ff383b"));
            }else{
                itemViewLine.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(R.drawable.eye_close).into(itemIvIcon);
                itemTvTitle.setTextColor(Color.parseColor("#BFBFBF"));
            }
        }
    }
}
