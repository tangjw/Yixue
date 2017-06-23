package com.zonsim.yixue.myexams;

import android.support.annotation.NonNull;

import com.zonsim.yixue.bean.MyExamsResp;
import com.zonsim.yixue.net.HttpMethods;

import io.reactivex.Observable;

/**
 * ^-^
 * Created by tang-jw on 2017/6/21.
 */

public class MyExamsRepository {
    
    private final String mUid;
    
    public MyExamsRepository(@NonNull String uid) {
        mUid = uid;
    }
    
    public Observable<MyExamsResp> getMyExams() {
        return HttpMethods.getInstance().getMyExams(Long.parseLong(mUid));
    }
}
