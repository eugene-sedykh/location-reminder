package org.android.app.locationreminder.activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.LocationsService;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationSaveTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.util.Date;

@ContentView(R.layout.new_location_activity)
public class AddLocationActivity extends RoboActivity implements View.OnClickListener {
    @InjectView(R.id.getLocationButton) Button locationButton;
    @InjectView(R.id.locationDetailsView) TextView locationDetails;
    @Inject TelephonyManager telephonyManager;
    @Inject LocationsService locationsService;
    @Inject Provider<Context> contextProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        locationDetails.setMovementMethod(new ScrollingMovementMethod());
        locationButton.setOnClickListener(this);
	}

    @Override
    public void onClick(View v) {
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
        Location location = new Location();
        location.setTitle(new Date().toString());
        location.setMcc_mnc(telephonyManager.getNetworkOperator());
        location.setLac(String.valueOf(cellLocation.getLac()));
        location.setCid(String.valueOf(cellLocation.getCid()));

        new LocationSaveTask(this.contextProvider.get(),this.locationDetails,location).execute();
    }

}