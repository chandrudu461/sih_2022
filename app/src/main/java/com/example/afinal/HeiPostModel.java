package com.example.afinal;

import android.net.Uri;

import java.io.Serializable;

public class HeiPostModel implements Serializable {
    public String nameOfHei;
    public String yearOfEstablishment;
    public String aicteCode;
    public String link;
    public String imageUri;
    public String selectedstate;
    public String selectedDistrict;
    public String pdfUri;
    public String verify;
    public String declineReason;

    public String uid;
    public String razorPayId;


    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String documentReference;

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public String getRazorPayId() {
        return razorPayId;
    }

    public void setRazorPayId(String razorPayId) {
        this.razorPayId = razorPayId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeclinedReason() {
        return declinedReason;
    }

    public void setDeclinedReason(String declinedReason) {
        this.declinedReason = declinedReason;
    }

    public String declinedReason;



    public String getDocumentReference() {
        return documentReference;
    }

    public void setDocumentReference(String documentReference) {
        this.documentReference = documentReference;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }


    public HeiPostModel(){}
    public HeiPostModel(String nameOfHei,String yearOfEstablishment,String aicteCode,String link,String selectedstate,String selectedDistrict,String verify,String uid){
        this.nameOfHei = nameOfHei;
        this.yearOfEstablishment = yearOfEstablishment;
        this.aicteCode = aicteCode;
        this.link = link;
        this.selectedstate = selectedstate;
        this.selectedDistrict = selectedDistrict;
        this.verify = verify;
        this.uid = uid;
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

}
