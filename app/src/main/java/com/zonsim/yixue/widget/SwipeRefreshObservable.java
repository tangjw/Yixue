package com.zonsim.yixue.widget;

import android.support.v4.widget.SwipeRefreshLayout;

import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * ^-^
 * Created by tang-jw on 2017/6/26.
 */

final public class SwipeRefreshObservable extends Observable<Object> {
    
    private final SwipeRefreshLayout view;
    
    
    public SwipeRefreshObservable(SwipeRefreshLayout view) {
        this.view = view;
    }
    
    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        if (!Preconditions.checkMainThread(observer)) {
            return;
        }
        
        Listener listener = new Listener(view, observer);
        observer.onSubscribe(listener);
        view.setOnRefreshListener(listener);
    }
    
    private static final class Listener extends MainThreadDisposable implements SwipeRefreshLayout.OnRefreshListener {
        
        private final SwipeRefreshLayout view;
        private final Observer<? super Object> observer;
        
        Listener(SwipeRefreshLayout view, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
        }
        
        @Override
        public void onRefresh() {
    
            if (!isDisposed()) {
                observer.onNext(Notification.INSTANCE);
            }
        }
        
        @Override
        protected void onDispose() {
            view.setOnRefreshListener(null);
        }
    }
    
}
