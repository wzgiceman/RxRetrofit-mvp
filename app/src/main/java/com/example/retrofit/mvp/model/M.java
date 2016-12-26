package com.example.retrofit.mvp.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by WZG on 2016/12/26.
 */

public class M implements Mlistener,HttpOnNextListener {

    /*view回调 -其实这里完全可以直接将此传入到HttpManager中，但是违背了mvp设计理念，自行考虑*/
    private HttpOnNextListener pOnNextListener;


    @Override
    public void onStart(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi, HttpOnNextListener onNextListener) {
        HttpManager manager=new HttpManager(this,rxAppCompatActivity);
        manager.doHttpDeal(baseApi);
        this.pOnNextListener=onNextListener;
    }

    @Override
    public void onNext(String resulte, String mothead) {
        pOnNextListener.onNext(resulte,mothead);
    }

    @Override
    public void onError(ApiException e) {
        pOnNextListener.onError(e);
    }

}
