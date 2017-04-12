package com.example.mvptest.model;

import android.util.SparseArray;

import com.example.mvptest.bean.UserBean;

/**
 * Created by panzq on 2017/4/12.
 */
public class UserModel implements IUserModel {
    private String mFirstName;
    private String mLastName;
    private int mID;
    private SparseArray<UserBean> mUserArray = new SparseArray<UserBean>();

    @Override
    public void setID(int id) {
        mID = id;
    }

    @Override
    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        mLastName = lastName;
        UserBean userBean = new UserBean(mFirstName,mLastName);
        mUserArray.append(mID,userBean);
    }

    @Override
    public UserBean load(int id) {
        mID = id;
        UserBean userBean = mUserArray.get(mID,new UserBean("not found","not found"));
        return userBean;
    }
}
