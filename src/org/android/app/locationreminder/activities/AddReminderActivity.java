package org.android.app.locationreminder.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import org.android.app.locationreminder.R;
import roboguice.activity.RoboActivity;

public class AddReminderActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.new_reminder_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Menu items default to never show in the action bar. On most devices this means
	    // they will show in the standard options menu panel when the menu button is pressed.
	    // On xlarge-screen devices a "More" button will appear in the far right of the
	    // Action Bar that will display remaining items in a cascading menu.
	
	    MenuItem actionItemDone = menu.add("Done");
	    MenuItem actionItemCancel = menu.add("Cancel");
	
	    // Items that show as actions should favor the "if room" setting, which will
	    // prevent too many buttons from crowding the bar. Extra items will show in the
	    // overflow area.
	    actionItemDone.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	    actionItemCancel.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	
	    // Items that show as actions are strongly encouraged to use an icon.
	    // These icons are shown without a text description, and therefore should
	    // be sufficiently descriptive on their own.
	    actionItemDone.setIcon(R.drawable.ic_action_done);
	    actionItemCancel.setIcon(R.drawable.ic_action_cancel);
	
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
	    return true;
	}

}
