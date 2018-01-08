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
//        setSlideRelatedOffset(300);
        return this;
    }

    /**
     * activity完全初始化完毕以后执行此操作
     *
     * @return
     */
    public SlidePage onPostCreate() {
        handleLayout();
        return this;
    }

    /**
     * 设置侧滑关联-当前页面下的页面会跟随当前页面有关联的滑动
     *
     * @param enable 是否侧滑关联
     * @return
     */
    public SlidePage setSlideRelatedEnable(boolean enable) {
        mRelativeEnable = enable;
        slider.setEnable(enable);
        return this;
    }

    /**
     * 上下两个页面侧滑移动时的距离差值
     *
     * @param offset 侧滑移动时的距离差值(配合setSlideRelatedEnable使用）
     * @return
     */
    public SlidePage setSlideRelatedOffset(int offset) {
        slider.setOffset(offset);
        return this;
    }

    /**
     * 是否可滑动
     *
     * @param enable 是否可滑动
     * @return
     */
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

    /**
     * 可滑动的范围。实际像素。200表示为左边200px的屏幕
     *
     * @param swipeEdge 可滑动的范围。实际像素
     * @return
     */
    public SlidePage setSlideEdge(int swipeEdge) {
        mSlideLayout.setEdgeSize(swipeEdge);
        return this;
    }

    /**
     * 可滑动的范围。百分比。0.2表示为左边20%的屏幕
     *
     * @param swipeEdgePercent 可滑动的范围。百分比
     * @return
     */
    public SlidePage setSlideEdgePercent(float swipeEdgePercent) {
        mSlideLayout.setEdgeSizePercent(swipeEdgePercent);
        return this;
    }

    /**
     * 对横向滑动手势的敏感程度。0为迟钝 1为敏感
     *
     * @param sensitivity 0为迟钝 1为敏感
     * @return
     */
    public SlidePage setSlideSensitivity(float sensitivity) {
        mSlideLayout.setSensitivity(mActivity, sensitivity);
        return this;
    }

    /**
     * 底层阴影颜色
     *
     * @param color 底层阴影颜色
     * @return
     */
    public SlidePage setScrimColor(int color) {
        mSlideLayout.setScrimColor(color);
        return this;
    }

    /**
     * 触发关闭Activity百分比
     *
     * @param percent 触发关闭Activity百分比
     * @return
     */
    public SlidePage setClosePercent(float percent) {
        mSlideLayout.setScrollThreshold(percent);
        return this;
    }

    /**
     * 是否不允许拦截事件
     *
     * @param disallowIntercept 布尔值
     * @return
     */
    public SlidePage setDisallowInterceptTouchEvent(boolean disallowIntercept) {
        mSlideLayout.setDisallowInterceptTouchEvent(disallowIntercept);
        return this;
    }

    /**
     * 添加页面滑动监听
     *
     * @param listener 页面滑动监听
     * @return
     */
    public SlidePage addListener(SlideListener listener) {
        mSlideLayout.addSlideListener(listener);
        return this;
    }

    /**
     * 移除页面滑动监听
     *
     * @param listener 页面滑动监听
     * @return
     */
    public SlidePage removeListener(SlideListener listener) {
        mSlideLayout.removeSlideListener(listener);
        return this;
    }

    /**
     * 获取页面滑动布局
     *
     * @return
     */
    public SlideLayout getSlideLayout() {
        return mSlideLayout;
    }


    public SlidePage scrollToFinishActivity() {
        mSlideLayout.scrollToFinishActivity();
        return this;
    }
}
