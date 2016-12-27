package com.example.retrofit.mvp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.activity.BaseActivity;
import com.example.retrofit.entity.api.SubjectPostApi;
import com.example.retrofit.mvp.presenter.P;
import com.example.retrofit.mvp.presenter.Plistener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpActivity extends BaseActivity implements Vlistener {
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    private Plistener plistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        plistener = new P(this);
    }


    @OnClick(value = R.id.tv_test)
    void onTvTestClick(View view) {
        plistener.doTest("1");
    }


    @OnClick(value = R.id.tv_msg)
    void onTvMsgClick(View view) {
        SubjectPostApi postEntity = new SubjectPostApi();
        postEntity.setAll(true);
        plistener.startPost(MvpActivity.this, postEntity);
    }


    @Override
    public void showProg() {
        showP();
    }

    @Override
    public void dismissProg() {
        Log.e("tag","--->dismiss");
        dismissP();
    }

    @Override
    public void onNext(String s, String m) {
        tvMsg.setText("返回数据:" + s);
    }

    @Override
    public void onError(ApiException e) {
        tvMsg.setText("错误信息:" + e.getMessage() + "------" + e.getCode());
    }

    @Override
    public void onTestNext(String msg) {
        tvTest.setText("测试返回数据了：" + msg);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissProg();
    }

}
