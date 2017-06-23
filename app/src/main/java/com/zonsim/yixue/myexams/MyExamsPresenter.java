package com.zonsim.yixue.myexams;

import android.support.annotation.NonNull;

import com.zonsim.yixue.base.BasePresenter;
import com.zonsim.yixue.bean.MyExamsResp;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ^-^
 * Created by tang-jw on 2017/6/23.
 */

public class MyExamsPresenter implements BasePresenter {
    
    private final MyExamsView mExamsView;
    private final MyExamsRepository mExamsRepository;
    private final CompositeDisposable mDisposable;
    
    public MyExamsPresenter(@NonNull MyExamsView view, @NonNull MyExamsRepository repository) {
        mExamsView = view;
        mExamsRepository = repository;
        mExamsView.setPresenter(this);
        mDisposable = new CompositeDisposable();
    }
    
    @Override
    public void subscribe() {
        loadMyExams(true);
    }
    
    @Override
    public void unsubscribe() {
        mDisposable.clear();
    }
    
    public void loadMyExams() {
        loadMyExams(false);
    }
    
    
    private void loadMyExams(boolean showLoadingUI) {
        if (showLoadingUI) {
            mExamsView.showEmpty();
        }
        
        Disposable disposable = mExamsRepository.getMyExams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyExamsResp>() {
                    @Override
                    public void accept(@NonNull MyExamsResp myExamsResp) throws Exception {
                        if (myExamsResp.getRet() != 0) {
                            List<MyExamsResp.ExamBean> myExams = myExamsResp.getInfo();
                            if (myExams != null) {
                                if (myExams.size() > 0) {
                                    mExamsView.showMyExams(myExams);
                                } else {
                                    mExamsView.showEmpty();
                                }
                            } else {
                                mExamsView.showError();
                            }
        
                        } else {
                            mExamsView.showError();
                        } 
                    }
                });
        
        mDisposable.add(disposable);
    }
    
}
