package com.example.lot_pr10_fct.base;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

// M for Model
@SuppressWarnings("unchecked")
public interface BaseDao<M> {

    @Insert
    long insert(M model);

    @Update
    int update(M... model);

    @Delete
    int delete(M... model);

}
