package org.android.app.locationreminder.dao.converter;

import android.content.ContentValues;
import android.database.Cursor;
import org.android.app.locationreminder.dao.constant.DatabaseFields;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.inject.ContextSingleton;

/**
 * Date: 11.07.13
 */
@ContextSingleton
public class LocationConverter {

    public Location convertToLocation(Cursor cursor) {
        Location location = new Location();
        location.setId(cursor.getInt(0));
        location.setTitle(cursor.getString(1));
        location.setMcc_mnc(cursor.getString(2));
        location.setLac(cursor.getString(3));
        location.setCid(cursor.getString(4));
        location.setLatitude(cursor.getFloat(5));
        location.setLongitude(cursor.getFloat(6));

        return location;
    }

    public ContentValues convertToDbObject(Location location) {
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
