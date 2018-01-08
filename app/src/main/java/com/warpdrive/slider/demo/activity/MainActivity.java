package com.warpdrive.slider.demo.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warpdrive.slider.demo.R;
import com.warpdrive.slider.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    @BindView(R.id.tv_version)
    TextView tv_version;

    @Override
    protected boolean isSlide() {
        return false;//主页不支持侧滑删除
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_pic, R.id.btn_shape, R.id.btn_banner,
            R.id.btn_coordinator, R.id.btn_web})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pic:
                startActivity(new Intent(this, PicActivity.class));
                break;
            case R.id.btn_shape:
                startActivity(new Intent(this, ShapeActivity.class));
                break;
            case R.id.btn_coordinator:
                startActivity(new Intent(this, CoordinatorActivity.class));
                break;
            case R.id.btn_web:
                startActivity(new Intent(this, WebActivity.class));
                break;
        }

    }
}
