package com.example.orderquick;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomerViewModel implements Parcelable {
    private int imageSrc;
    private String name;
    private String telephone;

    public CustomerViewModel() {
    }

    public CustomerViewModel(int imageSrc, String name, String telephone) {
        this.imageSrc = imageSrc;
        this.name = name;
        this.telephone = telephone;
    }

    protected CustomerViewModel(Parcel in) {
        imageSrc = in.readInt();
        name = in.readString();
        telephone = in.readString();
    }

    public static final Creator<CustomerViewModel> CREATOR = new Creator<CustomerViewModel>() {
        @Override
        public CustomerViewModel createFromParcel(Parcel in) {
            return new CustomerViewModel(in);
        }

        @Override
        public CustomerViewModel[] newArray(int size) {
            return new CustomerViewModel[size];
        }
    };

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageSrc);
        dest.writeString(name);
        dest.writeString(telephone);
    }
}
