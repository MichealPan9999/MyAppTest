package com.example.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvptest.presenter.UserPresenter;
import com.example.mvptest.view.IUserView;

public class MainActivity extends Activity implements View.OnClickListener,IUserView{

    private EditText mFirstNameEdit,mLastNameEdit,mIdEdit;
    private Button mSaveButton,mLoadButton;
    private UserPresenter mUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWidgets();
        mUserPresenter = new UserPresenter(this);
        mSaveButton.setOnClickListener(this);
        mLoadButton.setOnClickListener(this);
    }
    private void findWidgets()
    {
        mFirstNameEdit = (EditText)findViewById(R.id.first_name_edt);
        mLastNameEdit = (EditText)findViewById(R.id.last_name_edt);
        mIdEdit = (EditText) findViewById(R.id.id_edt);
        mSaveButton = (Button) findViewById(R.id.saveButton);
        mLoadButton = (Button) findViewById(R.id.loadButton);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.saveButton:
                mUserPresenter.saveUser(getID(),getFirstName(),getLastName());
                break;
            case R.id.loadButton:
                mUserPresenter.loadUser(getID());
                break;
        }
    }

    @Override
    public int getID() {
        return Integer.parseInt(mIdEdit.getText().toString());
    }

    @Override
    public String getFirstName() {
        return mFirstNameEdit.getText().toString();
    }

    @Override
    public String getLastName() {
        return mLastNameEdit.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        mFirstNameEdit.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        mLastNameEdit.setText(lastName);
    }
}
