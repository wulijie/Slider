package com.warpdrive.slider.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.warpdrive.slider.Slider;

/**
 * Created by wulijie on 2017/12/29.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slider.add(this).setSlideEnable(isSlide());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Slider.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Slider.remove(this);
    }

    /**
     * 是否支持侧滑
     *
     * @return
     */
    protected boolean isSlide() {
        return true;
    }

}
