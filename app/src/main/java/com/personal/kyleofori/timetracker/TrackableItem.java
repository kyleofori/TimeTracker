package com.personal.kyleofori.timetracker;

import android.widget.ImageView;

public class TrackableItem {
    private String name;
    private int level;
    private int hours;
    private String description;
    private ImageView icon;

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

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }
}
