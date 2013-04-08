package org.android.app.locationreminder;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.util.Date;

@ContentView(R.layout.new_location_activity)
public class AddLocationActivity extends RoboActivity implements LocationListener, View.OnClickListener {
    @InjectView(R.id.getLocationButton) Button locationButton;
    @InjectView(R.id.locationDetailsView) TextView locationDetails;
    @Inject LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        locationButton.setMovementMethod(new ScrollingMovementMethod());
        locationButton.setOnClickListener(this);
	}

    @Override
    public void onClick(View v) {
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,400,1,this);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        locationDetails.append(new Date().toString() + "\r\n");
        locationDetails.append(location.toString() + "\r\n\r\n");
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderEnabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderDisabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
