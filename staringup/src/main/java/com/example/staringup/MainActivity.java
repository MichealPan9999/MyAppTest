package com.example.staringup;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.staringup.adapter.BaseFragmentAdapter;
import com.example.staringup.fragment.LauncherBaseFragment;
import com.example.staringup.fragment.PrivateMessageLauncherFragment;
import com.example.staringup.fragment.RewardLauncherFragment;
import com.example.staringup.fragment.StereocopicLauncherFragment;
import com.example.staringup.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private GuideViewPager vPager;
    private List<LauncherBaseFragment>list = new ArrayList<LauncherBaseFragment>();
    private BaseFragmentAdapter adapter;
    private ImageView[] tips;
    private int currentSelect=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化点点点控件
        ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);
        tips = new ImageView[3];
        for (int i =0;i<tips.length;i++)
        {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));
            if (i==0)
            {
                imageView.setBackgroundResource(R.mipmap.page_indicator_focused);
            }else{
                imageView.setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            tips[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 20;//设置点点点view的左边距
            layoutParams.rightMargin = 20;//设置点点点view的右边距
            group.addView(imageView,layoutParams);
        }
        //获取自定义viewpager 然后设置背景图片
        vPager = (GuideViewPager)findViewById(R.id.viewpager_launcher);
        vPager.setBackGround(BitmapFactory.decodeResource(getResources(),R.mipmap.bg_kaka_launcher));
        /**
         * 初始化三个fragment  并且添加到list中
         */
        RewardLauncherFragment rewardLauncherFragment = new RewardLauncherFragment();
        PrivateMessageLauncherFragment privateMessageLauncherFragment = new PrivateMessageLauncherFragment();
        StereocopicLauncherFragment stereocopicLauncherFragment = new StereocopicLauncherFragment();
        list.add(rewardLauncherFragment);
        list.add(privateMessageLauncherFragment);
        list.add(stereocopicLauncherFragment);
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(),list);
        vPager.setAdapter(adapter);
        vPager.setOffscreenPageLimit(2);
        vPager.setCurrentItem(currentSelect);
        vPager.setOnPageChangeListener(changeListener);
    }
    /**
     * 监听viewpager的移动
     */
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d("panzq","onPageSelected "+position);
            setImageBackground(position);
            LauncherBaseFragment fragment = list.get(position);
            list.get(currentSelect).stopAnimation();//停止前一个页面的动画
            fragment.startAnimation();//开启当前页面的动画
            currentSelect = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    /**
     * 改变点点点的切换效果
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }
    }
}
