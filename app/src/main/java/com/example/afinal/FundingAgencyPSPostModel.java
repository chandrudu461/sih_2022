package com.example.afinal;

import android.util.Log;

public class FundingAgencyPSPostModel {
    public String problemStatement;
    public String description;
    public String budget;
    public String duration;
    public String deadline;
    public String eligibility;
    public String deliverables;

    public FundingAgencyPSPostModel() {
        problemStatement=new String("test project");
        description=new String("des test");
        budget=new String("7634");
        duration=new String("5 months");
        eligibility =new String("eligi test");
        deliverables=new String("deliv test ");

    }

    public FundingAgencyPSPostModel(String problemStatement,
                                    String description,
                                    String budget,
                                    String duration,
                                    String deadline,
                                    String eligibility,
                                    String deliverables){
        this.problemStatement = problemStatement;
        this.description = description;
        this.budget = budget;
        this.duration = duration;
        this.deadline = deadline;
        this.eligibility = eligibility;
        this.deliverables = deliverables;
        Log.d("values", "FundingAgencyPSPostModel: "+this.problemStatement+this.deliverables+this.eligibility);
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }




}
