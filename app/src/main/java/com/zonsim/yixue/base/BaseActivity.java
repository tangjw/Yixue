package com.zonsim.yixue.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zonsim.yixue.util.ActivityStackUtils;

import io.reactivex.disposables.CompositeDisposable;


/**
 * 益学重构 BaseActivityOld
 * Created by tang-jw on 2017/5/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    
    private static final String TAG = "BaseActivity";
    public CompositeDisposable mDisposables;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeSuperCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        
        // 所有Activity添加到栈管理
        ActivityStackUtils.getInstance().addActivity(this);
        mDisposables = new CompositeDisposable();
        initArgs();
        initView();
        loadData();
        setListener();
        
    }
    
    /**
     * getIntent() 等操作
     */
    protected void initArgs() {
    }
    
    protected abstract void initView();
    
    protected void setListener() {
    }
    
    protected void loadData() {
    }
    
    /**
     * 在 super.onCreate(savedInstanceState); 之前调用
     */
    protected void beforeSuperCreate(@Nullable Bundle savedInstanceState) {
        
    }
    
    /**
     * activity.finish() 操作 使用 ActivityStackUtils 进行管理
     */
    @Override
    public void finish() {
        if (ActivityStackUtils.getInstance().removeActivity()) {
            super.finish();
        }
    }
    
    protected void setToolbar(@NonNull Toolbar toolbar) {
//        setSupportActionBar(toolbar);
//        
//        ActionBar actionBar = getSupportActionBar();
//        
//        if (actionBar != null) {
//            // 显示 title 默认为 true ,设为 false Toolbar上的 title才会显示
//            actionBar.setDisplayShowTitleEnabled(false);
//            // 显示返回箭头 ← 默认不显示
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

//        toolbar.setTitle("资讯详情");
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.ic_nav_back);
        toolbar.setContentInsetStartWithNavigation(0);
        
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }
    
    @Override
    protected void onDestroy() {
        mDisposables.clear();
        super.onDestroy();
    }
}
