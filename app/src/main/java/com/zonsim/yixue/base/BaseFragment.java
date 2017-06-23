package com.zonsim.yixue.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ^-^
 * Created by tang-jw on 2017/4/12.
 */

public abstract class BaseFragment extends Fragment {
    
    private static final String TAG = "BaseFragment";
    //    protected BaseActivityOld mActivity;
    protected AppCompatActivity mActivity;
    protected int mContainerId;
    
    public void setContainerId(int containerId) {
        mContainerId = containerId;
    }
    
    /**
     * 根view
     */
    protected View mRootView;
    
    /**
     * 是否加载完成 当执行完onCreateView,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;
    
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
        Bundle args = getArguments();
        if (args != null) {
            initArguments(args);
        }
    }
    
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        mRootView = inflater.inflate(setLayoutId(), container, false);
        
        initView();
        
        mIsPrepare = true;
        
        setListener();
        
        loadData();
        
        
        return mRootView;
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }
    
    
    protected void loadData() {
    }
    
    
    protected abstract int setLayoutId();
    
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
        
        
    }
    
    
    public boolean prepareFetchData() {
        return prepareFetchData(true);
    }
    
    public boolean prepareFetchData(boolean forceUpdate) {
        
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            onVisible();
            isDataInitiated = true;
            return true;
        } else {
            if (isDataInitiated) {
                onInvisible();
            }
        }
        return false;
    }
    
    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected void initArguments(@NonNull Bundle arguments) {
        
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
    
    
    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onVisible() {
        
    }
    
    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onInvisible() {
        
        
    }
    
    
    protected void startFragment(BaseFragment fragment) {
        fragment.setContainerId(mContainerId);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
//                .setCustomAnimations(
//                        R.anim.drop_in_bottom,
//                        R.anim.drop_out_top,
//                        R.anim.pop_in_top,
//                        R.anim.pop_out_bottom)
                .hide(this)
                .add(mContainerId, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getTag())
                .commit();
        
    }
    
    protected void pop() {
        getFragmentManager().popBackStack();
//        mActivity.onBackPressed();
    }
    
}
