package com.example.homework3.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String name;
    private String data;
    private boolean isPhone;

    public Contact(String name, String data, boolean isPhone)   {
        this.name = name;
        this.data = data;
        this.isPhone = isPhone;
    }

    public Contact(Parcel in){
        String[] data = new String[2];
        in.readStringArray(data);
        boolean[] bData = new boolean[1];
        in.readBooleanArray(bData);
        this.name = data[0];
        this.data = data[1];
        this.isPhone=bData[0];
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public boolean isPhone() {
        return isPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{name,data});
        parcel.writeBooleanArray(new boolean[]{isPhone});
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>(){
        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }
        @Override
        public Contact[] newArray(int i) {
            return new Contact[i];
        }
    };
}
