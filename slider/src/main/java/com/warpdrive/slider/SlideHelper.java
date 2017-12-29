package com.warpdrive.slider;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wulijie on 2017/12/28.
 * TODO:单例-stack 不要静态持有activity的引用了
 */
public class SlideHelper {

    private static final Stack<SlidePage> mPageStack = new Stack<>();


    public static SlidePage with(Activity activity) {
        SlidePage page;
        if ((page = findHelperByActivity(activity)) == null) {
            throw new IllegalArgumentException("You Should call SlideHelper.onCreate(activity) first");
        }
        return page;
    }

    public static void onCreate(Activity activity) {
        SlidePage page;
        if ((page = findHelperByActivity(activity)) == null) {
            page = mPageStack.push(new SlidePage(activity));
        }
        page.onCreate();
    }

    public static void onPostCreate(Activity activity) {
        SlidePage page;
        if ((page = findHelperByActivity(activity)) == null) {
            throw new IllegalArgumentException("You Should call SlideHelper.onCreate(activity) first");
        }
        page.onPostCreate();
    }

    public static void onDestroy(Activity activity) {
        SlidePage page;
        if ((page = findHelperByActivity(activity)) == null) {
            throw new IllegalArgumentException("You Should call SlideHelper.onCreate(activity) first");
        }
        mPageStack.remove(page);
        page.mActivity = null;
    }

    public static void finish(Activity activity) {
        SlidePage page;
        if ((page = findHelperByActivity(activity)) == null) {
            throw new IllegalArgumentException("You Should call SlideHelper.onCreate(activity) first");
        }
        page.scrollToFinishActivity();
    }


    /**
     * 清空堆栈内的所有页面  cls页面除外
     *
     * @param cls
     */
    public static void finishAll(Class cls) {
        if (mPageStack == null || mPageStack.size() == 0) return;
        while (true) {
            SlidePage page = getCurPage();
            if (page == null) break;
            if (page.mActivity == null) break;
            if (cls != null && page.mActivity.getClass().equals(cls)) break;
            page.mActivity.finish();
            mPageStack.remove(page);
            page.mActivity = null;
        }
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
    private static SlidePage findHelperByActivity(Activity activity) {
        for (SlidePage slidePage : mPageStack) {
            if (slidePage.mActivity == activity) return slidePage;
        }
        return null;
    }
}
