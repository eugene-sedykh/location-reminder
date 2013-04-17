package org.android.app.locationreminder.dao.domain;

/**
 * Date: 13.04.13
 */
public class Location {

    private String title;

    private String mcc_mnc;

    private String lac;

    private String cid;

    private float latitude = 0;

    private float longitude = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMcc_mnc() {
        return mcc_mnc;
    }

    public void setMcc_mnc(String mcc_mnc) {
        this.mcc_mnc = mcc_mnc;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        builder.append("title=").append(this.getTitle()).append(";mcc+mnc=").append(this.mcc_mnc).append(";lac=").
                append(this.lac).append(";cid=").append(this.cid).append(";latitude=").append(latitude).
                append(";longitude=").append(this.longitude).append("}");
        return builder.toString();
    }
}
