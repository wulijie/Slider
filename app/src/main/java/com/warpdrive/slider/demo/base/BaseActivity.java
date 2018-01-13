package com.warpdrive.slider.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.warpdrive.slider.SlidePage;
import com.warpdrive.slider.Slider;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wulijie on 2017/12/29.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected SlidePage mSlidePage;
    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        //绑定控件
        unbinder = ButterKnife.bind(this);

        //初始化侧滑删除
        mSlidePage = Slider.bind(this)
                .setSlideEnable(isSlide())
                .setSlideEdge(100)//可滑动的范围。实际像素。200表示为左边200px的屏幕(默认是100)
                //.setSlideEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕(与setSlideEdge 功能一致使用一个就可以)
                .setSlideRelatedEnable(true)//是否支持当前页面下的页面跟随联动（默认是true）
                .setSlideRelatedOffset(300)//与上面的参数配合使用，连动差距（默认300）
                //.setScrimColor()//设置滑动时蒙层的颜色（默认是透明 仿微信）
                .setSlideSensitivity(0.5f);//对横向滑动手势的敏感程度。0为迟钝 1为敏感(默认0.5f)

        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //这个别忘了加上！
        mSlidePage.onPostCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除侧滑删除
        Slider.unBind(mSlidePage);
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    /**
     * 是否支持侧滑
     *
     * @return
     */
    protected boolean isSlide() {
        return true;
    }

    protected abstract int setLayoutId();

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void setListener() {
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

}
