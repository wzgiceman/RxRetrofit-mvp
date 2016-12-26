package com.example.retrofit.mvp.ui;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * Created by WZG on 2016/12/26.
 */

public interface Vlistener {

    void showProg();

    void dismissProg();

    void onNext(String s,String m);

    void  onError(ApiException e);

    void onTestNext(String msg);

}
