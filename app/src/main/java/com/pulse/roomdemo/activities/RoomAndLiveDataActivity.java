package com.pulse.roomdemo.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.pulse.roomdemo.viewmodel.DataViewModel;
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

public class RoomAndLiveDataActivity extends AppCompatActivity {


    private Button mBtnDelete, mBtnAdd;
    private RecyclerView mRecyclerViewUser;
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
        setContentView(R.layout.activity_room_livedata);


        init();
        setListener();

        //onData change observe for liveData
        mDataViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                mUserList = users;
                mListAdapter.setUserList(users);
            }
        });

        //insert  data in database
        if (!appDatabase.isTableExits(Constant.TABLE_USER)) {
            for (int i = 1; i < 150; i++) {
                final User user = new User("Name " + i);
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
                mListAdapter.setStringList(mUserList);
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
                    mDataViewModel.deleteUserDataById(mListAdapter.getUserId(0));

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
        mRecyclerViewUser = findViewById(R.id.recycleview);
        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewUser.setHasFixedSize(true);
        mRecyclerViewUser.setItemAnimator(new DefaultItemAnimator());
        appDatabase = AppDatabase.getDatabase(getApplication());
        mDatabaseDao = appDatabase.databaseDao();
        executor = Executors.newSingleThreadExecutor();

        //viewModel init
        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mUserList = new ArrayList<>();

        mListAdapter = new UserListAdapter(getApplicationContext());
        mRecyclerViewUser.setAdapter(mListAdapter);

    }
}
