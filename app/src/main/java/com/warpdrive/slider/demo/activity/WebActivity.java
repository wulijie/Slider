package com.warpdrive.slider.demo.activity;

import android.webkit.WebView;
import android.widget.LinearLayout;

import com.warpdrive.slider.demo.R;
import com.warpdrive.slider.demo.base.BaseActivity;

import butterknife.BindView;


public class WebActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView mWebView;
    @BindView(R.id.line)
    LinearLayout layout;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .init();
    }

    @Override
    protected void initView() {
        mWebView.loadUrl("http://www.baidu.com");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mWebView != null) {
                mWebView.removeAllViews();
                mWebView.destroy();
                mWebView = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
