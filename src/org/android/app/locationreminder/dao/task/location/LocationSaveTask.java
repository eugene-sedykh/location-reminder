package org.android.app.locationreminder.dao.task.location;

import android.content.Context;
import android.widget.TextView;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.LocationsService;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.util.RoboAsyncTask;

import java.util.List;

/**
 * Date: 14.04.13
 */
public class LocationSaveTask extends RoboAsyncTask<Long> {

    @Inject
    LocationsService locationsService;

    TextView infoField;

    Location location;

    public LocationSaveTask(Context context, TextView infoField, Location location) {
        super(context);
        this.infoField = infoField;
        this.location = location;
    }

    @Override
    public Long call() throws Exception {
        return this.locationsService.saveLocation(this.location);
    }

    @Override
    protected void onSuccess(Long result) {
        if (result == -1) {
            this.infoField.setText("Error occurred");
            return;
        }

        this.infoField.setText("Location saved successfully\r\n");
        this.infoField.append(this.location.toString());
    }

    @Override
    protected void onException(Exception e) {
        this.infoField.setText(e.toString() + "\r\n");
        this.infoField.append(this.location.toString());
    }
}
