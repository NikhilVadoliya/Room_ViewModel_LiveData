package com.pulse.roomdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pulse.roomdemo.R;

public class ListSelectorActivity extends AppCompatActivity {

    Button btnRoomLiveData, btnMutableLiveData;


  /*
    In LiveData - Android Developer Documentation, you can see that for LiveData, setValue() & postValue() methods are not public.
    Whereas, in MutableLiveData - Android Developer Documentation, you can see that, MutableLiveData extends LiveData internally and also the two magic methods of LiveData is publicly available in this and they are setValue() & postValue().

    setValue() : set the value and dispatch the value to all the active observers, must be called from main thread.
    postValue() : post a task to main thread to override value set by setValue(), must be called from background thread.

    So, LiveData is immutable. MutableLiveData is LiveData which is mutable & thread-safe.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selector);

        btnRoomLiveData = findViewById(R.id.btn_room_livedata);
        btnMutableLiveData = findViewById(R.id.btn_mutablelivedata);

        btnMutableLiveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListSelectorActivity.this, MutableLiveDataActivity.class));

            }
        });

        btnRoomLiveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListSelectorActivity.this, RoomAndLiveDataActivity.class));
            }
        });

    }
}
