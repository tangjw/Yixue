package com.zonsim.yixue.net;

import com.zonsim.yixue.bean.MyExamsResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ^-^
 * Created by tang-jw on 2017/6/23.
 */

public interface Api {
    /**
     * 获取我的考试列表
     */
    @GET("lianyi/EduApplyInfo/getApplyInfo.do")
    Observable<MyExamsResp> getMyExams(@Query("examineeId") long uid);
}
