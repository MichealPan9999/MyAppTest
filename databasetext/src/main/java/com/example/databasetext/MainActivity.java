package com.example.databasetext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.databasetext.datautil.DataHelper;
import com.example.databasetext.datautil.UserInfo;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button query, insert, modify, delect;
    private Context mContext;
    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        dataHelper = new DataHelper(mContext);
        findViews();
        onBtnClick();
    }

    private void findViews() {
        query = (Button) findViewById(R.id.query);
        insert = (Button) findViewById(R.id.insert);
        modify = (Button) findViewById(R.id.modify);
        delect = (Button) findViewById(R.id.delect);
    }

    private void onBtnClick() {
        query.setOnClickListener(this);
        insert.setOnClickListener(this);
        modify.setOnClickListener(this);
        delect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query:
                showQueryDialog();
                break;
            case R.id.insert:
                showInsertDialog();
                break;
            case R.id.modify:
                showModifyDialog();
                break;
            case R.id.delect:
                showDelDialog();
                break;
        }
    }

    private void showQueryDialog() {
        new AlertDialog.Builder(this)
                .setTitle("查询")
                .setSingleChoiceItems(new String[]{"根据userId", "查询所有"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int witch) {
                        Log.d("panzq", "witch = " + witch);
                        switch (witch) {
                            case 0:
                                final EditText userIdText = new EditText(mContext);
                                new AlertDialog.Builder(mContext).setTitle("根据userId")
                                        .setView(userIdText)
                                        .setPositiveButton("查询", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                String userId = userIdText.getText().toString();
                                                List<UserInfo> allUser = dataHelper.GetUserList(true);
                                                if (allUser.size()>0 && !TextUtils.isEmpty(userId)) {
                                                    UserInfo user = dataHelper.getUserByUserID(userId, allUser);
                                                    Log.d("panzq",user.toString());
                                                    dialogInterface.dismiss();
                                                }
                                            }
                                        })
                                        .setNegativeButton("取消", null)
                                        .show();
                                break;
                            case 1:
                                List<UserInfo> allUser = dataHelper.GetUserList(true);
                                if (allUser.size()>0)
                                    Log.d("panzq",allUser.toString());
                                break;
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null).show();

    }
    private void showInsertDialog() {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final EditText userIdText = new EditText(mContext);
        final EditText userNameText = new EditText(mContext);
        linearLayout.addView(userIdText);
        linearLayout.addView(userNameText);
        new AlertDialog.Builder(this)
                .setTitle("插入")
                .setView(linearLayout)
                .setPositiveButton("插入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userId = userIdText.getText().toString();
                        String userName = userNameText.getText().toString();
                        if (!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(userName))
                        {
                            UserInfo user= new UserInfo();
                            user.setUserId(userId);
                            user.setUserName(userName);
                            dataHelper.SaveUserInfo(user);
                            Log.d("panzq","插入"+user.toString());
                        }
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("取消", null).show();

    }

    private void showDelDialog() {
        new AlertDialog.Builder(this)
                .setTitle("删除")
                .setSingleChoiceItems(new String[]{"根据userId", "删除所有"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int witch) {
                        Log.d("panzq", "witch = " + witch);
                        switch (witch) {
                            case 0:
                                final EditText userIdText = new EditText(mContext);
                                new AlertDialog.Builder(mContext).setTitle("根据userId")
                                        .setView(userIdText)
                                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                String userId = userIdText.getText().toString();
                                                List<UserInfo> allUser = dataHelper.GetUserList(true);
                                                if (allUser.size()>0 && !TextUtils.isEmpty(userId)) {
                                                    int id = dataHelper.DelUserInfo(userId);
                                                    Log.d("panzq","删除 "+id);
                                                    dialogInterface.dismiss();
                                                }
                                            }
                                        })
                                        .setNegativeButton("取消", null)
                                        .show();
                                break;
                            case 1:
                                int id = dataHelper.DelAllUserInfo();
                                Log.d("panzq","删除所有 "+id);
                                break;
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null).show();

    }

    private void showModifyDialog() {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final EditText userIdText = new EditText(mContext);
        final EditText userNameText = new EditText(mContext);
        linearLayout.addView(userIdText);
        linearLayout.addView(userNameText);
        new AlertDialog.Builder(this)
                .setTitle("修改")
                .setView(linearLayout)
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userId = userIdText.getText().toString();
                        String userName = userNameText.getText().toString();
                        if (!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(userName))
                        {
                            UserInfo user= new UserInfo();
                            user.setUserId(userId);
                            user.setUserName(userName);
                            dataHelper.UpdateUserInfoByUserID(userId,userName);
                            Log.d("panzq","修改"+user.toString());
                        }
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("取消", null).show();

    }
}
