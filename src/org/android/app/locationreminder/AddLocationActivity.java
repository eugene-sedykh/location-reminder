package org.android.app.locationreminder;

import android.os.Bundle;
import roboguice.activity.RoboActivity;

public class AddLocationActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_location_activity);
	}

}
