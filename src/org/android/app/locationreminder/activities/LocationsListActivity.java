package org.android.app.locationreminder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class LocationsListActivity extends RoboActivity implements View.OnClickListener {
    @InjectView(R.id.newLocationButton)
    Button newLocationButton;

    @Inject
    Provider<Context> contextProvider;

    @InjectView(R.layout.locations_list_activity)
    View contentView;

    @InjectView(android.R.id.list)
    ListView locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locations_list_activity);
//        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this,
//                this.locationsList.getId(), getLocations());
        List<String> data = new ArrayList<String>();
        data.add("first");
        data.add("second");
        data.add("third");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.contextProvider.get(),this.locationsList.getId(),data);
        Toast.makeText(this.contextProvider.get(), "Adapter created", 5);
        //setContentView(this.locationsList);
        this.locationsList.setAdapter(adapter);
        Toast.makeText(this.contextProvider.get(), "Adapter has been set", 5);
        newLocationButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        try {
//            List<Location> locations = new LocationsListTask(this.contextProvider.get()).call();
//            ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this,
//                    android.R.layout.simple_list_item_1, locations);
////            setListAdapter(adapter);
//        } catch (Exception e) {
//            Toast.makeText(this.contextProvider.get(),e.toString(),5);
//        }
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

}
