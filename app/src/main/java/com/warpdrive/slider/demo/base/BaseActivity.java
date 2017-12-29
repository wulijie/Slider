package com.warpdrive.slider.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.warpdrive.slider.Slider;
import com.warpdrive.slider.demo.R;

/**
 * Created by wulijie on 2017/12/29.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slider.with(this)
                .setSlideEnable(isSlide())
                .setSlideEdge(100)
                .setSlideSensitivity(0.5f)
                //给出默认值 直接仿照微信 抽出Activity内存管理stack 对外暴露方法
                .setScrimColor(getResources().getColor(R.color.translate))
                .setSlideRelatedEnable(true)
                .setSlideRelatedOffset(300);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Slider.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Slider.onDestroy(this);
        Log.e("Slider", "页面销毁了");
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
