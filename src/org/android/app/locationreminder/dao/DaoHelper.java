package org.android.app.locationreminder.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import roboguice.inject.ContextSingleton;

/**
 * Date: 12.04.13
 */
@ContextSingleton
public class DaoHelper extends SQLiteOpenHelper implements Provider<SQLiteDatabase> {

    private static final String DATABASE_NAME = "locationReminder";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase mDb;

    //DatabaseSQLStatement
    public static final String DATABASE_CREATE =
        "Create table if not exists locations(" +
            "_id integer primary key autoincrement, " +
            "title text not null," +
            "mcc_mnc text not null," +
            "lac int," +
            "cid int," +
            "longitude real," +
            "latitude real)";

    @Inject
    public DaoHelper(Provider<Context> contextProvider) {
        super(contextProvider.get(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS locations");
        onCreate(db);
    }

    @Override
    public SQLiteDatabase get() {
        if (mDb == null || !mDb.isOpen()) {
            mDb = getWritableDatabase();
        }
        return mDb;
    }
}

