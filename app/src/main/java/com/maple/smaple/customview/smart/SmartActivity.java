package com.maple.smaple.customview.smart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maple.smaple.customview.R;
import com.maple.smaple.customview.decoration.DividerListItemDecoration;
import com.maple.smaple.customview.jianshu.JianShuAdapter;
import com.maple.smaple.customview.jianshu.bean.BannerBean;
import com.maple.smaple.customview.jianshu.bean.ListBean;
import com.maple.smaple.customview.jianshu.bean.MenuBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaogu on 2018/1/12.
 */

public class SmartActivity extends AppCompatActivity{

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private LinearLayoutManager mLinearLayoutManager;
    private JianShuAdapter mAdapter;

    private List<BannerBean> mBannerData;
    private List<MenuBean> mMenuData;
    private List<ListBean> mListData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();


        /**

         本周工作：

         1，好人品修改友盟分享；
         2，优化代码；

         下周计划：
         1，；

         */
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerview.addItemDecoration(new DividerListItemDecoration(this,LinearLayoutManager.VERTICAL));
        mAdapter = new JianShuAdapter(this);
        recyclerview.setLayoutManager(mLinearLayoutManager);
        recyclerview.setAdapter(mAdapter);
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
    }

    private void initEvent() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败

                refreshLayout.finishLoadmoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
            }
        });

        refreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
