package com.pulse.roomdemo.helper;


import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.pulse.roomdemo.database.table.User;

import java.util.List;

/**
 * Created by nikhil.vadoliya on 23-11-2018.
 */


public class DiffUtilCallback extends DiffUtil.Callback {

    List<User> newList;
    List<User> oldList;

    public DiffUtilCallback(List<User> newList, List<User> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    /*
    Called by the DiffUtil to decide whether two object represent the same Item.
    If your items have unique ids, this method should check their id equality.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId() == oldList.get(oldItemPosition).getId();
    }

    /*
    Checks whether two items have the same data.You can change its behavior depending on your UI.
    This method is called by DiffUtil only if areItemsTheSame returns true.
    */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).getName().compareTo(oldList.get(oldItemPosition).getName());
        if (result == 0) {
            return true;
        }
        return false;
    }

    /*
    If areItemTheSame return true and areContentsTheSame returns false
     DiffUtil calls this method to get a payload about the change.
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);

    }
}
