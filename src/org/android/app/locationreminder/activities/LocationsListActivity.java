package org.android.app.locationreminder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationDeleteTask;
import org.android.app.locationreminder.dao.task.location.LocationsListTask;
import roboguice.activity.RoboListActivity;

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
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.locations_item_menu, menu);
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
        Location location = getLocationByPosition(position);
        Intent editIntent = new Intent(this, EditLocationActivity.class);
        editIntent.putExtra(ExtraKeys.LOCATION, location);
        startActivity(editIntent);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo locationInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.remove_location:
                removeLocation(locationInfo);
                return true;
        }
        return false;
    }

    private void removeLocation(AdapterView.AdapterContextMenuInfo info) {
        Location location = getLocationByPosition(info.position);
        int deletedRowsNumber = new LocationDeleteTask(this, location.getId()).call();
        if (deletedRowsNumber != 0) {
            this.adapter.remove(location);
            this.adapter.notifyDataSetChanged();
        }
    }

    private Location getLocationByPosition(int position) {
        return (Location) getListView().getItemAtPosition(position);
    }
}
