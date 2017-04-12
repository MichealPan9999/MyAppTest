package com.example.mvptest.view;

/**
 * Created by panzq on 2017/4/12.
 */
public interface IUserView {
    int getID();
    String getFirstName();
    String getLastName();
    void setFirstName(String firstName);
    void setLastName(String lastName);
}
