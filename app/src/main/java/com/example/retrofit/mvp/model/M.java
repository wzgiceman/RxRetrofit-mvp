package com.example.retrofit.mvp.model;

import com.example.retrofit.mvp.presenter.PVlistener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by WZG on 2016/12/26.
 */

public class M implements Mlistener, HttpOnNextListener {


    private PVlistener pVlistener;

    public M(PVlistener pVlistener) {
        this.pVlistener = pVlistener;
    }

    @Override
    public void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        manager.doHttpDeal(baseApi);
    }

    @Override
    public void testDo(String s) {
        String msg = "M" + s;
        pVlistener.testPSuc(msg);
    }


    @Override
    public void onNext(String resulte, String mothead) {
        pVlistener.onNext(resulte, mothead);
    }

    @Override
    public void onError(ApiException e) {
        pVlistener.onError(e);
    }

}
