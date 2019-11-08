package com.anamaniac.movieinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDataModel implements Parcelable {
    public Boolean success;
    public String Expiry;
    public String request_token;

    protected UserDataModel(Parcel in) {
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        Expiry = in.readString();
        request_token = in.readString();
    }

    public static final Creator<UserDataModel> CREATOR = new Creator<UserDataModel>() {
        @Override
        public UserDataModel createFromParcel(Parcel in) {
            return new UserDataModel(in);
        }

        @Override
        public UserDataModel[] newArray(int size) {
            return new UserDataModel[size];
        }
    };

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String expiry) {
        Expiry = expiry;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
    public UserDataModel(Boolean Success,String expiry,String Request_token){
        success = Success;
        Expiry = expiry;
        request_token = Request_token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        dest.writeString(Expiry);
        dest.writeString(request_token);
    }
}
