package com.example.retrofit.mvp.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by WZG on 2016/12/26.
 */

public interface Mlistener {/*
    * 开始任务
    * */
    void onStart(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi, HttpOnNextListener pVlistener);
}
