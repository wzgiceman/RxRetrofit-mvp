package com.example.retrofit.mvp.presenter;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * mvp 中p层提供给m层使用的接口
 * Created by WZG on 2016/12/26.
 */

public interface PVlistener {

    /**
     * 测试回调
     * @param msg
     */
    void testPSuc(String msg);

    /**
     * 成功后回调方法
     *
     * @param resulte
     * @param mothead
     */
    void onNext(String resulte, String mothead);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param e
     */
    void onError(ApiException e);

}
