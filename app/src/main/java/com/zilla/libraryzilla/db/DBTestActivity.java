/*
 * Copyright (c) 2015. Zilla Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zilla.libraryzilla.db;

import android.os.Handler;
import android.os.Message;

import com.github.snowdream.android.util.Log;
import com.zilla.android.zillacore.libzilla.db.DBOperator;
import com.zilla.android.zillacore.libzilla.lifecircle.annotation.LifeCircleInject;
import com.zilla.android.zillacore.libzilla.ui.annotatioin.InjectLayout;
import com.zilla.android.zillacore.libzilla.util.Util;
import com.zilla.libraryzilla.R;
import com.zilla.libraryzilla.common.BaseActivity;
import com.zilla.libraryzilla.db.po.User;
import com.zilla.libraryzilla.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

@InjectLayout(R.layout.activity_dbtest)
public class DBTestActivity extends BaseActivity {

    @LifeCircleInject
    public LoadingDialog loadingDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    loadingDialog.dismiss();
                    Util.toastMsg("success");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        testDB();
    }


    private void testDB() {
        loadingDialog.show("testDB...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Delete all rows
                DBOperator.getInstance().deleteAll(User.class);

                //save
                User user = new User();
                user.setName("user1");
                user.setEmail("user1@example.com");
                user.setAddress("user1 address");
                DBOperator.getInstance().save(user);

                //save list
                List<User> userList = new ArrayList<User>();
                for (int i = 0; i < 100; i++) {
                    User u = new User();
                    u.setName("name" + i);
                    u.setEmail("name" + i + "@example.com");
                    u.setAddress("address" + i);
                    userList.add(u);
                }
                DBOperator.getInstance().saveList(userList);

                //Query
                User user1 = DBOperator.getInstance().query(User.class,"address = ?",new String[]{"address1"});
                Log.i("user1:" + user1.toString());

                DBOperator.getInstance().update(user1);

                //query all rows
                List<User> users = DBOperator.getInstance().queryAll(User.class);
                for (User u : users) {
                    Log.i(u.toString());
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();


    }
}