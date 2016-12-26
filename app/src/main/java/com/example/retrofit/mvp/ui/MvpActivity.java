package com.example.retrofit.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.activity.BaseActivity;
import com.example.retrofit.entity.api.SubjectPostApi;
import com.example.retrofit.mvp.presenter.Plistener;
import com.example.retrofit.mvp.presenter.P;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

public class MvpActivity extends BaseActivity implements Vlistener {
    TextView tvMsg,tvTest;
    Plistener plistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        tvTest=(TextView)findViewById(R.id.tv_test);


        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plistener.doTest("1");
            }
        });


        plistener = new P(this);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        tvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubjectPostApi postEntity = new SubjectPostApi();
                postEntity.setAll(true);
                plistener.startPost(MvpActivity.this, postEntity);
            }
        });
    }

    @Override
    public void showProg() {
        showP();
    }

    @Override
    public void dismissProg() {
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
    public void onTest(String msg) {
        tvTest.setText("测试返回数据了："+msg);
    }

}
