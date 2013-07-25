package org.android.app.locationreminder.fragments;

import java.util.Calendar;

import org.android.app.locationreminder.activities.AddReminderActivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

public class TimePickerFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current time as the default values for the picker
	final Calendar c = Calendar.getInstance();
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE);

	return new TimePickerDialog(getActivity(), (AddReminderActivity)getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

}