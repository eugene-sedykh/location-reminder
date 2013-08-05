package org.android.app.locationreminder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.android.app.locationreminder.activity.AddLocationActivity;
import org.android.app.locationreminder.activity.EditLocationActivity;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationsListTask;
import roboguice.fragment.RoboListFragment;

import java.util.ArrayList;
import java.util.List;

public class LocationsListFragment extends RoboListFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayAdapter<Location> adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.adapter = new ArrayAdapter<Location>(view.getContext(), android.R.layout.simple_list_item_1,
                new ArrayList<Location>());
        getListView().setAdapter(this.adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshDataSet();
        setListShown(true);
    }

    private void refreshDataSet() {
        this.adapter.clear();
        this.adapter.addAll(getLocations());
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        makeToast("Bla-bla-bla");
    }

    private List<Location> getLocations () {
        try {
            return new LocationsListTask(getActivity()).call();
        } catch (Exception e) {
            makeToast(e.getMessage());
        }
        return null;
    }

    private void makeToast(String text) {
        Toast toast = Toast.makeText(getActivity(), text.toString(), 5);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(getActivity(), AddLocationActivity.class));
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Location location = (Location) getListView().getItemAtPosition(position);
        Intent editIntent = new Intent(getActivity(), EditLocationActivity.class);
        editIntent.putExtra(ExtraKeys.LOCATION, location);
        startActivity(editIntent);
    }
}
