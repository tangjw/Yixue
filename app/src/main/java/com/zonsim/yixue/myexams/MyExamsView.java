package com.zonsim.yixue.myexams;

import android.support.annotation.NonNull;

import com.zonsim.yixue.bean.MyExamsResp;

import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 2017/6/23.
 */

public interface MyExamsView {
    
    void setPresenter(@NonNull MyExamsPresenter presenter);
    
    void showMyExams(@NonNull List<MyExamsResp.ExamBean> myExams);
    
    void showEmpty();
    
    void showLoadingUI(boolean isShow);
    
    void showError();
} 