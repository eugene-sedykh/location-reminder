package org.android.app.locationreminder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationsListTask;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

public class LocationsListActivity extends RoboListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private List<Location> locations;

    private ArrayAdapter<Location> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.locations = getLocations();
        this.adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, this.locations);
        getListView().setAdapter(this.adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshDataSet();
    }

    private void refreshDataSet() {
        this.adapter.clear();
        this.adapter.addAll(getLocations());
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AddLocationActivity.class));
    }

    private List<Location> getLocations () {
        try {
            return new LocationsListTask(this).call();
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, e.toString(), 5);
            toast.show();
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.locations_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, AddLocationActivity.class));
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Location location = (Location) getListView().getItemAtPosition(position);
        Intent editIntent = new Intent(this, EditLocationActivity.class);
        editIntent.putExtra(ExtraKeys.LOCATION, location);
        startActivity(editIntent);
    }
}
