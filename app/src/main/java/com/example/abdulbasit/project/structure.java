package com.example.abdulbasit.project;

import android.os.Parcel;
import android.os.Parcelable;

public class structure implements Parcelable {
    String title;
    String date;
    String time;
    String isSwitch;

    public structure(String d, String da, String ti, String s) {
        title = d;
        date = da;
        time = ti;
        isSwitch = s;
    }

    protected structure(Parcel in) {
        title = in.readString();
        date = in.readString();
        time = in.readString();
        isSwitch = in.readString();
    }

    public structure() {
    }


    public static final Creator<structure> CREATOR = new Creator<structure>() {
        @Override
        public structure createFromParcel(Parcel in) {
            return new structure(in);
        }

        @Override
        public structure[] newArray(int size) {
            return new structure[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(isSwitch);
    }
}
