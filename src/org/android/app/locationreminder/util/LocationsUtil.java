package org.android.app.locationreminder.util;

import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.inject.ContextSingleton;

/**
 * Date: 14.08.13
 */
@ContextSingleton
public class LocationsUtil {

    @Inject
    TelephonyManager telephonyManager;

    public Location getCurrentLocation() {
        Location location = new Location();
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
        location.setMcc_mnc(telephonyManager.getNetworkOperator());
        location.setLac(String.valueOf(cellLocation.getLac()));
        location.setCid(String.valueOf(cellLocation.getCid()));
        return location;
    }
}
