package org.android.app.locationreminder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.task.location.LocationsListTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.locations_list_activity)
public class LocationsListActivity extends RoboActivity implements View.OnClickListener {
    @InjectView(R.id.newLocationButton)
    Button newLocationButton;

    @Inject
    Provider<Context> contextProvider;

    @InjectView(R.id.locationsListView)
    TextView locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newLocationButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new LocationsListTask(this.contextProvider.get(), this.locationsList).execute();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AddLocationActivity.class));
    }

}
