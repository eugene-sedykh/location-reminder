package org.android.app.locationreminder;

import android.os.Bundle;
import roboguice.activity.RoboActivity;

public class AddReminderActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_reminder_activity);
	}

}
