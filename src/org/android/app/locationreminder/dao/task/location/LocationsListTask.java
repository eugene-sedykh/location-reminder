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

//    @Override
//    protected void onSuccess(List<Location> locations) {
//        if (locations == null || locations.isEmpty()) {
//            this.locationsList.setText("Locations not found");
//            return;
//        }
//
//        this.locationsList.setText("");
//        for (Location location : locations) {
//            this.locationsList.append(location.toString() + "\r\n\r\n");
//        }
//    }

//    @Override
//    protected void onException(Exception e) {
//        this.locationsList.setText(e.toString());
//    }
}
