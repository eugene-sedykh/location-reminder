package org.android.app.locationreminder.dao.domain;

public class Reminder {

    private String reminderTitle;
    private String date;
    private String locationId;

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
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
