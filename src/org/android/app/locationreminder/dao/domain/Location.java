package org.android.app.locationreminder.dao.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Date: 13.04.13
 */
public class Location implements Parcelable {

    private Integer id;

    private String title;

    private String mcc_mnc;

    private String lac;

    private String cid;

    private float latitude = 0;

    private float longitude = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        builder.append("id=").append(this.id).append(";title=").append(this.title).append(";mcc+mnc=").
                append(this.mcc_mnc).append(";lac=").append(this.lac).append(";cid=").append(this.cid).
                append(";latitude=").append(latitude).append(";longitude=").append(this.longitude).append("}");
        return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.mcc_mnc);
        dest.writeString(this.lac);
        dest.writeString(this.cid);
        dest.writeFloat(this.latitude);
        dest.writeFloat(this.longitude);
    }

    public Location(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.mcc_mnc = in.readString();
        this.lac = in.readString();
        this.cid = in.readString();
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
    }

    public Location() {
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
