package com.warpdrive.slider;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by wulijie on 2017/12/28.
 */
public class RelatedSlider implements SlideListener {
    public SlidePage curPage;
    private static final int DEFAULT_OFFSET = 40;
    private int offset = 500;

    public RelatedSlider(SlidePage curPage) {
        this.curPage = curPage;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setEnable(boolean enable) {
        if (enable) curPage.addListener(this);
        else curPage.removeListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onScroll(float percent, int px) {
        if (Build.VERSION.SDK_INT > 11) {
            SlidePage page = SlideHelper.getPrePage(curPage);
            if (page != null) {
                page.getSwipeBackLayout().setX(Math.min(-offset * Math.max(1 - percent, 0) + DEFAULT_OFFSET, 0));
                if (percent == 0) {
                    page.getSwipeBackLayout().setScrollX(0);
                }
            }
        }
    }

    @Override
    public void onEdgeTouch() {

    }

    @Override
    public void onScrollToClose() {
        SlidePage page = SlideHelper.getPrePage(curPage);
        if (Build.VERSION.SDK_INT > 11) {
            if (page != null) page.getSwipeBackLayout().setX(0);
        }
    }
}
