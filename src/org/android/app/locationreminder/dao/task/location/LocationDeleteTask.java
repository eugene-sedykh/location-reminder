package org.android.app.locationreminder.dao.task.location;

import android.content.Context;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.LocationsService;
import roboguice.util.RoboAsyncTask;

/**
 * Date: 22.07.13
 */
public class LocationDeleteTask extends RoboAsyncTask<Integer> {

    @Inject
    LocationsService locationsService;

    private Integer locationId;

    public LocationDeleteTask(Context context, Integer id) {
        super(context);
        this.locationId = id;
    }

    public Integer call() {
        return this.locationsService.deleteLocation(this.locationId);
    }
}
