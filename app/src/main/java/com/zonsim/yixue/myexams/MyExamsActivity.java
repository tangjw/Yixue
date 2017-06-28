package com.zonsim.yixue.myexams;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zonsim.yixue.R;
import com.zonsim.yixue.base.BaseActivity;
import com.zonsim.yixue.bean.MyExamsResp;
import com.zonsim.yixue.myexams.adapter.MyExamsAdapter;
import com.zonsim.yixue.util.L;
import com.zonsim.yixue.util.ToastUtils;
import com.zonsim.yixue.widget.ScrollChildSwipeRefreshLayout;
import com.zonsim.yixue.widget.SwipeRefreshObservable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

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
    private ScrollChildSwipeRefreshLayout mSwipeRefreshLayout;
    
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
        
        mSwipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.srl_content);
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorExamStatus1),
                ContextCompat.getColor(this, R.color.colorExamStatus2),
                ContextCompat.getColor(this, R.color.colorExamStatus3),
                ContextCompat.getColor(this, R.color.colorExamStatus4)
        );
        mSwipeRefreshLayout.setScrollUpChild(mRecyclerView);
    }
    
    @Override
    protected void loadData() {
        MyExamsRepository repository = new MyExamsRepository("112");
        MyExamsPresenter presenter = new MyExamsPresenter(this, repository);
        mPresenter.subscribe();
    }
    
    @Override
    protected void setListener() {
        
        Observable<Object> observable = new SwipeRefreshObservable(mSwipeRefreshLayout);
        observable.subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                mPresenter.loadMyExams();
            }
        });
        
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
        System.out.println(isShow + "---------------");
        mVLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    
    @Override
    public void setLoadingIndicator(boolean active) {
        mSwipeRefreshLayout.setRefreshing(active);
    }
    
    @Override
    public void showError() {
        ToastUtils.show("出错了");
    }
}
