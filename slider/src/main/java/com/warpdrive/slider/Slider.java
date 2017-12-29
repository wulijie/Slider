package com.warpdrive.slider;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wulijie on 2017/12/28.
 */
public class Slider {

    private static final Stack<SlidePage> mPageStack = new Stack<>();

    private static final String EXCEPTION_MESSAGE = "you should call Slider.with(activity) first";

    public static SlidePage with(Activity activity) {
        SlidePage page = findSliderByActivity(activity);
        if (page == null) {
            page = mPageStack.push(new SlidePage(activity));
            page.init();
        }
        return page;
    }

    public static void onPostCreate(Activity activity) {
        SlidePage page;
        if ((page = findSliderByActivity(activity)) == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        page.onPostCreate();
    }

    public static void onDestroy(Activity activity) {
        SlidePage page;
        if ((page = findSliderByActivity(activity)) == null) return;
        mPageStack.remove(page);
        page.mActivity = null;
    }

    /**
     * 清空堆栈内的所有页面  cls页面除外
     *
     * @param cls
     */
    public static void finishAll(Class cls) {
        //TODO: 完成这个功能
//        if (mPageStack == null || mPageStack.size() == 0) return;
//        for (SlidePage page : mPageStack) {
//            if (page.mActivity == null) return;
//            if (cls != null && cls.equals(page.mActivity.getClass())) return;
//            page.mActivity.finish();
//            page.mActivity = null;
//        }
    }

    /**
     * 清空堆栈内的所有页面
     */
    public static void finishAll() {
        finishAll(null);
    }

    /**
     * 获取栈顶的Activity
     *
     * @return
     */
    public static Activity getCurActivity() {
        SlidePage page;
        if ((page = getCurPage()) == null) return null;
        return page.mActivity;
    }

    /**
     * 获取当前页面下的Activity
     *
     * @return
     */
    public static Activity getPreActivity() {
        SlidePage page;
        if ((page = getCurPage()) == null) return null;
        SlidePage prePage;
        if ((prePage = getPrePage(page)) == null) return null;
        return prePage.mActivity;
    }

    /**
     * 获取栈顶Page
     *
     * @return
     */
    private static SlidePage getCurPage() {
        if (mPageStack == null || mPageStack.size() == 0) return null;
        SlidePage page = mPageStack.lastElement();
        return page;
    }

    /**
     * 获取当前Page下的Page
     *
     * @param activity
     * @return
     */
    protected static SlidePage getPrePage(SlidePage activity) {
        int index = mPageStack.indexOf(activity);
        if (index > 0) return mPageStack.get(index - 1);
        else return null;
    }

    /**
     * @param activity
     * @return
     */
    private static SlidePage findSliderByActivity(Activity activity) {
        for (SlidePage slidePage : mPageStack) {
            if (slidePage.mActivity == activity) return slidePage;
        }
        return null;
    }
}
