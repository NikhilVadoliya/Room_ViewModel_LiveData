package com.pulse.roomdemo.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pulse.roomdemo.R;
import com.pulse.roomdemo.database.table.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil.vadoliya on 22-11-2018.
 */


public class UserListAdapter extends BaseAdapter {
    private Context context;
    private List<User> items;

    public UserListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public User getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_list_user, parent, false);
        }

        User user = (User) getItem(position);

        TextView textId = convertView.findViewById(R.id.text_id);
        TextView textName = convertView.findViewById(R.id.text_name);

        textId.setText("" + user.getId());
        textName.setText(user.getName());

        return convertView;
    }

    public void setList(List<User> items) {
        this.items = new ArrayList<>();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
