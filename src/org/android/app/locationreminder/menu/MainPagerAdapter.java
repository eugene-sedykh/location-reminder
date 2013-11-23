package org.android.app.locationreminder.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import org.android.app.locationreminder.fragment.LocationsListFragment;
import org.android.app.locationreminder.fragment.RemindersListFragment;

/**
 * Date: 02.08.13
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    protected final Fragment[] frags = new Fragment[2];

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    protected void initFragments() {
        this.frags[0] = new RemindersListFragment();
        this.frags[1] = new LocationsListFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return this.frags[position];
    }

    @Override
    public int getCount() {
        return this.frags.length;
    }
}
