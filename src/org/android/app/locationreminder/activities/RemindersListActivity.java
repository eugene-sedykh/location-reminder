package org.android.app.locationreminder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import org.android.app.locationreminder.R;
import roboguice.activity.RoboActivity;

public class RemindersListActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminders_list_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent = null;
        switch (item.getItemId()) {
        case R.id.add_new_reminder:
            intent = new Intent(this, AddReminderActivity.class);
            break;
        case R.id.view_locations:
        	intent = new Intent(this, LocationsListActivity.class);
            break;
        default:
        	break;
        }
        startActivity(intent);
        return true;
    }

}