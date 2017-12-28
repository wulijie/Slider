package com.warpdrive.slider;

/**
 * Created by wulijie on 2017/12/28.
 */
public interface SlideListener {
    void onScroll(float percent, int px);

    void onEdgeTouch();

    void onScrollToClose();
}
