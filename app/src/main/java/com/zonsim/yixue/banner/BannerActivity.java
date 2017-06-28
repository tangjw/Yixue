package com.zonsim.yixue.banner;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zonsim.yixue.R;
import com.zonsim.yixue.banner.adapter.BannerAdapter;
import com.zonsim.yixue.banner.indicator.CirclePagerIndicator;
import com.zonsim.yixue.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2017/6/26.
 */

public class BannerActivity extends BaseActivity {
    
    private RecyclerView mRecyclerView;
    private List<Banner> mBanners;
    private BannerAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean mDirection;
    private int mCurrentPosition;
    private CirclePagerIndicator mIndicator;
    
    @Override
    protected void initArgs() {
        mBanners = new ArrayList<>();
        mBanners.add(new Banner("", R.drawable.img_1));
        mBanners.add(new Banner("", R.drawable.img_2));
        mBanners.add(new Banner("", R.drawable.img_3));
        mBanners.add(new Banner("", R.drawable.img_4));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        mBanners.add(new Banner("", R.drawable.img_5));
        
        mAdapter = new BannerAdapter(mBanners);
    }
    
    @Override
    protected void initView() {
        setContentView(R.layout.activity_banner);
        
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_banner);
        mIndicator = (CirclePagerIndicator) findViewById(R.id.indicator);
        mIndicator.bindViewPager(mRecyclerView);
        mIndicator.setCount(11);
        
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setAdapter(mAdapter);
        
        final BannerSnapHelper snapHelper = new BannerSnapHelper(mIndicator);
        snapHelper.attachToRecyclerView(mRecyclerView);
        
        mCurrentPosition = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mBanners.size());
        
        mRecyclerView.scrollToPosition(mCurrentPosition);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.smoothScrollToPosition(++mCurrentPosition);
                mIndicator.setCurrentItem(mCurrentPosition);
                new Handler().postDelayed(this, 2000L);
            }
        }, 2000L);
        
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            
            private int currentX;
            
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        break;
                    
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                    
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        
                        break;
                }
                
            }
            
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                
                if (Math.abs(dx) > currentX) {
                    
                    if (dx > 0) {
                        
                        mDirection = false;
                    } else {
                        
                        mDirection = true;
                    }
                    
                }
                
                currentX = Math.abs(dx);
                
            }
            
        });
    }
    
}
