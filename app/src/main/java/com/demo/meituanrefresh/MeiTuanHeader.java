package com.demo.meituanrefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created by cheng on 2019/1/3.
 */

public class MeiTuanHeader extends LinearLayout implements RefreshHeader {
    private TextView textView;
    private AnimationDrawable animationDrawable;

    public MeiTuanHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        textView = new TextView(context);
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.a);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        addView(imageView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(DensityUtil.dp2px(80));
        addView(textView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        animationDrawable.stop();
        if (success){
            textView.setText("刷新完成");
        } else {
            textView.setText("刷新失败");
        }
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        animationDrawable.start();
        switch (newState) {
            case None:
            case PullDownToRefresh:
                textView.setText("下拉开始刷新");
                break;
            case Refreshing:
                textView.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                textView.setText("释放立即刷新");
                break;
        }
    }
}
