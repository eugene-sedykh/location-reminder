package org.android.app.locationreminder.dao.task.reminder;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.RemindersService;
import org.android.app.locationreminder.dao.domain.Reminder;
import roboguice.util.RoboAsyncTask;

public class ReminderSaveTask extends RoboAsyncTask<Long> {
    @Inject
    RemindersService remindersService;

    Reminder reminder;

    public ReminderSaveTask(Context context, Reminder reminder) {
        super(context);
        this.reminder = reminder;
    }

    @Override
    public  Long call() throws Exception {
        return this.remindersService.saveReminder(this.reminder);
    }

    @Override
    protected void onSuccess(Long result) {
        if (result == -1) {
            Toast.makeText(context, "Error is occurred", 5);
            return;
        }

        Toast.makeText(context, "Reminder saved successfully", 5);
    }

    @Override
    protected void onException(Exception e) {
        Toast.makeText(context, e.toString() + "\r\n", 5);
    }
}
