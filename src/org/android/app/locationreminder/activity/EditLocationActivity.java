package org.android.app.locationreminder.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationUpdateTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.new_location_activity)
public class EditLocationActivity extends RoboActivity implements View.OnClickListener {
    @InjectView(R.id.getLocationButton) Button locationButton;
    @InjectView(R.id.locationTitle) TextView locationTitle;

    private Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.location = getIntent().getParcelableExtra(ExtraKeys.LOCATION);
        this.locationTitle.setText(this.location.getTitle());
        locationButton.setOnClickListener(this);
	}

    @Override
    public void onClick(View v) {
        try {
            updateLocation();
            new LocationUpdateTask(this,this.location).call();
            finish();
        } catch (Exception e) {
            showExceptionToast(e);
        }
    }

    private void updateLocation() {
        this.location.setTitle(this.locationTitle.getText().toString());
    }

    private void showExceptionToast(Exception e) {
        Toast toast = Toast.makeText(this, e.toString(), 5);
        toast.show();
    }
}
