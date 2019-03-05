package com.example.lot_pr10_fct.data.local;

import com.example.lot_pr10_fct.base.BaseDao;
import com.example.lot_pr10_fct.data.local.model.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface StudentDao extends BaseDao<Student> {

    @Query("SELECT * FROM students")
    LiveData<List<Student>> queryStudents();

    @Query("SELECT * FROM students WHERE id = :studentId")
    LiveData<Student> queryStudent(long studentId);
}
