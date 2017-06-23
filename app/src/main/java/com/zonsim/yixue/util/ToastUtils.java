package com.zonsim.yixue.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import static com.zonsim.yixue.App.app;

/**
 * 自定义的Toast 避免Toast长时间不消失 Created by tang-jw on 2016/6/3.
 */
public class ToastUtils {
    
    private static Toast mToast;
    
    public static void show(String string) {
        showToast(string, false);
    }
    
    public static void showLong(String string) {
        showToast(string, true);
    }
    
    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
    
    private static void showToast(String text, boolean flag) {
        if (TextUtils.isEmpty(text)) return;
        
        int length = flag ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        if (mToast == null) {
            mToast = Toast.makeText(app.getApplicationContext(), text, length);
        }
        mToast.setDuration(length);
        mToast.setText(text);
        
        mToast.setGravity(Gravity.CENTER, mToast.getXOffset() / 2, 4 * mToast.getYOffset() / 5);
        
        mToast.show();
    }
}
