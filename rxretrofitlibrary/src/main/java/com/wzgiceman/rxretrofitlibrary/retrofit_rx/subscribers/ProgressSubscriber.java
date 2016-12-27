package com.wzgiceman.rxretrofitlibrary.retrofit_rx.subscribers;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.CodeException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.HttpTimeException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.cookie.CookieResulte;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.AppUtil;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.CookieDbUtil;

import java.lang.ref.SoftReference;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 *
 * 统一处理缓存-数据持久化
 * 异常回调
 * Created by WZG on 2016/7/16.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements Subscription {
    //    回调接口
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    /*请求数据*/
    private BaseApi api;


    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(BaseApi api, SoftReference<HttpOnNextListener> listenerSoftReference) {
        this.api = api;
        this.mSubscriberOnNextListener = listenerSoftReference;
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        /*缓存并且有网*/
        if (api.isCache() && AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
             /*获取缓存数据*/
            CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            if (cookieResulte != null) {
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (mSubscriberOnNextListener.get() != null) {
                        mSubscriberOnNextListener.get().onNext(cookieResulte.getResulte(), api.getMothed());
                    }
                    onCompleted();
                    unsubscribe();
                }
            }
        }
    }


    @Override
    public void onCompleted() {

    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        /*需要緩存并且本地有缓存才返回*/
        if (api.isCache()) {
            getCache();
        } else {
            errorDo(e);
        }
    }

    /**
     * 获取cache数据
     */
    private void getCache() {
        Observable.just(api.getUrl()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                errorDo(e);
            }

            @Override
            public void onNext(String s) {
                           /*获取缓存数据*/
                CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(s);
                if (cookieResulte == null) {
                    throw new HttpTimeException(HttpTimeException.NO_CHACHE_ERROR);
                }
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNoNetWorkTime()) {
                    if (mSubscriberOnNextListener.get() != null) {
                        mSubscriberOnNextListener.get().onNext(cookieResulte.getResulte(), api.getMothed());
                    }
                } else {
                    CookieDbUtil.getInstance().deleteCookie(cookieResulte);
                    throw new HttpTimeException(HttpTimeException.CHACHE_TIMEOUT_ERROR);
                }
            }
        });
    }


    /**
     * 错误统一处理
     *
     * @param e
     */
    private void errorDo(Throwable e) {
        HttpOnNextListener httpOnNextListener = mSubscriberOnNextListener.get();
        if (httpOnNextListener == null) return;
        if (e instanceof ApiException) {
            httpOnNextListener.onError((ApiException) e);
        } else if (e instanceof HttpTimeException) {
            HttpTimeException exception = (HttpTimeException) e;
            httpOnNextListener.onError(new ApiException(exception, CodeException.RUNTIME_ERROR, exception.getMessage()));
        } else {
            httpOnNextListener.onError(new ApiException(e, CodeException.UNKNOWN_ERROR, e.getMessage()));
        }
    }


    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
         /*缓存处理*/
        if (api.isCache()) {
            CookieResulte resulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            long time = System.currentTimeMillis();
            /*保存和更新本地数据*/
            if (resulte == null) {
                resulte = new CookieResulte(api.getUrl(), t.toString(), time);
                CookieDbUtil.getInstance().saveCookie(resulte);
            } else {
                resulte.setResulte(t.toString());
                resulte.setTime(time);
                CookieDbUtil.getInstance().updateCookie(resulte);
            }
        }
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext((String) t, api.getMothed());
        }
    }





}