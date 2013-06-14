package org.android.app.locationreminder.dao.domain;

public class Reminder {

    private String reminder_title;

    private String date;

    private String locationId;

    public String getReminderTitle() {
        return reminder_title;
    }

    public void setReminderTitle(String reminder_title) {
        this.reminder_title = reminder_title;
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
