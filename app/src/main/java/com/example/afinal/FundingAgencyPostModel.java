package com.example.afinal;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class FundingAgencyPostModel implements Serializable {
    public String agencyType,nameOfFundingAgency,yearOfEstablishment,nameOfFunder,phoneNumber,portFolioLink,socialLinks,declarationPdfUri,state,district;
    public Boolean isVerified;
    public String razorPayId;
    public String declineReason;
    public Boolean isDeclined;

    public Boolean getDeclined() {
        return isDeclined;
    }

    public void setDeclined(Boolean declined) {
        isDeclined = declined;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String uid;

    public Boolean getIsDeclined() {
        return isDeclined;
    }

    public void setIsDeclined(Boolean isDeclined) {
        this.isDeclined = isDeclined;
    }

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


    public ArrayList<FundingAgencyPSPostModel> getFundingAgencyPSPostModels() {
        return fundingAgencyPSPostModels;
    }

    public void setFundingAgencyPSPostModels(ArrayList<FundingAgencyPSPostModel> fundingAgencyPSPostModels) {
        this.fundingAgencyPSPostModels = fundingAgencyPSPostModels;
    }

    public ArrayList<FundingAgencyPSPostModel> fundingAgencyPSPostModels;

    public String getDocumentReference() {
        return documentReference;
    }

    public void setDocumentReference(String documentReference) {
        this.documentReference = documentReference;
    }

    public String documentReference;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String imageUri;

    public FundingAgencyPostModel(){}
    public FundingAgencyPostModel(String agencyType,String nameOfFundingAgency,String yearOfEstablishment,String nameOfFunder,String phoneNumber,String portFolioLink,String socialLinks,String state,String district,Boolean isVerified)
    {
        this.agencyType = agencyType;
        this.nameOfFundingAgency = nameOfFundingAgency;
        this.yearOfEstablishment = yearOfEstablishment;
        this.nameOfFunder = nameOfFunder;
        this.phoneNumber = phoneNumber;
        this.portFolioLink = portFolioLink;
        this.socialLinks = socialLinks;
        this.declarationPdfUri = declarationPdfUri;
        this.state = state;
        this.district = district;
        this.isVerified = isVerified;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getNameOfFundingAgency() {
        return nameOfFundingAgency;
    }

    public void setNameOfFundingAgency(String nameOfFundingAgency) {
        this.nameOfFundingAgency = nameOfFundingAgency;
    }

    public String getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(String yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public String getNameOfFunder() {
        return nameOfFunder;
    }

    public void setNameOfFunder(String nameOfFunder) {
        this.nameOfFunder = nameOfFunder;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPortFolioLink() {
        return portFolioLink;
    }

    public void setPortFolioLink(String portFolioLink) {
        this.portFolioLink = portFolioLink;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    public String getDeclarationPdfUri() {
        return declarationPdfUri;
    }

    public void setDeclarationPdfUri(String declarationPdfUri) {
        this.declarationPdfUri = declarationPdfUri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

}
