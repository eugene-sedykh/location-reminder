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
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationsListTask;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

public class LocationsListActivity extends RoboListActivity implements View.OnClickListener {

    @Inject
    Provider<Context> contextProvider;

    private List<Location> locations;

    private ArrayAdapter<Location> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.locations = getLocations();
        this.adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, this.locations);
        getListView().setAdapter(this.adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.locations = getLocations();
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AddLocationActivity.class));
    }

    private List<Location> getLocations () {
        try {
            return new LocationsListTask(this.contextProvider.get()).call();
        } catch (Exception e) {
            Toast.makeText(this.contextProvider.get(), e.toString(), 5);
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
}
