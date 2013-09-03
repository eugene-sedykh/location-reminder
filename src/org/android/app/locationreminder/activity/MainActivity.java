package org.android.app.locationreminder.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.menu.MainMenuTabListener;
import org.android.app.locationreminder.menu.PagerAdapter;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

/**
 * Date: 02.08.13
 */
public class MainActivity extends RoboFragmentActivity {

    @InjectView(R.id.viewpager)
    private ViewPager viewPager;

    private ActionBar actionBar;

    private ViewPager.OnPageChangeListener pageChangeListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializeActionBar();
        initializeViewPager();

        createTabs();
    }

    private void initializeActionBar() {
        this.actionBar = getActionBar();
        this.actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        this.actionBar.setDisplayShowTitleEnabled(true);

        this.pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                actionBar.setSelectedNavigationItem(position);
            }
        };
    }

    private void initializeViewPager() {
        this.viewPager.setOnPageChangeListener(this.pageChangeListener);
        this.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        this.viewPager.setCurrentItem(0);
    }

    private void createTabs() {
        ActionBar.TabListener tabListener = new MainMenuTabListener(this.viewPager);

        ActionBar.Tab tab1 = this.actionBar.newTab().setText(R.string.reminders_list_fragment_name).
                setTabListener(tabListener);
        ActionBar.Tab tab2 = this.actionBar.newTab().setText(R.string.locations_list_fragment_name).
                setTabListener(tabListener);
        this.actionBar.addTab(tab1);
        this.actionBar.addTab(tab2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.add_new_reminder:
                intent = new Intent(this, AddReminderActivity.class);
                break;
            case R.id.add_new_location:
                intent = new Intent(this, AddLocationActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
        return true;
    }
}
