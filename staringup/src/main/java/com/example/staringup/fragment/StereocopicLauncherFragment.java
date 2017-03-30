package com.example.staringup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.staringup.R;

/**
 * Created by panzq on 2017/3/30.
 */
public class StereocopicLauncherFragment extends LauncherBaseFragment implements View.OnClickListener {
    private static final float ZOOM_MAX = 1.3f;
    private static final  float ZOOM_MIN = 1.0f;

    private ImageView imgView_immediate_experience;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView=inflater.inflate(R.layout.fragment_stereoscopic_launcher, null);
        imgView_immediate_experience=(ImageView) rooView.findViewById(R.id.imgView_immediate_experience);
        imgView_immediate_experience.setOnClickListener(this);
        return rooView;
    }

    @Override
    public void startAnimation() {
        playHeartbeatAnimation();
    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void onClick(View view) {

    }
    public void playHeartbeatAnimation()
    {
        /**
         * 放大动画
         */
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new ScaleAnimation(ZOOM_MIN,ZOOM_MAX,ZOOM_MIN,ZOOM_MAX, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f));
        animationSet.addAnimation(new AlphaAnimation(1.0f,1.8f));
        animationSet.setDuration(500);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                /**
                 * 缩小动画
                 */
                AnimationSet animationSet2 = new AnimationSet(true);
                animationSet2.addAnimation(new ScaleAnimation(ZOOM_MAX, ZOOM_MIN, ZOOM_MAX,ZOOM_MIN, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f));
                animationSet2.addAnimation(new AlphaAnimation(0.8f,1.0f));
                animationSet2.setDuration(600);
                animationSet2.setInterpolator(new DecelerateInterpolator());
                animationSet2.setFillAfter(false);
                // 实现心跳的View
                imgView_immediate_experience.startAnimation(animationSet2);
            }
        });
        // 实现心跳的View
        imgView_immediate_experience.startAnimation(animationSet);
    }
}
