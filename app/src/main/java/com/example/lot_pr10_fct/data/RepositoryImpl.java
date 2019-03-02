package com.example.lot_pr10_fct.data;

import com.example.lot_pr10_fct.data.local.CompanyDao;
import com.example.lot_pr10_fct.data.local.StudentDao;
import com.example.lot_pr10_fct.data.local.VisitDao;

public class RepositoryImpl implements Repository {

    private final VisitDao visitDao;
    private final StudentDao studentDao;
    private final CompanyDao companyDao;

    public RepositoryImpl(VisitDao visitDao, StudentDao studentDao, CompanyDao companyDao) {
        this.visitDao = visitDao;
        this.studentDao = studentDao;
        this.companyDao = companyDao;
    }
}
