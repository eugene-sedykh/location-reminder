package org.android.app.locationreminder.menu;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

/**
 * Date: 04.08.13
 */
public class MainMenuTabListener implements ActionBar.TabListener {

    private ViewPager viewPager;

    public MainMenuTabListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
