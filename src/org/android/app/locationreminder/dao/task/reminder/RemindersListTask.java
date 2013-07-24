package org.android.app.locationreminder.dao.task.reminder;

import android.content.Context;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.RemindersService;
import org.android.app.locationreminder.dao.domain.Reminder;
import roboguice.util.RoboAsyncTask;

import java.util.List;

public class RemindersListTask extends RoboAsyncTask<List<String>>{
    @Inject
    RemindersService remindersService;

    public RemindersListTask(Context context) {
        super(context);
    }

    @Override
    public List<String> call() throws Exception {
        return this.remindersService.getAllReminders();
    }
}
