package com.warpdrive.slider.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.warpdrive.slider.demo.R;
import com.warpdrive.slider.demo.base.BaseActivity;

import butterknife.BindView;

public class PicActivity extends BaseActivity {
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .titleBar(toolbar, false)
                .transparentBar()
                .init();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alpha = (float) progress / 100;
                textView.setText("透明度:" + alpha + "f");
                ImmersionBar.with(PicActivity.this)
                        .addViewSupportTransformColor(toolbar, R.color.colorPrimary)
                        .navigationBarColorTransform(R.color.orange)
                        .barAlpha(alpha)
                        .init();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pic;
    }
}
