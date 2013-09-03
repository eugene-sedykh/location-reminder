package org.android.app.locationreminder.fragment;

import java.util.Calendar;

import org.android.app.locationreminder.activity.AddReminderActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DatePickerFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current date as the default date in the picker
	final Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);

	return new DatePickerDialog(getActivity(), (AddReminderActivity)getActivity(), year, month, day);
	}
	
}