package org.android.app.locationreminder.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Date: 11.04.13
 */
public class BaseService {

    private SQLiteDatabase database;

    private DaoHelper daoHelper;

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public BaseService(DaoHelper helper) {
        this.database = helper.get();
        this.daoHelper = helper;
    }

    @Override
    protected void finalize() throws Throwable {
        try{
            this.daoHelper.close();
        }
        finally {
            super.finalize();
        }
    }
}
