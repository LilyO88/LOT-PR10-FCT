package com.example.lot_pr10_fct.data.local;

import com.example.lot_pr10_fct.base.BaseDao;
import com.example.lot_pr10_fct.data.local.model.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CompanyDao extends BaseDao<Company> {

    @Query("SELECT * FROM companies")
    LiveData<List<Company>> queryCompanies();

    @Query("SELECT * FROM companies WHERE id = :companyId")
    LiveData<Company> queryCompany(long companyId);
}
