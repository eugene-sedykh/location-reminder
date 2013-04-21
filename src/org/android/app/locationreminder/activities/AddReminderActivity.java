package org.android.app.locationreminder.activities;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.android.app.locationreminder.R;
import org.android.app.locationreminder.fragments.DatePickerFragment;
import org.android.app.locationreminder.fragments.TimePickerFragment;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.new_reminder_activity)
public class AddReminderActivity extends RoboFragmentActivity implements OnDateSetListener, OnTimeSetListener {

    @InjectView(R.id.newReminderTitle)
    TextView reminderTitle;
    
    @InjectView(R.id.textViewDate)
    TextView textViewDate;

    @InjectView(R.id.textViewTime)
    TextView textViewTime;
    
    @InjectView(R.id.useLocationSwitcher)
    Switch useLocationSwitcher;
    
    @InjectView(R.id.location_label)
    TextView locationLabel;
    
    @InjectView(R.id.editTextLocation)
    EditText editTextLocation;
    
    boolean useLocation = false;
    
    static final float DISABLED_ALPHA = 0.3f;
    static final float ENABLED_ALPHA = 1f;

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
		//test implementation
	    Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
	    return true;
	}
	
	public void onSwitchClicked(View view) {
	    boolean on = ((Switch) view).isChecked();
	    
	    if (on) {
	        locationLabel.setAlpha(ENABLED_ALPHA);
	        editTextLocation.setAlpha(ENABLED_ALPHA);
	        editTextLocation.setFocusable(true);
	        editTextLocation.setFocusableInTouchMode(true);
	        useLocation = true;
	    } else {
	        locationLabel.setAlpha(DISABLED_ALPHA);
	        editTextLocation.setAlpha(DISABLED_ALPHA);
	        editTextLocation.setText("");
	        editTextLocation.setFocusable(false);
	        editTextLocation.setFocusableInTouchMode(false);
	        useLocation = false;
	    }
	}
	
	public void setDateButtonClick(View v) {
	    DialogFragment newDateFragment = new DatePickerFragment();
	    newDateFragment.show(getFragmentManager(), "date picker");
	}
	
    
    public void onDateSet(DatePicker view, int year, int month, int day) {
        textViewDate.setText(month + "/" + day + "/" + year);
    }
    
	public void setTimeButtonClick(View v) {
		DialogFragment newTimeFragment = new TimePickerFragment();
	    newTimeFragment.show(getFragmentManager(), "time picker");
	}
    
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    	textViewTime.setText(hourOfDay + ":" + minute);
    	}

}
