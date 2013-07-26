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
public class LocationsListTask extends RoboAsyncTask<List<Location>> {

    @Inject
    LocationsService locationsService;

    public LocationsListTask(Context context) {
        super(context);
    }

    @Override
    public List<Location> call() throws Exception {
        return this.locationsService.getAll();
    }
}
