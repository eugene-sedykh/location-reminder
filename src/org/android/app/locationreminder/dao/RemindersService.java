package org.android.app.locationreminder.dao;

import android.content.ContentValues;
import android.database.Cursor;
import com.google.inject.Inject;
import org.android.app.locationreminder.dao.constant.DatabaseFields;
import org.android.app.locationreminder.dao.constant.DatabaseTables;
import org.android.app.locationreminder.dao.domain.Reminder;
import roboguice.inject.ContextSingleton;

import java.util.ArrayList;
import java.util.List;

@ContextSingleton
public class RemindersService extends BaseService{
    @Inject
    public RemindersService(DaoHelper helper) {
        super(helper);
    }

    public List<Reminder> getAllReminders() {
        List<Reminder> locations = new ArrayList<Reminder>();
        Cursor cursor = getDatabase().query(DatabaseTables.REMINDERS, new String[]{DatabaseFields.REMINDER_TITLE,
                DatabaseFields.DATE, DatabaseFields.LOCATION_ID},null,null,null,null,null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                locations.add(convertToReminder(cursor));
            }
            cursor.close();
        }

        return locations;
    }

    private Reminder convertToReminder(Cursor cursor) {
        Reminder reminder = new Reminder();
        reminder.setReminderTitle(cursor.getString(0));
        reminder.setDate(cursor.getString(1));
        reminder.setLocationId(cursor.getString(2));

        return reminder;
    }

    public long saveReminder(Reminder reminder) {
        //reminder with the same data could be save a few times
        return getDatabase().insertOrThrow(DatabaseTables.REMINDERS, null, convertToDbObject(reminder));
    }

    private ContentValues convertToDbObject(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DatabaseFields.REMINDER_TITLE, reminder.getReminderTitle());
        values.put(DatabaseFields.DATE, reminder.getDate());
        values.put(DatabaseFields.LOCATION_ID, reminder.getLocationId());

        return values;
    }
}
