package com.zonsim.yixue.banner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zonsim.yixue.util.L;

/**
 * ^-^
 * Created by tang-jw on 2017/6/27.
 */

public class BannerSnapHelper extends LinearSnapHelper {
    
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        
        int targetSnapPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        L.i("targetSnapPosition => " + targetSnapPosition);
        View snapView = findSnapView(layoutManager);
        
        if (snapView != null) {
            int currentPosition = layoutManager.getPosition(snapView);
            L.i("currentPosition => " + currentPosition);
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
            
            L.i("firstVisibleItemPosition => " + firstVisibleItemPosition + "\nlastVisibleItemPosition => " + lastVisibleItemPosition);

//            currentPosition = targetSnapPosition < currentPosition ? lastVisibleItemPosition
//                    : (targetSnapPosition > currentPosition ? firstVisibleItemPosition : currentPosition);
//            L.i("currentPosition2 => "+currentPosition);
//            targetSnapPosition = targetSnapPosition < currentPosition ? currentPosition - 1
//                    : (targetSnapPosition > currentPosition ? currentPosition + 1 : currentPosition);
//            L.i("targetSnapPosition2 => "+targetSnapPosition);
    
            if (targetSnapPosition != 0) {
                if (targetSnapPosition == -1) {
            
                    if (firstVisibleItemPosition == lastVisibleItemPosition) {
                        //上下滑动情况
                        targetSnapPosition = currentPosition;
                    } else if (currentPosition == firstVisibleItemPosition) {
                        targetSnapPosition = currentPosition < manager.getItemCount() ? currentPosition + 1 : currentPosition;
                    } else if (currentPosition == lastVisibleItemPosition) {
                        targetSnapPosition = currentPosition > 0 ? currentPosition - 1 : currentPosition;
                    }
            
            
                } else {
            
                    targetSnapPosition = targetSnapPosition < currentPosition ?
                            (currentPosition > 0 ? currentPosition - 1 : currentPosition)
                            : (currentPosition < manager.getItemCount() ? currentPosition + 1 : currentPosition);
                }
            } else {
    
                L.i("targetSnapPosition => 0");
            }
    
            L.i("targetSnapPosition2 => " + (targetSnapPosition%manager.getItemCount()));
            
        }
        
        
        
        L.i("--------------------------"+(targetSnapPosition)+"--------------------------------");
        
        return targetSnapPosition;
    }
}
