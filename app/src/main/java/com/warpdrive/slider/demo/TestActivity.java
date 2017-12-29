package com.warpdrive.slider.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.warpdrive.slider.Slider;
import com.warpdrive.slider.demo.base.BaseActivity;

public class TestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.button2:
                Slider.finishAll(MainActivity.class);
                break;
        }

    }
}
