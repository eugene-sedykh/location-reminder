package org.android.app.locationreminder.menu;

import android.support.v4.app.FragmentManager;
import org.android.app.locationreminder.fragment.LocationDetailsFragment;
import org.android.app.locationreminder.fragment.LocationsListFragment;
import org.android.app.locationreminder.fragment.ReminderDetailsFragment;
import org.android.app.locationreminder.fragment.RemindersListFragment;

/**
 * Date: 23.09.13
 */
public class DetailsPagerAdapter extends MainPagerAdapter {

    public DetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    protected void initFragments() {
        this.frags[0] = new ReminderDetailsFragment();
        this.frags[1] = new LocationDetailsFragment();
    }
}
