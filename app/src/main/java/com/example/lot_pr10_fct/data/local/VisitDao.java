package com.example.lot_pr10_fct.data.local;

import com.example.lot_pr10_fct.base.BaseDao;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface VisitDao extends BaseDao<Visit> {

//    LiveData<List<Visit>> queryVisits();
//    LiveData<List<Visit>> queryStudentVisits(long studentId);
//    LiveData<Visit> queryVisit(long visitId);
//    void insertVisit(Visit visit);
//    void updateVisit(Visit visit);
//    void deleteVisit(Visit visit);
}
