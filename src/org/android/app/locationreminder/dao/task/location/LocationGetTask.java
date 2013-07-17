package org.android.app.locationreminder.dao.task.location;

import android.content.Context;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.LocationsService;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.util.RoboAsyncTask;

import java.util.List;

/**
 * Date: 14.04.13
 */
public class LocationGetTask extends RoboAsyncTask<Location> {

    @Inject
    LocationsService locationsService;

    private Integer locationId;

    public LocationGetTask(Context context, Integer locationId) {
        super(context);
        this.locationId = locationId;
    }

    @Override
    public Location call() throws Exception {
        return this.locationsService.getLocationById(this.locationId);
    }
}
