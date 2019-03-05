package com.example.lot_pr10_fct.data.local;

import android.content.Context;

import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.data.local.model.Converters;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Visit.class, Student.class, Company.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "practices";

    public abstract VisitDao visitDao();
    public abstract StudentDao studentDao();
    public abstract CompanyDao companyDao();

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
