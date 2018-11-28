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

import com.pulse.roomdemo.R;
import com.pulse.roomdemo.adapters.MutableDataListAdapter;
import com.pulse.roomdemo.helper.Utility;
import com.pulse.roomdemo.viewmodel.MutableViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.pulse.roomdemo.helper.Utility.getRandomName;

public class MutableLiveDataActivity extends AppCompatActivity {

    private Button mBtnDelete, mBtnAdd;
    private RecyclerView mRecyclerViewUser;
    private MutableDataListAdapter mListAdapter;

    //viewModel obj.
    private MutableViewModel mDataViewModel;

    private List<String> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutable_live_data);

        init();
        setListener();
        mUserList.add(Utility.getRandomName());
        mDataViewModel.setListString(mUserList);
        mDataViewModel.getList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                mUserList = strings;
                mListAdapter.setUserList(mUserList);
            }
        });


    }


    private void setListener() {
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserList.size() > 0)
                    mDataViewModel.deleteData(0);

            }
        });
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataViewModel.addData(getRandomName());
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

        //viewModel init
        mDataViewModel = ViewModelProviders.of(this).get(MutableViewModel.class);

        mUserList = new ArrayList<>();

        mListAdapter = new MutableDataListAdapter(getApplicationContext());
        mRecyclerViewUser.setAdapter(mListAdapter);

    }
}
