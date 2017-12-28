package com.warpdrive.slider;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wulijie on 2017/12/28.
 * TODO:单例-stack 不要静态持有activity的引用了
 */
public class SlideHelper {
    private static final Stack<SlidePage> mPageStack = new Stack<>();

    private static SlidePage findHelperByActivity(Activity activity) {
        for (SlidePage slidePage : mPageStack) {
            if (slidePage.mActivity == activity) return slidePage;
        }
        return null;
    }

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

    protected static SlidePage getPrePage(SlidePage activity) {
        int index = mPageStack.indexOf(activity);
        if (index > 0) return mPageStack.get(index - 1);
        else return null;
    }
}
