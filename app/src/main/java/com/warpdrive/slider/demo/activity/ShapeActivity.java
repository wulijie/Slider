package com.warpdrive.slider.demo.activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.warpdrive.slider.demo.R;
import com.warpdrive.slider.demo.base.BaseActivity;

import butterknife.BindView;

public class ShapeActivity extends BaseActivity {

    @BindView(R.id.vp)
    RollPagerView mPagerView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shape;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .navigationBarColor(R.color.shape1)
                .init();

        mPagerView.setAdapter(new TestNomalAdapter());
        //让RollPagerView可以处理事件，而RollPagerView处不可以滑动环比，其他没有View处理事件的区域可以正常滑动关闭。
        mSlidePage.setDisallowInterceptTouchEvent(true);
    }

    public static class TestNomalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.test,
                R.mipmap.img2,
                R.mipmap.test,
                R.mipmap.img2,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }


}
