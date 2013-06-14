package org.android.app.locationreminder.activities;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.domain.Reminder;
import org.android.app.locationreminder.dao.task.reminder.ReminderSaveTask;
import org.android.app.locationreminder.fragments.DatePickerFragment;
import org.android.app.locationreminder.fragments.LocationSelectionDialogFragment;
import org.android.app.locationreminder.fragments.TimePickerFragment;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.util.ArrayList;

@ContentView(R.layout.new_reminder_activity)
public class AddReminderActivity extends RoboFragmentActivity implements OnDateSetListener, OnTimeSetListener, LocationSelectionDialogFragment.NoticeDialogListener {

    @InjectView(R.id.newReminderTitle)
    TextView reminderTitle;
    
    @InjectView(R.id.setDateButton)
    Button setDateButton;
    
    @InjectView(R.id.setTimeButton)
    Button setTimeButton;
    
    @InjectView(R.id.useLocationSwitcher)
    Switch useLocationSwitcher;
    
    @InjectView(R.id.location_label)
    TextView locationLabel;
    
    @InjectView(R.id.setLocationButton)
    Button setLocationButton;

    @Inject
    Provider<Context> contextProvider;
    
    boolean useLocation = false;
    
    static final float DISABLED_ALPHA = 0.3f;
    static final float ENABLED_ALPHA = 1f;
    private static final String TAG = "NewReminderActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
	    MenuItem actionItemDone = menu.add("Done");
	    MenuItem actionItemCancel = menu.add("Cancel");
	
	    actionItemDone.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    actionItemCancel.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	 
	    actionItemDone.setIcon(R.drawable.ic_action_done);
	    actionItemCancel.setIcon(R.drawable.ic_action_cancel);
	
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        if (item.getTitle().equals("Done")) {
            Reminder reminder = new Reminder();
            reminder.setReminderTitle(reminderTitle.getText().toString());
            reminder.setDate(setDateButton.getText().toString());
            reminder.setLocationId(setLocationButton.getText().toString());
            new ReminderSaveTask(this.contextProvider.get(), reminder).execute();
            this.finish();
        }  else if (item.getTitle().equals("Cancel")) {
            this.finish();
        }
	    return true;
	}

    public void onSwitchClicked(View view) {
	    boolean isChecked = ((Switch) view).isChecked();
        Log.v(TAG, "Switcher was changed: " + isChecked);
	    
	    if (isChecked) {
	        locationLabel.setAlpha(ENABLED_ALPHA);
	        setLocationButton.setAlpha(ENABLED_ALPHA);
	        setLocationButton.setClickable(true);
	        useLocation = true;
	    } else {
	        locationLabel.setAlpha(DISABLED_ALPHA);
	        setLocationButton.setAlpha(DISABLED_ALPHA);
	        setLocationButton.setText("Set location");
	        setLocationButton.setClickable(false);
	        useLocation = false;
	    }
	}
	
	public void setDateButtonClick(View v) {
	    DialogFragment newDateFragment = new DatePickerFragment();
	    newDateFragment.show(getFragmentManager(), "date picker");
	}
	
    
    public void onDateSet(DatePicker view, int year, int month, int day) {
        setDateButton.setText(month + "/" + day + "/" + year);
    }
    
	public void setTimeButtonClick(View v) {
		DialogFragment newTimeFragment = new TimePickerFragment();
	    newTimeFragment.show(getFragmentManager(), "time picker");
	}
    
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    	setTimeButton.setText(hourOfDay + ":" + minute);
    	}
    
	public void setLocationsButtonClick(View v) {
		DialogFragment newLocationsFragment = new LocationSelectionDialogFragment();
		newLocationsFragment.show(getFragmentManager(), "location selection");
	}

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String resultLocations) {
        // User touched the dialog's positive button
        if (resultLocations != "") {
            setLocationButton.setText(resultLocations);
        } else {
            setLocationButton.setText("Set location");
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
    }

}
