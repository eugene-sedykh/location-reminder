package org.android.app.locationreminder.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class LocationSelectionDialogFragment extends DialogFragment {
	String locations[] = {"home", "work", "school", "university", "store", "bar", "library", "ikea", "parents home", "test location 1", "test location 2"};
	ArrayList<Integer> mSelectedItems;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle("Select location")
        // Specify the list array, the items to be selected by default (null for none),
        // and the listener through which to receive callbacks when items are selected
               .setMultiChoiceItems(locations, null,
                          new DialogInterface.OnMultiChoiceClickListener() {
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
               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       // User clicked OK, so save the mSelectedItems results somewhere
                       // or return them to the component that opened the dialog
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
        
                   }
               });

        return builder.create();
    }
}
