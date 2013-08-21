package org.android.app.locationreminder.dao.task.location;

import android.content.Context;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.LocationsDaoService;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.util.RoboAsyncTask;

/**
 * Date: 14.04.13
 */
public class LocationGetTask extends RoboAsyncTask<Location> {

    @Inject
    LocationsDaoService locationsDaoService;

    private Integer locationId;

    public LocationGetTask(Context context, Integer locationId) {
        super(context);
        this.locationId = locationId;
    }

    @Override
    public Location call() throws Exception {
        return this.locationsDaoService.getLocationById(this.locationId);
    }
}
