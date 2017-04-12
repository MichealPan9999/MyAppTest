package com.example.mvptest.presenter;

import com.example.mvptest.bean.UserBean;
import com.example.mvptest.model.IUserModel;
import com.example.mvptest.model.UserModel;
import com.example.mvptest.view.IUserView;

/**
 * Created by panzq on 2017/4/12.
 */
public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView mUserView) {
        this.mUserView = mUserView;
        mUserModel = new UserModel();
    }
    public void saveUser(int id,String firstName,String lastName)
    {
        mUserModel.setID(id);
        mUserModel.setFirstName(firstName);
        mUserModel.setLastName(lastName);
    }
    public void loadUser(int id)
    {
        UserBean user = mUserModel.load(id);
        mUserView.setFirstName(user.getmFirstName());
        mUserView.setLastName(user.getmLastName());
    }
}
