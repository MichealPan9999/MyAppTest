package com.example.staringup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.staringup.fragment.LauncherBaseFragment;

import java.util.List;

/**
 * Created by panzq on 2017/3/30.
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter{
    private List<LauncherBaseFragment> list;

    public BaseFragmentAdapter(FragmentManager fm,List<LauncherBaseFragment> list) {
        super(fm);
        this.list = list;
    }
    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
