package org.android.app.locationreminder.dao.task.location;

import android.content.Context;
import android.widget.TextView;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.LocationsService;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.util.RoboAsyncTask;

/**
 * Date: 14.04.13
 */
public class LocationUpdateTask extends RoboAsyncTask<Integer> {

    @Inject
    LocationsService locationsService;

    Location location;

    public LocationUpdateTask(Context context, Location location) {
        super(context);
        this.location = location;
    }

    @Override
    public Integer call() throws Exception {
        return this.locationsService.updateLocation(this.location);
    }
}
