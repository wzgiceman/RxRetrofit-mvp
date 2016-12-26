package com.example.retrofit.mvp.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

/**
 * http请求接口
 * Created by WZG on 2016/12/26.
 */

public interface Plistener {
    void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi);
}
