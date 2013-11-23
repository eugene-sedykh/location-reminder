package org.android.app.locationreminder.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.google.inject.Inject;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.LocationsDaoService;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.dao.task.location.LocationDeleteTask;
import org.android.app.locationreminder.dao.task.location.LocationsListTask;
import org.android.app.locationreminder.util.ApplicationUtil;
import org.android.app.locationreminder.util.LocationsUtil;
import roboguice.fragment.RoboListFragment;

import java.util.ArrayList;
import java.util.List;

public class LocationsListFragment extends RoboListFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int ADD_LOCATION_DIALOG_ID = 1;

    private static final String ADD_LOCATION_FRAGMENT_TAG = "addLocationTag";

    private ArrayAdapter<Location> adapter;

    private Fragment fragment;

//    @InjectView(R.id.viewpager)
//    private ViewPager viewPager;

    @Inject
    private LocationsDaoService locationsDaoService;

    @Inject
    private LocationsUtil locationsUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(ADD_LOCATION_DIALOG_ID,R.id.remove_location,0,R.string.remove);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.adapter = new ArrayAdapter<Location>(view.getContext(), android.R.layout.simple_list_item_1,
                new ArrayList<Location>());
        getListView().setAdapter(this.adapter);
        getListView().setOnItemClickListener(this);
        registerForContextMenu(getListView());
//        this.viewPager.setAdapter(new DetailsPagerAdapter(getChildFragmentManager()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshDataSet();
        //setListShown(true);
    }

    private void refreshDataSet() {
        this.adapter.clear();
        this.adapter.addAll(getLocations());
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        ApplicationUtil.showToast("Bla-bla-bla", getActivity());
    }

    private List<Location> getLocations () {
        try {
            return new LocationsListTask(getActivity()).call();
        } catch (Exception e) {
            ApplicationUtil.showToast(e.getMessage(), getActivity());
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_location_bar:
                showNewLocationDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showNewLocationDialog() {
        Location location = this.locationsUtil.getCurrentLocation();
        if (!this.locationsDaoService.locationExists(location)) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ExtraKeys.LOCATION, location);
            DialogFragment fragment = new AddLocationDialogFragment();
            fragment.setArguments(bundle);
            fragment.setTargetFragment(this, ADD_LOCATION_DIALOG_ID);
            fragment.show(getFragmentManager(), ADD_LOCATION_FRAGMENT_TAG);
        }
        else {
            ApplicationUtil.showToast(R.string.locationExists, getActivity());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Location location = (Location) getListView().getItemAtPosition(position);
//        Intent editIntent = new Intent(getActivity(), EditLocationActivity.class);
//        editIntent.putExtra(ExtraKeys.LOCATION, location);
//        startActivity(editIntent);
        this.fragment = new LocationDetailsFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.viewpager,this.fragment,"blablatag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_location_button, menu);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_LOCATION_DIALOG_ID && resultCode == Activity.RESULT_OK) {
            this.refreshDataSet();
        }
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
        int deletedRowsNumber = new LocationDeleteTask(getActivity(), location.getId()).call();
        if (deletedRowsNumber != 0) {
            this.adapter.remove(location);
            this.adapter.notifyDataSetChanged();
        }
    }

    private Location getLocationByPosition(int position) {
        return (Location) getListView().getItemAtPosition(position);
    }

    @Override
    public void onDestroyView() {
        try {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.remove(this.fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        super.onDestroyView();
    }
}
