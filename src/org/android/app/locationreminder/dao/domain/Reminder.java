package org.android.app.locationreminder.dao.domain;

public class Reminder {

    private String reminderTitle;
    private long date;
    private int locationId;

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        builder.append("title=").append(this.getReminderTitle()).append(";date=").append(this.getDate()).
                append(";locationId=").append(this.getLocationId()).append("}");
        return builder.toString();
    }
}
