package com.pulse.roomdemo.database;



import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.Cursor;

import com.pulse.roomdemo.database.table.User;
import com.pulse.roomdemo.helper.Constant;

/**
 * Created by nikhil.vadoliya on 13-04-2018.
 */


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DatabaseDao databaseDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Constant.DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public boolean isTableExits(String tableName) {
        if (INSTANCE != null) {
            Cursor cursor = INSTANCE.query("SELECT * FROM " + tableName, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
        }
        return false;
    }


}
