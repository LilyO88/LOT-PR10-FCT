package com.example.lot_pr10_fct.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "practices";

    private static AppDatabase instance;
//    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
