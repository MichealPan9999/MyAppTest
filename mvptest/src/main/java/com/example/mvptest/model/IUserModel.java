package com.example.mvptest.model;

import com.example.mvptest.bean.UserBean;

/**
 * Created by panzq on 2017/4/12.
 */
public interface IUserModel {
    void setID(int id);
    void setFirstName(String firstName);
    void setLastName(String lastName);
    UserBean load(int id);
}
