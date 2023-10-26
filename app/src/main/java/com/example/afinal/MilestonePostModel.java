package com.example.afinal;

import java.io.Serializable;

public class MilestonePostModel implements Serializable {

    public String milestone;
    public String duration;
    public String deliverables;
    public String budget;
    public String deliverableUri;
    public String status;

    public String getMilestoneNumber() {
        return milestoneNumber;
    }

    public void setMilestoneNumber(String milestoneNumber) {
        this.milestoneNumber = milestoneNumber;
    }

    public String milestoneNumber;

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String ps;
    public String psId;

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getDeliverableUri() {
        return deliverableUri;
    }

    public void setDeliverableUri(String deliverableUri) {
        this.deliverableUri = deliverableUri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public MilestonePostModel(){

    }

    public MilestonePostModel(String milestone,String duration,String deliverables){
        this.milestone = milestone;
        this.duration = duration;
        this.deliverables = deliverables;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }
}
