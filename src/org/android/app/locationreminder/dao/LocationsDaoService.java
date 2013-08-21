package org.android.app.locationreminder.dao;

import android.database.Cursor;
import android.database.SQLException;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.constant.DatabaseFields;
import org.android.app.locationreminder.dao.constant.DatabaseTables;
import org.android.app.locationreminder.dao.converter.LocationConverter;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.inject.ContextSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13.04.13
 */
@ContextSingleton
public class LocationsDaoService extends BaseService {

    private LocationConverter converter;

    @Inject
    public LocationsDaoService(DaoHelper helper, LocationConverter converter) {
        super(helper);
        this.converter = converter;
    }

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<Location>();
        Cursor cursor = getDatabase().query(DatabaseTables.LOCATIONS, new String[]{DatabaseFields.ID, DatabaseFields.TITLE,
                DatabaseFields.MCC_MNC, DatabaseFields.LAC, DatabaseFields.CID, DatabaseFields.LATITUDE,
                DatabaseFields.LONGITUDE}, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                locations.add(this.converter.convertToLocation(cursor));
            }
            cursor.close();
        }

        return locations;
    }

    public long saveLocation(Location location) {
        if (locationExists(location)) {
            throw new SQLException("Location already exists");
        }
        return getDatabase().insertOrThrow(DatabaseTables.LOCATIONS, null, this.converter.convertToDbObject(location));
    }

    public boolean locationExists(Location location) {
        Cursor cursor = getDatabase().query(DatabaseTables.LOCATIONS, new String[]{DatabaseFields.ID},
                DatabaseFields.MCC_MNC + "=? AND " + DatabaseFields.LAC + "=? AND " + DatabaseFields.CID + "=?",
                new String[]{location.getMcc_mnc(), location.getLac(), location.getCid()}, null, null, null);
        return cursor.getCount() != 0;
    }

    public Location getLocationById(Integer locationId) {
        Cursor cursor = getDatabase().query(DatabaseTables.LOCATIONS, new String[]{DatabaseFields.ID, DatabaseFields.TITLE,
                DatabaseFields.MCC_MNC, DatabaseFields.LAC, DatabaseFields.CID, DatabaseFields.LATITUDE,
                DatabaseFields.LONGITUDE}, DatabaseFields.ID + "=?", new String[]{locationId.toString()}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            return this.converter.convertToLocation(cursor);
        }

        return null;
    }

    public int updateLocation(Location location) {
        return getDatabase().update(DatabaseTables.LOCATIONS, this.converter.convertToDbObject(location),
                DatabaseFields.ID + "=?", new String[]{location.getId().toString()});
    }

    public Integer deleteLocation(Integer locationId) {
        return getDatabase().delete(DatabaseTables.LOCATIONS, DatabaseFields.ID + "=?",
                new String[]{locationId.toString()});
    }
}
