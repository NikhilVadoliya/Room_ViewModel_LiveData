package com.pulse.roomdemo.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pulse.roomdemo.R;
import com.pulse.roomdemo.database.table.User;
import com.pulse.roomdemo.helper.DiffUtilCallback;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by nikhil.vadoliya on 22-11-2018.
 */


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    Context context;
    List<User> usersList = new ArrayList<>();

    public UserListAdapter(Context context) {
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
        viewHolder.mTxtName.setText(usersList.get(i).getName());
        viewHolder.mTxtId.setText("" + usersList.get(i).getId());
        Log.i(TAG, "onBindViewHolder: " + i);
    }

    @Override
    public int getItemCount() {
        return usersList == null ? 0 : usersList.size();
    }

    public int getUserId(int pos) {
        return usersList.get(pos).getId();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView mTxtId, mTxtName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtId = itemView.findViewById(R.id.text_id);
            mTxtName = itemView.findViewById(R.id.text_name);
        }
    }

    public void setUserList(List<User> userList) {

        // this method not call every time(item size) onBindViewHolder : only update data on particular item
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(userList, this.usersList));
        this.usersList.clear();
        this.usersList.addAll(userList);
        diffResult.dispatchUpdatesTo(this);

        // this method call every time(item size) onBindViewHolder : take long time when use have long data 100k
       /* this.usersList = new ArrayList<>();
        this.usersList.addAll(userList);
        notifyDataSetChanged();*/
    }


}
