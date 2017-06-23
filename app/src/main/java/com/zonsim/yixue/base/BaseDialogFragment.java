package com.zonsim.yixue.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * ^-^
 * Created by tang-jw on 2017/5/15.
 */

public abstract class BaseDialogFragment extends DialogFragment {
    
    protected View mRootView;
    protected AppCompatActivity mActivity;
    
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        /*if (context instanceof BaseActivityOld) {
            mActivity = (BaseActivityOld) context;
        } else {
            throw new RuntimeException(context.toString() + "must extends BaseActivityOld!");
        }*/
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogStyle();
        
        Bundle args = getArguments();
        if (args != null) {
            initArguments(args);
        }
    }
    
    protected void setDialogStyle() {
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog_NoActionBar);
    }
    
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        mRootView = inflater.inflate(setLayoutId(), container, false);
        
        initView();
        
        setListener();
        
        return mRootView;
    }
    
    @Override
    public void onStart() {
        super.onStart();
    
        setDialogBackground();
    }
    
    protected void setDialogBackground() {
        Window window = getDialog().getWindow();
        if (window != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            window.setLayout((int) (dm.widthPixels * 0.86), ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
    
    /**
     * 初始化View
     */
    protected void initView() {
        
    }
    
    /**
     * 设置监听事件
     */
    protected void setListener() {
        
    }
    
    protected void initArguments(@NonNull Bundle args) {
        
    }
    
    protected abstract int setLayoutId();
    
    
}
