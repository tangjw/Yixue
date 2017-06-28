package com.zonsim.yixue.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zonsim.yixue.R;
import com.zonsim.yixue.banner.adapter.BannerAdapter;
import com.zonsim.yixue.banner.indicator.CirclePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2017/6/27.
 */

public class RecyclerBanner extends FrameLayout {
    
    private BannerAdapter mAdapter;
    private List<Banner> mBanners;
    private RecyclerView mRecyclerView;
    private CirclePagerIndicator mIndicator;
    
    private int mCurrentPosition;
    private Handler mHandler;
    
    public RecyclerBanner(Context context) {
        this(context, null);
    }
    
    public RecyclerBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public RecyclerBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    public void replaceBanners(List<Banner> banners) {
        mBanners = banners;
        if (banners.size() == 0) return;
        mAdapter.replaceData(mBanners);
        mIndicator.setCount(mBanners.size());
        mCurrentPosition = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mBanners.size());
        mRecyclerView.scrollToPosition(mCurrentPosition);
        mIndicator.setCurrentItem(mCurrentPosition);
        if (mHandler == null) {
        
            mHandler = new Handler();
        } else {
            mHandler.removeCallbacksAndMessages(null);
        
        }
    
    
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.smoothScrollToPosition(++mCurrentPosition);
                mIndicator.setCurrentItem(mCurrentPosition);
                mHandler.postDelayed(this, 5000L);
            }
        }, 5000L);
        
        
    }
    
    private void init() {
        
        mBanners = new ArrayList<>();
        mAdapter = new BannerAdapter(mBanners);
        
        mRecyclerView = new RecyclerView(getContext());
        
        addView(mRecyclerView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    
    
        mIndicator = (CirclePagerIndicator) LayoutInflater.from(getContext()).inflate(R.layout.indicator, this, false);
        
        
        mIndicator.bindViewPager(mRecyclerView);
        
        
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
        params.gravity = Gravity.BOTTOM;
        mIndicator.setBackgroundColor(Color.GRAY);
        addView(mIndicator, params);
        
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        
        mRecyclerView.setAdapter(mAdapter);
        
        BannerSnapHelper snapHelper = new BannerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        
        
    }
    
    
    private class BannerSnapHelper extends LinearSnapHelper {
        
        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            
            int targetSnapPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            
            View snapView = findSnapView(layoutManager);
            
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            if (snapView != null) {
                int currentPosition = layoutManager.getPosition(snapView);
                
                
                int first = manager.findFirstVisibleItemPosition();
                int last = manager.findLastVisibleItemPosition();
                
                if (targetSnapPosition != 0) {
                    if (targetSnapPosition == -1) {
                        
                        if (first == last) {
                            //上下滑动情况
                            targetSnapPosition = currentPosition;
                        } else if (currentPosition == first) {
                            targetSnapPosition = currentPosition < manager.getItemCount() ? currentPosition + 1 : currentPosition;
                        } else if (currentPosition == last) {
                            targetSnapPosition = currentPosition > 0 ? currentPosition - 1 : currentPosition;
                        }
                        
                    } else {
                        currentPosition = targetSnapPosition < currentPosition ? last : (targetSnapPosition > currentPosition ? first : currentPosition);
                        targetSnapPosition = targetSnapPosition < currentPosition ?
                                (currentPosition > 0 ? currentPosition - 1 : currentPosition)
                                : (currentPosition < manager.getItemCount() ? currentPosition + 1 : currentPosition);
                    }
                }
                
            }
            
            mCurrentPosition = targetSnapPosition;
            
            if (mIndicator != null) {
                mIndicator.setCurrentItem(mCurrentPosition);
            }
            
            return targetSnapPosition;
        }
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        
    }
}
