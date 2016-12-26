package com.example.retrofit.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by WZG on 2016/12/26.
 */

public class BaseActivity extends RxAppCompatActivity {
    //    加载框可自己定义
    protected ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(pd==null){
            pd = new ProgressDialog(this);
            pd.setCancelable(false);
        }
    }

    protected void showP(){
        if(pd!=null&&!pd.isShowing()){
            pd.show();
        }
    }


    protected void dismissP(){
        if(pd!=null&&pd.isShowing()){
            pd.dismiss();
        }
    }
}
