package com.maple.smaple.customview.jianshu;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.maple.smaple.customview.R;
import com.maple.smaple.customview.jianshu.bean.BannerBean;
import com.maple.smaple.customview.jianshu.bean.ListBean;
import com.maple.smaple.customview.jianshu.bean.MenuBean;
import com.maple.smaple.customview.jianshu.interfaces.OnItemClickLitener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/11.
 */

public class JianShuActivity extends AppCompatActivity{

    @BindView(R.id.jianshu_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.jianshu_recyclerView)
    RecyclerView mRecyclerView;

    private List<BannerBean> mBannerData;
    private List<MenuBean> mMenuData;
    private List<ListBean> mListData;

    private JianShuAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianshu);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mAdapter = new JianShuAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 当RecyclerView的滑动状态改变时触发
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            /**
             * 当RecyclerView滑动时触发
             * 类似点击事件的MotionEvent.ACTION_MOVE
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                Log.e("TAG", "lastVisibleItemPosition:"+lastVisibleItemPosition);
                Log.e("TAG", "count:"+ mAdapter.getItemCount());
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    Log.e("TAG", "loading executed");
                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                    Log.e("TAG", "isRefreshing:"+isRefreshing);
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading ) {
                        isLoading = true;
                        mAdapter.setFooterState(isLoading);
                        mAdapter.notifyDataSetChanged();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadData();
                            }
                        }, 3000);
                    }
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright
                ,android.R.color.holo_green_light
                ,android.R.color.holo_orange_light
                ,android.R.color.holo_red_light);
    }

    private void loadData() {
        isLoading = false;
        for (int i = 0; i < 10; i++) {
            ListBean mListBean = new ListBean("加载更多"+i,"描述描述描述描述描述描述","https://www.baidu.com/img/bd_logo1.png");
            mListData.add(mListBean);
            mAdapter.setFooterState(isLoading);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initData() {
        mBannerData = new ArrayList<>();
        BannerBean mBannerBean1 = new BannerBean("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2526095363,542110218&fm=173&s=9E804A87CA30E4CE529D4F8003006095&w=218&h=146&img.JPEG","百度1");
        BannerBean mBannerBean2 = new BannerBean("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3509703757,1230033114&fm=173&s=7488AABE4A1024C81D1EB1F20300501A&w=218&h=146&img.JPEG","百度2");
        BannerBean mBannerBean3 = new BannerBean("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2238839426,1679103971&fm=173&s=A851A14ED440815F58ECC49A03005093&w=218&h=146&img.JPEG","百度3");
        mBannerData.add(mBannerBean1);
        mBannerData.add(mBannerBean2);
        mBannerData.add(mBannerBean3);

        mMenuData = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            MenuBean mMenuBean = new MenuBean("https://www.baidu.com/img/bd_logo1.png","百度"+i);
            mMenuData.add(mMenuBean);
        }

        mListData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ListBean mListBean = new ListBean("标题"+i,"描述描述描述描述描述描述","https://www.baidu.com/img/bd_logo1.png");
            mListData.add(mListBean);
        }


        mAdapter.setBannerData(mBannerData);
        mAdapter.setMenuData(mMenuData);
        mAdapter.setListData(mListData);
        mAdapter.setFooterState(isLoading);
    }


    private void initEvent() {
        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {

            @Override
            public void onBannerItemClick(String title) {
                Toast.makeText(JianShuActivity.this,title,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuItemClick(String name) {
                Toast.makeText(JianShuActivity.this,name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListItemClick(String title) {
                Toast.makeText(JianShuActivity.this,title,Toast.LENGTH_SHORT).show();
            }
        });
        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mBannerData.clear();
                for (int i = 0; i < 5; i++) {
                    BannerBean mBannerBean = new BannerBean("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2526095363,542110218&fm=173&s=9E804A87CA30E4CE529D4F8003006095&w=218&h=146&img.JPEG","刷新"+i);
                    mBannerData.add(mBannerBean);
                }


                mMenuData.clear();
                for (int i = 0; i < 20; i++) {
                    MenuBean mMenuBean = new MenuBean("https://www.baidu.com/img/bd_logo1.png","刷新"+i);
                    mMenuData.add(mMenuBean);
                }

                mListData.clear();
                for (int i = 0; i < 10; i++) {
                    ListBean mListBean = new ListBean("刷新"+i,"描述描述描述描述描述描述","https://www.baidu.com/img/bd_logo1.png");
                    mListData.add(mListBean);
                }

                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
