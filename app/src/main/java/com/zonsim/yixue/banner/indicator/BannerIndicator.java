package com.zonsim.yixue.banner.indicator;

import android.support.v7.widget.RecyclerView;

public interface BannerIndicator {
    
    void bindViewPager(RecyclerView recyclerView);
    

    void bindViewPager(RecyclerView recyclerView, int initialPosition);
    

    void setCurrentItem(int currentItem);
    
 
}
