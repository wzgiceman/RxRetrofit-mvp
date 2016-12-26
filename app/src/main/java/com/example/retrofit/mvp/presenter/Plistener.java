package com.example.retrofit.mvp.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

/**
 * p层提供给v层的接口
 * Created by WZG on 2016/12/26.
 */

public interface Plistener {
    /**
     * http请求
     * @param rxAppCompatActivity
     * @param baseApi
     */
    void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi);

    /**
     * 其他方法-演示
     * @param msg
     */
    void doTest(String msg);

}
