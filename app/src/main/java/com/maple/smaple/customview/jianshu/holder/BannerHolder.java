package com.maple.smaple.customview.jianshu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.maple.smaple.customview.R;
import com.maple.smaple.customview.jianshu.bean.BannerBean;
import com.maple.smaple.customview.jianshu.interfaces.OnItemClickLitener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/11.
 */

public class BannerHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.banner)
    Banner mBanner;

    private List<BannerBean> mData;
    private OnItemClickLitener mOnItemClickLitener;
    public BannerHolder(OnItemClickLitener onItemClickLitener,View itemView) {
        super(itemView);
        this.mOnItemClickLitener = onItemClickLitener;
        ButterKnife.bind(this,itemView);
    }

    public void setData(List<BannerBean> data) {
        this.mData = data;

        List<String> images = null;
        List<String> titles = null;

        if(null != data && data.size() >0){
            images = new ArrayList<>(data.size());
            titles = new ArrayList<>(data.size());

            for (int i = 0; i < data.size(); i++) {
                images.add(data.get(i).getImage());
                titles.add(data.get(i).getTitle());
            }
        }
        mBanner.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();

        if(null != mOnItemClickLitener){
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    mOnItemClickLitener.onBannerItemClick(mData.get(position).getTitle());
                }
            });
        }
    }
}
