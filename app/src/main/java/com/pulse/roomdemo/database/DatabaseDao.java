package com.pulse.roomdemo.database;


/**
 * Copyright (c) 2015-2016 Sailfin Technologies, Pvt. Ltd.  All Rights Reserved.
 * This software is the confidential and proprietary information
 * (Confidential Information) of Sailfin Technologies, Pvt. Ltd.  You shall not
 * disclose or use Confidential Information without the express written
 * agreement of Sailfin Technologies, Pvt. Ltd.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pulse.roomdemo.database.table.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by nikhil.vadoliya on 12-04-2018.
 */

@Dao
public interface DatabaseDao {


    //Room not support mutableLiveData
    @Query("select * from User")
    LiveData<List<User>> getUserList();

    @Query("select * from User where id = :id")
    User getUser(String id);


    @Insert(onConflict = REPLACE)
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("delete from User where id=:id ")
    void deleteUserById(int id);


}
