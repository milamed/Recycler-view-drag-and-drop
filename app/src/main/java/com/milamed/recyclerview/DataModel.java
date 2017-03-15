package com.milamed.recyclerview;

import java.io.Serializable;

/**
 * Created by milamed on 14/03/17.
 */

public class DataModel implements Serializable {
    private String mTitle;
    private String mDescription;
    private int mImg;
    private boolean isChecked;



    public DataModel() {


    }

    public DataModel(String mTitle, String mDescription, int mImg) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImg = mImg;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean cheked) {
        isChecked = cheked;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public int getmImg() {
        return mImg;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }
}
