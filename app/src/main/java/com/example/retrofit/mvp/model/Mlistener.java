package com.example.retrofit.mvp.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

/**
 * Created by WZG on 2016/12/26.
 */

public interface Mlistener {

    /**
     * 开始任务
     * @param rxAppCompatActivity
     * @param baseApi
     */
    void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi);

    /**
     * 测试接口
     * @param s
     */
    void testDo(String s);

}
