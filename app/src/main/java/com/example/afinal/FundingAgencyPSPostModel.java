package com.example.afinal;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class FundingAgencyPSPostModel implements Serializable {
    public String problemStatement;
    public String nameOfFundingAgency;
    public String description;
    public String budget;
    public String duration;
    public String deadline;
    public String eligibility;
    public String deliverables;
    public String uid;
    public String status;

    public String getAccepted_proposal_uid() {
        return accepted_proposal_uid;
    }

    public void setAccepted_proposal_uid(String accepted_proposal_uid) {
        this.accepted_proposal_uid = accepted_proposal_uid;
    }

    public String accepted_proposal_uid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<HeiProposalModel> proposalModels;

    public ArrayList<HeiProposalModel> getProposalModels() {
        return proposalModels;
    }

    public void setProposalModels(ArrayList<HeiProposalModel> proposalModels) {
        this.proposalModels = proposalModels;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String psId;

    public FundingAgencyPSPostModel() {
//        problemStatement=new String("test project");
//        description=new String("des test");
//        budget=new String("7634");
//        duration=new String("5 months");
//        eligibility =new String("eligi test");
//        deliverables=new String("deliv test ");

    }

    public FundingAgencyPSPostModel(String nameOfFundingAgency,
                                    String problemStatement,
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


    public String getNameOfFundingAgency() {
        return nameOfFundingAgency;
    }

    public void setNameOfFundingAgency(String nameOfFundingAgency) {
        this.nameOfFundingAgency = nameOfFundingAgency;
    }



}
