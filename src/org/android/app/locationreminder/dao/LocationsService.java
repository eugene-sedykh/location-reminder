package org.android.app.locationreminder.dao;

import android.content.ContentValues;
import android.database.Cursor;
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

    public long createLocation(String mcc_mnc, String lac, String cid) {
        ContentValues contentValues = createContentValues(mcc_mnc, lac, cid);
        return getDatabase().insert(DatabaseTables.LOCATIONS, null, contentValues);
    }

    private ContentValues createContentValues(String mcc_mnc, String lac, String cid) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseFields.MCC_MNC, mcc_mnc);
        contentValues.put(DatabaseFields.LAC, lac);
        contentValues.put(DatabaseFields.CID, cid);

        return contentValues;
    }

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<Location>();

        //getDatabase().execSQL("DROP DATABASE 'locationReminder'");
        Cursor cursor = getDatabase().query(DatabaseTables.LOCATIONS, new String[]{DatabaseFields.MCC_MNC,
                DatabaseFields.LAC, DatabaseFields.CID, DatabaseFields.LATITUDE, DatabaseFields.LONGITUDE},
                null,null,null,null,null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                locations.add(convertToLocation(cursor));
            }
        }

        cursor.close();

        return locations;
    }

    private Location convertToLocation(Cursor cursor) {
        Location location = new Location();
        location.setMcc_mnc(cursor.getString(0));
        location.setLac(cursor.getString(1));
        location.setCid(cursor.getString(2));
        location.setLatitude(cursor.getFloat(3));
        location.setLongitude(cursor.getFloat(4));

        return location;
    }
}
