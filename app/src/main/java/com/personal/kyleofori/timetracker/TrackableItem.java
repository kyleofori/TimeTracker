package com.personal.kyleofori.timetracker;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class TrackableItem implements Parcelable {
    private String name;
    private int level;
    private int hours;
    private String description;
    private Bitmap bmp;

    public TrackableItem(String name, int level, int hours, String description) {
        this.name = name;
        this.level = level;
        this.hours = hours;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getBitmap() {
        return bmp;
    }

    public void setBitmap(Bitmap bmp) {
        this.bmp = bmp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.level);
        dest.writeInt(this.hours);
        dest.writeString(this.description);
        dest.writeParcelable(this.bmp, flags);
    }

    protected TrackableItem(Parcel in) {
        this.name = in.readString();
        this.level = in.readInt();
        this.hours = in.readInt();
        this.description = in.readString();
        this.bmp = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<TrackableItem> CREATOR = new Creator<TrackableItem>() {
        @Override
        public TrackableItem createFromParcel(Parcel source) {
            return new TrackableItem(source);
        }

        @Override
        public TrackableItem[] newArray(int size) {
            return new TrackableItem[size];
        }
    };
}
