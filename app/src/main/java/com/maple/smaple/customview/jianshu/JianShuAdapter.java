package com.maple.smaple.customview.jianshu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.smaple.customview.*;
import com.maple.smaple.customview.jianshu.bean.BannerBean;
import com.maple.smaple.customview.jianshu.bean.ListBean;
import com.maple.smaple.customview.jianshu.bean.MenuBean;
import com.maple.smaple.customview.jianshu.holder.BannerHolder;
import com.maple.smaple.customview.jianshu.holder.FooterHolder;
import com.maple.smaple.customview.jianshu.holder.ListHolder;
import com.maple.smaple.customview.jianshu.holder.MenuHolder;
import com.maple.smaple.customview.jianshu.interfaces.OnItemClickLitener;

import java.util.List;

/**
 * Created by gaogu on 2018/1/10.
 */

public class JianShuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public  final int BANNER = 0;
    public  final int MENU = 1;
    public  final int LIST = 2;
    public  final int FOOTER = 3;


    public  final int BANNER_SIZE = 1;
    public  final int MENU_SIZE = 1;
    //加载更多
    public  final int FOOTER_SIZE = 1;



    private Context mContext;

    private List<BannerBean> mBannerData;
    private List<MenuBean> mMenuData;
    private List<ListBean> mListData;

    private LayoutInflater mInflater;

    public JianShuAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    private com.maple.smaple.customview.jianshu.interfaces.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerHolder(mOnItemClickLitener,mInflater.inflate(R.layout.item_jianshu_banner, parent, false));
        } else if (viewType == MENU) {
            return new MenuHolder(mContext,mOnItemClickLitener,mInflater.inflate(R.layout.item_jianshu_menu, parent, false));
        } else if(viewType == LIST){
            return new ListHolder(mContext,mOnItemClickLitener,mInflater.inflate(R.layout.item_jianshu_list, parent, false));
        }else if (viewType == FOOTER) {
            return new FooterHolder(mContext,mInflater.inflate(R.layout.item_jianshu_footer, parent, false));
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            ((BannerHolder) holder).setData(mBannerData);
        }else if(holder instanceof MenuHolder){
            ((MenuHolder) holder).setData(mMenuData);
        }else if(holder instanceof ListHolder) {
            ((ListHolder) holder).setData(mListData.get(position-(BANNER_SIZE+MENU_SIZE)));
        }else if(holder instanceof FooterHolder) {
            ((FooterHolder) holder).setState();
        }
    }

    @Override
    public int getItemCount() {
        return mBannerData == null && mMenuData == null && mListData == null ? 0 : mListData.size()+(BANNER_SIZE+MENU_SIZE);
    }

    @Override
    public int getItemViewType(int position) {
        if (mBannerData.size() != 0 && position == 0) {
            return BANNER;
        }else if (mMenuData.size() != 0 && position == BANNER_SIZE) {
            return MENU;
        }else if(mListData.size() != 0 && position >= (BANNER_SIZE+MENU_SIZE)) {
            return LIST;
        }else {
            return -1;
        }
    }

    public void setBannerData(List<BannerBean> data) {
        this.mBannerData = data;
        notifyDataSetChanged();
    }

    public void setMenuData(List<MenuBean> data) {
        this.mMenuData = data;
        notifyDataSetChanged();
    }

    public void setListData(List<ListBean> data) {
        this.mListData = data;
        notifyDataSetChanged();
    }
}
