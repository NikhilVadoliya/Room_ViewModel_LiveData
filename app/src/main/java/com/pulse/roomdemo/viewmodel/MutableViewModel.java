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

import java.util.List;

/**
 * Created by nikhil.vadoliya on 28-11-2018.
 */


public class MutableViewModel extends ViewModel {

    private MutableLiveData<List<String>> mutableLiveData = new MutableLiveData<>();


    public MutableLiveData<List<String>> getList() {
        return mutableLiveData;
    }

    public void setListString(List<String> listString) {
        mutableLiveData.setValue(listString);
    }

    public void addData(String s) {
        List<String> stringList = mutableLiveData.getValue();
        if (stringList != null) {
            stringList.add(s);
            //onChange() called
//           setValue() : set the value and dispatch the value to all the active observers, must be called from main thread.
//           postValue() : post a task to main thread to override value set by setValue(), must be called from background thread.
            mutableLiveData.setValue(stringList);

        }
    }

    public void deleteData(int pos) {
        List<String> stringList = mutableLiveData.getValue();
        if (stringList != null) {
            stringList.remove(pos);
            //onChange() called
//           setValue() : set the value and dispatch the value to all the active observers, must be called from main thread.
//           postValue() : post a task to main thread to override value set by setValue(), must be called from background thread.
            mutableLiveData.setValue(stringList);
        }
    }
}
