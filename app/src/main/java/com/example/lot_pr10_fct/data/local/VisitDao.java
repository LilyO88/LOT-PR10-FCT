package com.example.lot_pr10_fct.data.local;

import com.example.lot_pr10_fct.base.BaseDao;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface VisitDao extends BaseDao<Visit> {

    @Query("SELECT * FROM visits as T1 WHERE " +
            "NOT EXISTS(SELECT * FROM visits AS T2 " +
            "WHERE T1.idStudent = T2.idStudent AND T1.date < T2.date)")
    LiveData<List<Visit>> queryVisits();

    @Query("SELECT * FROM visits as T1 WHERE " +
            "NOT EXISTS(SELECT * FROM visits AS T2 " +
            "WHERE T1.idStudent = T2.idStudent AND T1.date < T2.date)" +
            "AND T1.idStudent = :studentId")
    LiveData<Visit> queryLastVisitsStudent(long studentId);

    @Query("SELECT * FROM visits WHERE idStudent = :studentId")
    LiveData<List<Visit>> queryStudentVisits(long studentId);

    @Query("SELECT * FROM visits WHERE id = :visitId")
    LiveData<Visit> queryVisit(long visitId);
}
