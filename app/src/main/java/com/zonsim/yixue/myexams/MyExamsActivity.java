package com.zonsim.yixue.myexams;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zonsim.yixue.R;
import com.zonsim.yixue.base.BaseActivity;
import com.zonsim.yixue.bean.MyExamsResp;
import com.zonsim.yixue.myexams.adapter.MyExamsAdapter;
import com.zonsim.yixue.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2017/6/23.
 */

public class MyExamsActivity extends BaseActivity implements MyExamsView {
    
    private RecyclerView mRecyclerView;
    
    private MyExamsAdapter mExamsAdapter;
    
    private MyExamsAdapter.OnMyExamClickListener mExamClickListener = new MyExamsAdapter.OnMyExamClickListener() {
        @Override
        public void onItemClick(MyExamsResp.ExamBean myExam) {
            ToastUtils.show("点击了" + myExam.getProfessionName());
        }
    };
    private MyExamsPresenter mPresenter;
    private View mVLoading;
    
    @Override
    protected void initArgs() {
        mExamsAdapter = new MyExamsAdapter(new ArrayList<MyExamsResp.ExamBean>(0), mExamClickListener);
    }
    
    @Override
    protected void initView() {
        setContentView(R.layout.activity_myexams);
        
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_myexams);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(mExamsAdapter);
    
    
        mVLoading = findViewById(R.id.ll_loading);
        
    }
    
    @Override
    protected void loadData() {
        MyExamsRepository repository = new MyExamsRepository("112");
        MyExamsPresenter presenter = new MyExamsPresenter(this, repository);
        mPresenter.subscribe();
    }
    
    @Override
    public void setPresenter(@NonNull MyExamsPresenter presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void showMyExams(@NonNull List<MyExamsResp.ExamBean> myExams) {
        mExamsAdapter.replaceData(myExams);
        mVLoading.setVisibility(View.GONE);
    }
    
    @Override
    public void showEmpty() {
        
    }
    
    @Override
    public void showLoadingUI(boolean isShow) {
        mVLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    
    @Override
    public void showError() {
        ToastUtils.show("出错了");
    }
}
