package com.pulse.roomdemo.viewmodel;


/**
 * Copyright (c) 2015-2016 Sailfin Technologies, Pvt. Ltd.  All Rights Reserved.
 * This software is the confidential and proprietary information
 * (Confidential Information) of Sailfin Technologies, Pvt. Ltd.  You shall not
 * disclose or use Confidential Information without the express written
 * agreement of Sailfin Technologies, Pvt. Ltd.
 */

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil.vadoliya on 28-11-2018.
 */


public class MutableViewModel extends ViewModel {

    private MutableLiveData<List<String>> mutableLiveData = new MutableLiveData<>();

    public List<String> list = new ArrayList<>();

    public MutableLiveData<List<String>> getList() {
        return mutableLiveData;
    }



    public void addData(String s) {
        //onChange() called
        // setValue() : set the value and dispatch the value to all the active observers, must be called from main thread.
        //postValue() : post a task to main thread to override value set by setValue(), must be called from background thread.

        List<String> stringList = mutableLiveData.getValue();
        if (stringList == null) {
            List<String> list = new ArrayList<>();
            list.add(s);
            mutableLiveData.setValue(list);
        } else {
            stringList.add(s);
            mutableLiveData.setValue(stringList);

        }

    }

    public void deleteData(int pos) {
        // onChange() called
        // setValue() : set the value and dispatch the value to all the active observers, must be called from main thread.
        // postValue() : post a task to main thread to override value set by setValue(), must be called from background thread.
        List<String> stringList = mutableLiveData.getValue();
        if (stringList != null) {
            stringList.remove(pos);
            mutableLiveData.setValue(stringList);
        }
    }

    public void setStringList(List<String> list) {
        this.list = list;
    }

    public List<String> getStringList() {
        return this.list;
    }
}
