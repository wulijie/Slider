package com.warpdrive.slider;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;

/**
 * Created by wulijie on 2017/12/28.
 * 管理每个需要侧滑删除的页面
 */
public class SlidePage {
    private boolean mEnable = true;//判断是否需要侧滑删除,如果不需要就不要入住SlideLayout
    private boolean mRelativeEnable = false;
    private static final int DEFAULT_EDGE_SIZE = 100;
    //    private static final float DEFAULT_EDGE_PERCENT = 0.2f;
    private static final float DEFAULT_SENSITIVITY = 0.5f;//对横向滑动手势的敏感程度

    Activity mActivity;
    SlideLayout mSlideLayout;
    RelatedHelper slider;

    public SlidePage(Activity activity) {
        this.mActivity = activity;
    }

    //页面的回调用于配置滑动效果
    protected SlidePage init() {
        mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mActivity.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        mSlideLayout = new SlideLayout(mActivity);
        mSlideLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slider = new RelatedHelper(this);
        //默认值设置 尽量按照模仿微信来
        setSlideEdge(DEFAULT_EDGE_SIZE);
//        setSlideEdgePercent(DEFAULT_EDGE_PERCENT);//左边屏幕的百分之20为可滑动区域
        setSlideSensitivity(DEFAULT_SENSITIVITY);
        setSlideRelatedEnable(true);//默认支持底层page联动
        setSlideRelatedOffset(300);
        return this;
    }

    protected SlidePage onPostCreate() {
        handleLayout();
        return this;
    }

    public SlidePage setSlideRelatedEnable(boolean enable) {
        mRelativeEnable = enable;
        slider.setEnable(enable);
        return this;
    }

    public SlidePage setSlideRelatedOffset(int offset) {
        slider.setOffset(offset);
        return this;
    }

    //是否可滑动关闭
    public SlidePage setSlideEnable(boolean enable) {
        mEnable = enable;
        mSlideLayout.setEnableGesture(enable);
        handleLayout();
        return this;
    }

    private void handleLayout() {
        if (mEnable || mRelativeEnable) {
            mSlideLayout.attachToActivity(mActivity);
        } else {
            mSlideLayout.removeFromActivity(mActivity);
        }
    }

    //可滑动的范围。百分比。200表示为左边200px的屏幕
    public SlidePage setSlideEdge(int swipeEdge) {
        mSlideLayout.setEdgeSize(swipeEdge);
        return this;
    }

    //可滑动的范围。百分比。0.2表示为左边20%的屏幕
    public SlidePage setSlideEdgePercent(float swipeEdgePercent) {
        mSlideLayout.setEdgeSizePercent(swipeEdgePercent);
        return this;
    }

    //对横向滑动手势的敏感程度。0为迟钝 1为敏感
    public SlidePage setSlideSensitivity(float sensitivity) {
        mSlideLayout.setSensitivity(mActivity, sensitivity);
        return this;
    }

    //底层阴影颜色
    public SlidePage setScrimColor(int color) {
        mSlideLayout.setScrimColor(color);
        return this;
    }

    //触发关闭Activity百分比
    public SlidePage setClosePercent(float percent) {
        mSlideLayout.setScrollThreshold(percent);
        return this;
    }

    public SlidePage setDisallowInterceptTouchEvent(boolean disallowIntercept) {
        mSlideLayout.setDisallowInterceptTouchEvent(disallowIntercept);
        return this;
    }

    public SlidePage addListener(SlideListener listener) {
        mSlideLayout.addSlideListener(listener);
        return this;
    }

    public SlidePage removeListener(SlideListener listener) {
        mSlideLayout.removeSlideListener(listener);
        return this;
    }

    public SlideLayout getSlideLayout() {
        return mSlideLayout;
    }

    public SlidePage scrollToFinishActivity() {
        mSlideLayout.scrollToFinishActivity();
        return this;
    }
}
