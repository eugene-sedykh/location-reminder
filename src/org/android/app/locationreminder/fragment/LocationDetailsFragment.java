package org.android.app.locationreminder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import roboguice.fragment.RoboFragment;

/**
 * Date: 17.09.13
 */
public class LocationDetailsFragment extends RoboFragment {

    private Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        this.location = savedInstanceState.getParcelable(ExtraKeys.LOCATION);
        return inflater.inflate(R.layout.location_details, container, false);
//        if (container != null) {
//            return inflater.inflate(R.layout.location_details, container, false);
//        }
//        else {
//            return null;
//        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
