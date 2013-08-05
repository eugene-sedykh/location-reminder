package org.android.app.locationreminder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.task.reminder.RemindersListTask;
import roboguice.fragment.RoboListFragment;

import java.util.ArrayList;
import java.util.List;

public class RemindersListFragment extends RoboListFragment {
    @Inject
    Provider<Context> contextProvider;
    private ArrayAdapter<String> adapter;
    final String TAG = "Reminders List";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        this.adapter = new ArrayAdapter<String>(view.getContext(), R.layout.reminders_list_item, new ArrayList<String>());
        getListView().setAdapter(this.adapter);
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart");
        super.onStart();
        this.adapter.clear();
        this.adapter.addAll(getReminders());
        this.adapter.notifyDataSetChanged();
        setListShown(true);
    }

    private List<String> getReminders () {
        try {
            return new RemindersListTask(this.contextProvider.get()).call();
        } catch (Exception e) {
            Toast.makeText(this.contextProvider.get(), e.toString(), 5);
        }
        return null;
    }
}
