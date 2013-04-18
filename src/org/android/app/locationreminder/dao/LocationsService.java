package org.android.app.locationreminder.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.constant.DatabaseFields;
import org.android.app.locationreminder.dao.constant.DatabaseTables;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.inject.ContextSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13.04.13
 */
@ContextSingleton
public class LocationsService extends BaseService {

    @Inject
    public LocationsService(DaoHelper helper) {
        super(helper);
    }

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<Location>();
        Cursor cursor = getDatabase().query(DatabaseTables.LOCATIONS, new String[]{DatabaseFields.TITLE,
                DatabaseFields.MCC_MNC, DatabaseFields.LAC, DatabaseFields.CID, DatabaseFields.LATITUDE,
                DatabaseFields.LONGITUDE},null,null,null,null,null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                locations.add(convertToLocation(cursor));
            }
            cursor.close();
        }

        return locations;
    }

    private Location convertToLocation(Cursor cursor) {
        Location location = new Location();
        location.setTitle(cursor.getString(0));
        location.setMcc_mnc(cursor.getString(1));
        location.setLac(cursor.getString(2));
        location.setCid(cursor.getString(3));
        location.setLatitude(cursor.getFloat(4));
        location.setLongitude(cursor.getFloat(5));

        return location;
    }

    public long saveLocation(Location location) {
        if (locationExists(location)) {
            throw new SQLException("Location already exists");
        }
        return getDatabase().insertOrThrow(DatabaseTables.LOCATIONS, null, convertToDbObject(location));
    }

    private boolean locationExists(Location location) {
        Cursor cursor = getDatabase().query(DatabaseTables.LOCATIONS, new String[]{DatabaseFields.ID},
                DatabaseFields.MCC_MNC + "=? AND " + DatabaseFields.LAC + "=? AND " + DatabaseFields.CID + "=?",
                new String[]{location.getMcc_mnc(),location.getLac(),location.getCid()},null,null,null);
        return cursor.getCount() != 0;
    }

    private ContentValues convertToDbObject(Location location) {
        ContentValues values = new ContentValues();
        values.put(DatabaseFields.TITLE, location.getTitle());
        values.put(DatabaseFields.MCC_MNC, location.getMcc_mnc());
        values.put(DatabaseFields.LAC, location.getLac());
        values.put(DatabaseFields.CID, location.getCid());
        values.put(DatabaseFields.LATITUDE, location.getLatitude());
        values.put(DatabaseFields.LONGITUDE, location.getLongitude());

        return values;
    }
}
