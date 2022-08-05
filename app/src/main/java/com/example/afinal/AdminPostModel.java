package com.example.afinal;


public class AdminPostModel {
    public String name;
    public String empID;
    public String phoneNumber;
    public String department;
    public String imageUrl;
    public String pdfIdUri;

    public String getPdfIdUri() {
        return pdfIdUri;
    }

    public void setPdfIdUri(String pdfIdUri) {
        this.pdfIdUri = pdfIdUri;
    }

    public String getPdfDeclarationDocUri() {
        return pdfDeclarationDocUri;
    }

    public void setPdfDeclarationDocUri(String pdfDeclarationDocUri) {
        this.pdfDeclarationDocUri = pdfDeclarationDocUri;
    }

    public String getPdfDocUri() {
        return pdfDocUri;
    }

    public void setPdfDocUri(String pdfDocUri) {
        this.pdfDocUri = pdfDocUri;
    }

    public String pdfDeclarationDocUri;
    public String pdfDocUri;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String docId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String gender;

    public AdminPostModel(){}




}
