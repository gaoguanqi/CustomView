package com.maple.smaple.customview.timeline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maple.smaple.customview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeLineActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private TimeLineAdapter mAdapter;
    private List<TimeLineBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TimeLineAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        TimeLineBean bena1 = new TimeLineBean("申请提交成功","2018-02-16 09:09","申请借款100000元，期限6个月",false);
        TimeLineBean bena2 = new TimeLineBean("借款状态","2018-03-16 09:09","预计会在一个小时内完成审核，请您耐心等待",true);
        mData.add(bena1);
        mData.add(bena1);
        mData.add(bena1);
        mData.add(bena1);
        mData.add(bena1);
        mData.add(bena2);
        mAdapter.setData(mData);
    }

    private void initEvent() {
        mAdapter.setData(mData);
    }
}
