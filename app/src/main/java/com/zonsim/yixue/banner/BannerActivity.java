package com.zonsim.yixue.banner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zonsim.yixue.R;
import com.zonsim.yixue.banner.adapter.BannerAdapter;
import com.zonsim.yixue.base.BaseActivity;
import com.zonsim.yixue.util.L;

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
        
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setAdapter(mAdapter);
        
        new BannerSnapHelper().attachToRecyclerView(mRecyclerView);
        
        mRecyclerView.scrollToPosition(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mBanners.size()));
        
        L.i("RecyclerView", "indicator indexItem1 => 0");
        
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
                        
                        int first = mLinearLayoutManager.findFirstVisibleItemPosition();
                        L.i("RecyclerView", "FirstVisibleItem1 => " + first % mBanners.size());
                        int last = mLinearLayoutManager.findLastVisibleItemPosition();
                        L.i("RecyclerView", "LastVisibleItem1 => " + last % mBanners.size());
    
                        if (!mDirection) {
                            L.i("RecyclerView", "indicator indexItem1 => " + (first % mBanners.size() + 1));
                        } else {
                            L.i("RecyclerView", "indicator indexItem1 => " + (last % mBanners.size() - 1));
                        } 
                        
                        
                        
                        break;
                }
                
                L.i("RecyclerView", "newState => " + newState);
                
            }
            
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
    
                if (Math.abs(dx) > currentX) {
    
                    if (dx > 0) {
                        L.i("RecyclerView", "方向 <= ");
        
                        mDirection = false;
                    } else {
                        // TODO: 2017/6/27 向左滑动 
                        L.i("RecyclerView", "方向 => ");
                        mDirection = true;
                    } 
                    
                }
                
                
    
                /*if (dx < currentX) {
                    // TODO: 2017/6/27 向右滑动 
                    L.i("RecyclerView", "方向 => ");
                    
                    mDirection = true;
                    
                } else if (dx > currentX) {
                    // TODO: 2017/6/27 向左滑动 
                    L.i("RecyclerView", "方向 <= ");
                    mDirection = false;
                }*/
                currentX = Math.abs(dx);
                
                L.i("RecyclerView", "dx => " + dx + " , dy => " + dy);
            }
            
            
            
            
        });
        
    }
    
    
}
