package com.zonsim.yixue.banner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zonsim.yixue.banner.indicator.BannerIndicator;
import com.zonsim.yixue.util.L;

/**
 * ^-^
 * Created by tang-jw on 2017/6/27.
 */

public class BannerSnapHelper extends LinearSnapHelper {
    
    private final BannerIndicator mIndicator;
    private int indicatorPosition = 0;
    
    public BannerSnapHelper( BannerIndicator indicator) {
    
        mIndicator = indicator;
    
    }
    
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
            
            L.i("currentPosition => " + currentPosition);
        }
        
        L.i("targetSnapPosition => " + targetSnapPosition);
        
        indicatorPosition = targetSnapPosition;
        
        L.i("indicatorPosition => " + indicatorPosition);
    
        if (mIndicator != null) {
            mIndicator.setCurrentItem(indicatorPosition);
        }
        
        return targetSnapPosition;
    }
}
