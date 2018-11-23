package com.pulse.roomdemo.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.pulse.roomdemo.DataViewModel;
import com.pulse.roomdemo.R;
import com.pulse.roomdemo.adapters.UserListAdapter;
import com.pulse.roomdemo.database.AppDatabase;
import com.pulse.roomdemo.database.DatabaseDao;
import com.pulse.roomdemo.database.table.User;
import com.pulse.roomdemo.helper.Constant;
import com.pulse.roomdemo.helper.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    private Button mBtnDelete, mBtnAdd;
    private ListView mListViewUser;
    private UserListAdapter mListAdapter;

    private Executor executor;

    //db obj.
    private AppDatabase appDatabase;
    private DatabaseDao mDatabaseDao;

    //viewModel obj.
    private DataViewModel mDataViewModel;

    private List<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        setListener();

        //onData change observe for liveData
        mDataViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                mUserList = users;
                mListAdapter.setList(users);
            }
        });

        //insert  data in database
        if (!appDatabase.isTableExits(Constant.TABLE_USER)) {
            for (int i = 1; i < 10; i++) {
                final User user = new User(0, "Name" + i);
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        mDatabaseDao.addUser(user);
                    }
                });
            }
        }


      /*  // Retrieve the data from db and without viewModel
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mUserList = mDatabaseDao.getUserList().getValue();
                mListAdapter.setList(mUserList);
            }
        });*/


        //delete item by obj.  and without viewModel
       /* executor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabaseDao.deleteUser(new User(2, "Name2"));

            }
        });*/


    }

    private void setListener() {
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserList.size() > 0)
                    mDataViewModel.deleteUserDataById(mListAdapter.getItem(0).getId());

            }
        });
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataViewModel.addUserData(new User(Utility.getRandomName()));
            }
        });
    }


    private void init() {

        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnAdd = findViewById(R.id.btn_add);
        mListViewUser = findViewById(R.id.list_user);

        appDatabase = AppDatabase.getDatabase(getApplication());
        mDatabaseDao = appDatabase.databaseDao();
        executor = Executors.newSingleThreadExecutor();

        //viewModel init
        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mUserList = new ArrayList<>();

        mListAdapter = new UserListAdapter(getApplicationContext());
        mListViewUser.setAdapter(mListAdapter);

    }
}
