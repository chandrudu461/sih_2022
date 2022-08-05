package com.example.afinal;

public class FundingAgencyPostModel {
    public String agencyType,nameOfFundingAgency,yearOfEstablishment,nameOfFunder,phoneNumber,portFolioLink,socialLinks,declarationPdfUri,state,district;
    public Boolean isVerified;

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
