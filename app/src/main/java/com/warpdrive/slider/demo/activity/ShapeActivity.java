package com.warpdrive.slider.demo.activity;


import com.warpdrive.slider.demo.R;
import com.warpdrive.slider.demo.base.BaseActivity;

public class ShapeActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shape;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .navigationBarColor(R.color.shape1)
                .init();
    }
}
