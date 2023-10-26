package com.example.afinal;

import android.telephony.TelephonyCallback;

import androidx.core.content.res.FontResourcesParserCompat;

public class MilestoneFurtherPostModel {
    String name;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MilestoneFurtherPostModel(String name,String value){
        this.name = name;
        this.value = value;
    }

    public MilestoneFurtherPostModel(){}
}
