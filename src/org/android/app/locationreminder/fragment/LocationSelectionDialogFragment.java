package org.android.app.locationreminder.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import org.android.app.locationreminder.R;

public class LocationSelectionDialogFragment extends DialogFragment {
	String[] locations = {"home", "work", "school", "university", "store", "bar", "library", "ikea", "parents home", "test location 1", "test location 2"};
	ArrayList<Integer> mSelectedItems;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String resultLocations);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(getString(R.string.location_picker_title))
        // Specify the list array, the items to be selected by default (null for none),
        // and the listener through which to receive callbacks when items are selected
               .setMultiChoiceItems(locations, null, new DialogInterface.OnMultiChoiceClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which,
                                       boolean isChecked) {
                       if (isChecked) {
                           // If the user checked the item, add it to the selected items
                           mSelectedItems.add(which);
                       } else if (mSelectedItems.contains(which)) {
                           // Else, if the item is already in the array, remove it
                           mSelectedItems.remove(Integer.valueOf(which));
                       }
                   }
               })
        // Set the action buttons
               .setPositiveButton(getString(R.string.location_picker_positive_button), new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       // User clicked OK, so save the mSelectedItems results somewhere
                       // or return them to the component that opened the dialog
                       String resultString = "";
                       for (int item: mSelectedItems) {
                           if (resultString.equals("")) {
                               resultString = resultString + locations[item];
                           } else {
                               resultString = resultString + ", " + locations[item];
                           }
                       }
                       mListener.onDialogPositiveClick(LocationSelectionDialogFragment.this, resultString);
                   }
               })
               .setNegativeButton(getString(R.string.location_picker_negative_button), new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       mListener.onDialogNegativeClick(LocationSelectionDialogFragment.this);
                   }
               });

        return builder.create();
    }

}
