package org.android.app.locationreminder.config;

import android.database.sqlite.SQLiteDatabase;
import com.google.inject.AbstractModule;
import org.android.app.locationreminder.dao.DaoHelper;

/**
 * Date: 12.04.13
 */
public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        requestInjection(DaoHelper.class);
        bind(SQLiteDatabase.class).toProvider(DaoHelper.class);
    }
}
