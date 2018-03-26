package com.example.tnv.jsouphtml.model;

import java.io.Serializable;

/**
 * Created by TNV on 3/15/2018.
 */

public class EnglishDetails implements Serializable{
    private String mImage;
    private String mWord;
    private String mEasyRead;//de doc
    private String mCategory;
    private String mExample;
    private String mTranslate;
    private String mUrlAudio;

    public EnglishDetails() {
    }

    public EnglishDetails(String mImage, String mWord, String mEasyRead, String mCategory, String mExample, String mTranslate) {
        this.mImage = mImage;
        this.mWord = mWord;
        this.mEasyRead = mEasyRead;
        this.mCategory = mCategory;
        this.mExample = mExample;
        this.mTranslate = mTranslate;
    }

    public EnglishDetails(String mImage, String mWord, String mEasyRead, String mCategory, String mExample, String mTranslate, String mUrlAudio) {
        this.mImage = mImage;
        this.mWord = mWord;
        this.mEasyRead = mEasyRead;
        this.mCategory = mCategory;
        this.mExample = mExample;
        this.mTranslate = mTranslate;
        this.mUrlAudio = mUrlAudio;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmWord() {
        return mWord;
    }

    public void setmWord(String mWord) {
        this.mWord = mWord;
    }

    public String getmEasyRead() {
        return mEasyRead;
    }

    public void setmEasyRead(String mEasyRead) {
        this.mEasyRead = mEasyRead;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmExample() {
        return mExample;
    }

    public void setmExample(String mExample) {
        this.mExample = mExample;
    }

    public String getmTranslate() {
        return mTranslate;
    }

    public void setmTranslate(String mTranslate) {
        this.mTranslate = mTranslate;
    }

    public String getmUrlAudio() {
        return mUrlAudio;
    }

    public void setmUrlAudio(String mUrlAudio) {
        this.mUrlAudio = mUrlAudio;
    }
}
