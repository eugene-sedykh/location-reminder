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
    public static final String LOCATIONS_TABLE_CREATE =
        "Create table if not exists locations(" +
            "_id integer primary key autoincrement, " +
            "title text not null," +
            "mcc_mnc text not null," +
            "lac int," +
            "cid int," +
            "longitude real," +
            "latitude real)";

    public static final String REMINDERS_TABLE_CREATE =
        "Create table if not exists reminders(" +
            "_id integer primary key autoincrement, " +
            "reminder_title text not null," +
            "date int," +
            "locationId int)";

    @Inject
    public DaoHelper(Provider<Context> contextProvider) {
        super(contextProvider.get(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LOCATIONS_TABLE_CREATE);
        db.execSQL(REMINDERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do we really need to drop the tables with all saved data?
        db.execSQL("DROP TABLE IF EXISTS locations");
        db.execSQL("DROP TABLE IF EXISTS reminders");
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

