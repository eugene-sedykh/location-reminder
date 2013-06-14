package org.android.app.locationreminder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.google.inject.Inject;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.domain.Reminder;
import org.android.app.locationreminder.dao.task.reminder.RemindersListTask;
import roboguice.activity.RoboListActivity;
import com.google.inject.Provider;

import java.util.List;

public class RemindersListActivity extends RoboListActivity {
    @Inject
    Provider<Context> contextProvider;

    private List<Reminder> reminders;

    private ArrayAdapter<Reminder> adapter;

    final String TAG = "Remnders List";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
		super.onCreate(savedInstanceState);
        this.reminders = getReminders();
        this.adapter = new ArrayAdapter<Reminder>(this, android.R.layout.simple_list_item_1, this.reminders);
        getListView().setAdapter(this.adapter);
	}

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart");
        super.onStart();
        this.reminders = getReminders();
        this.adapter.notifyDataSetChanged();
        getListView().setAdapter(this.adapter);
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume");
        super.onResume();
        this.reminders = getReminders();
        this.adapter.notifyDataSetChanged();
        getListView().setAdapter(this.adapter);
    }

    private List<Reminder> getReminders () {
        try {
            return new RemindersListTask(this.contextProvider.get()).call();
        } catch (Exception e) {
            Toast.makeText(this.contextProvider.get(), e.toString(), 5);
        }
        return null;
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
