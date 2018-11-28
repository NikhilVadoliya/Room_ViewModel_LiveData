package com.pulse.roomdemo.adapters;


/**
 * Copyright (c) 2015-2016 Sailfin Technologies, Pvt. Ltd.  All Rights Reserved.
 * This software is the confidential and proprietary information
 * (Confidential Information) of Sailfin Technologies, Pvt. Ltd.  You shall not
 * disclose or use Confidential Information without the express written
 * agreement of Sailfin Technologies, Pvt. Ltd.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pulse.roomdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil.vadoliya on 28-11-2018.
 */


public class MutableDataListAdapter extends RecyclerView.Adapter<MutableDataListAdapter.UserViewHolder> {

    Context context;
    List<String> usersList = new ArrayList<>();

    public MutableDataListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_user, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder viewHolder, int i) {
        viewHolder.mTxtName.setText(usersList.get(i));
    }

    @Override
    public int getItemCount() {
        return usersList == null ? 0 : usersList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView mTxtId, mTxtName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtId = itemView.findViewById(R.id.text_id);
            mTxtName = itemView.findViewById(R.id.text_name);
        }
    }

    public void setUserList(List<String> userList) {


        // this method call every time(item size) onBindViewHolder : take long time when use have long data 100k
        this.usersList = new ArrayList<>();
        this.usersList.addAll(userList);
        notifyDataSetChanged();
    }
}
