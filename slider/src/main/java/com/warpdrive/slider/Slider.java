package com.warpdrive.slider;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wulijie on 2017/12/28.
 */
public class Slider {

    /**
     * 页面栈
     */
    private static final Stack<SlidePage> mPageStack = new Stack<>();

    /**
     * @param activity
     * @return
     */
    public static SlidePage bind(Activity activity) {
        return mPageStack.push(new SlidePage(activity)).init();
    }


    public static SlidePage unBind(SlidePage page) {
        if (page == null) return page;
        mPageStack.remove(page);
        page.mActivity = null;
        return page;
    }

    /**
     * 清空堆栈内的所有页面  cls页面除外
     *
     * @param cls
     */
    public static void finishAll(Class cls) {
        if (mPageStack == null || mPageStack.size() == 0) return;
        SlidePage clsPage = null;
        for (int i = mPageStack.size() - 1; i >= 0; i--) {
            SlidePage page = mPageStack.get(i);
            if (page == null || page.mActivity == null) break;
            if (cls != null && cls.equals(page.mActivity.getClass())) {
                clsPage = page;
            } else {
                page.mActivity.finish();
                page.mActivity = null;
            }
        }
        mPageStack.clear();
        if (clsPage != null) mPageStack.push(clsPage);
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
     * 通过activity查询对应的SlidePage
     *
     * @param activity
     * @return
     */
    public static SlidePage findSliderByActivity(Activity activity) {
        if (mPageStack == null || mPageStack.size() == 0) return null;
        for (int i = mPageStack.size() - 1; i >= 0; i--) {
            SlidePage page = mPageStack.get(i);
            if (page.mActivity == activity) return page;
        }
        return null;
    }
}
