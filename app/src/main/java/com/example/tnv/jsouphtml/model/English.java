package com.example.tnv.jsouphtml.model;

import java.io.Serializable;

/**
 * Created by TNV on 3/15/2018.
 */

public class English implements Serializable {
    private String mId;
    private String mName;
    private String mUrlImage;
    private String mUrlNextPage;

    public English() {
    }

    public English(String mName, String mUrlImage) {
        this.mName = mName;
        this.mUrlImage = mUrlImage;
    }

    public English(String mName, String mUrlImage, String mUrlNextPage) {
        this.mName = mName;
        this.mUrlImage = mUrlImage;
        this.mUrlNextPage = mUrlNextPage;
    }

    public English(String mId, String mName, String mUrlImage, String mUrlNextPage) {
        this.mId = mId;
        this.mName = mName;
        this.mUrlImage = mUrlImage;
        this.mUrlNextPage = mUrlNextPage;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUrlImage() {
        return mUrlImage;
    }

    public void setmUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
    }

    public String getmUrlNextPage() {
        return mUrlNextPage;
    }

    public void setmUrlNextPage(String mUrlNextPage) {
        this.mUrlNextPage = mUrlNextPage;
    }
}
