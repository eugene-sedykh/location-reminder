package org.android.app.locationreminder.activities;

import android.os.Bundle;
import org.android.app.locationreminder.R;
import roboguice.activity.RoboActivity;

public class AddReminderActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_reminder_activity);
	}

}
