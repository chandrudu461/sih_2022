package com.example.afinal;

import java.io.Serializable;

public class BillModel implements Serializable {

    public String nameOfPerson;

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public void setNameOfPerson(String nameOfPerson) {
        this.nameOfPerson = nameOfPerson;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String billAmount;

    public void BillModel(){}



}
