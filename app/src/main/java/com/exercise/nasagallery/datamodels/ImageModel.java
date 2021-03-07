package com.exercise.nasagallery.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageModel implements Parcelable, Comparable<ImageModel> {

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("explanation")
    @Expose
    private String explanation;

    @SerializedName("hdurl")
    @Expose
    private String hdurl;

    @SerializedName("media_type")
    @Expose
    private String mediaType;

    @SerializedName("service_version")
    @Expose
    private String serviceVersion;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    private Long timeInMs = null;

    protected ImageModel(Parcel in) {
        copyright = in.readString();
        date = in.readString();
        explanation = in.readString();
        hdurl = in.readString();
        mediaType = in.readString();
        serviceVersion = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(copyright);
        parcel.writeString(date);
        parcel.writeString(explanation);
        parcel.writeString(hdurl);
        parcel.writeString(mediaType);
        parcel.writeString(serviceVersion);
        parcel.writeString(title);
        parcel.writeString(url);
    }

    public Long getTimeInMs() {
        return timeInMs;
    }

    public void setTimeInMs(Long timeInMs) {
        this.timeInMs = timeInMs;
    }

    @Override
    public int compareTo(ImageModel o) {
        return this.getTimeInMs().compareTo(o.getTimeInMs());
    }
}
