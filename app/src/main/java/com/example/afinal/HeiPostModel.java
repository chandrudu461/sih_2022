package com.example.afinal;

import android.net.Uri;

public class HeiPostModel {
    public String nameOfHei;
    public String yearOfEstablishment;
    public String aicteCode;
    public String link;
    public String selectedstate;
    public String selectedDistrict;
    public String pdfUri;

    public void setImageUri(String toString) {
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }


    public String imageUri;
    Boolean isVerified;
    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }


    public HeiPostModel(){}
    public HeiPostModel(String nameOfHei,String yearOfEstablishment,String aicteCode,String link,String selectedstate,String selectedDistrict,Boolean isVerified){
        this.nameOfHei = nameOfHei;
        this.yearOfEstablishment = yearOfEstablishment;
        this.aicteCode = aicteCode;
        this.link = link;
        this.selectedstate = selectedstate;
        this.selectedDistrict = selectedDistrict;
        this.isVerified = isVerified;
    }

    public String getNameOfHei() {
        return nameOfHei;
    }

    public void setNameOfHei(String nameOfHei) {
        this.nameOfHei = nameOfHei;
    }

    public String getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(String yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public String getAicteCode() {
        return aicteCode;
    }

    public void setAicteCode(String aicteCode) {
        this.aicteCode = aicteCode;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSelectedstate() {
        return selectedstate;
    }

    public void setSelectedstate(String selectedstate) {
        this.selectedstate = selectedstate;
    }

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(String selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
