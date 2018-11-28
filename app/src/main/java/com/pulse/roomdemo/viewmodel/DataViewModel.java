package com.pulse.roomdemo.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pulse.roomdemo.database.AppDatabase;
import com.pulse.roomdemo.database.DatabaseDao;
import com.pulse.roomdemo.database.table.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nikhil.vadoliya on 18-04-2018.
 */

/*
AndroidViewModel :  application lifecycle observer
 */
public class DataViewModel extends AndroidViewModel {

    private ExecutorService executorService;
//Room database not support MutableLiveData
    private LiveData<User> user;
    DatabaseDao databaseDao;

    public DataViewModel(@NonNull Application application) {
        super(application);
        databaseDao = AppDatabase.getDatabase(application).databaseDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<User>> getAllUsers() {
        return databaseDao.getUserList();
    }

    public void addUserData(final User user) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                databaseDao.addUser(user);
            }
        });
    }

    public void deleteUserDataById(final int id) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                databaseDao.deleteUserById(id);

            }
        });
    }
}
