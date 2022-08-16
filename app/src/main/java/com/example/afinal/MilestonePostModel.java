package com.example.afinal;
public class MilestonePostModel {

    public String milestone;
    public String duration;
    public String deliverables;

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
