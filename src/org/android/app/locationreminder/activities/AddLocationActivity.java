package org.android.app.locationreminder.activities;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.inject.Inject;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.LocationsService;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        locationDetails.setMovementMethod(new ScrollingMovementMethod());
        locationButton.setOnClickListener(this);
	}

    @Override
    public void onClick(View v) {
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

        this.locationsService.createLocation(telephonyManager.getNetworkOperator(), String.valueOf(cellLocation.getLac()),
                String.valueOf(cellLocation.getCid()));

        locationDetails.append(new Date().toString() + "\r\n");
        locationDetails.append("Country ISO: " + telephonyManager.getNetworkCountryIso() + "\r\n");
        locationDetails.append("Operator: " + telephonyManager.getNetworkOperator() + "\r\n");
        locationDetails.append("Operator name: " + telephonyManager.getNetworkOperatorName() + "\r\n");
        locationDetails.append("Sim operator: " + telephonyManager.getSimOperator() + "\r\n");
        locationDetails.append("Sim operator name: " + telephonyManager.getSimOperatorName() + "\r\n");
        locationDetails.append(cellLocation.toString() + "\r\n\r\n");
    }

}
