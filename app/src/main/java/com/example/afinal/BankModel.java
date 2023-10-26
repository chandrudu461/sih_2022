package com.example.afinal;


import java.io.Serializable;
import java.util.ArrayList;


public class BankModel implements Serializable {
    String bankName;
    ArrayList<String> upis;

    public ArrayList<String> getUpis() {
        return upis;
    }

    public void setUpis(ArrayList<String> upis) {
        this.upis = upis;
    }

    String bankAccountNo;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    String ifscCode;
    String branchName;

    public BankModel() {

    }

}