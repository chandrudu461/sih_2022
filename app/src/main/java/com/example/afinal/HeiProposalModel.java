package com.example.afinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HeiProposalModel implements Serializable {

    public String problemStatement,description,expectedDuration;
    public String expectedBudget;
    public String proposalDocumentUri;
    public String heiName;

    public String getNoOfProjects() {
        return noOfProjects;
    }

    public void setNoOfProjects(String noOfProjects) {
        this.noOfProjects = noOfProjects;
    }

    public String getRecentProjectDesc() {
        return recentProjectDesc;
    }

    public void setRecentProjectDesc(String recentProjectDesc) {
        this.recentProjectDesc = recentProjectDesc;
    }

    public String getNoOfOngoingProjects() {
        return noOfOngoingProjects;
    }

    public void setNoOfOngoingProjects(String noOfOngoingProjects) {
        this.noOfOngoingProjects = noOfOngoingProjects;
    }

    public String noOfProjects;
    public String recentProjectDesc;
    public String noOfOngoingProjects;
//    public String
    public String getHeiScore() {
        return heiScore;
    }

    public void setHeiScore(String heiScore) {
        this.heiScore = heiScore;
    }

    public String heiScore;
    public HashMap<String, String> getBudgetMap() {
        return budgetMap;
    }

    public void setBudgetMap(HashMap<String, String> budgetMap) {
        this.budgetMap = budgetMap;
    }

    public HashMap<String,String> budgetMap;



    public String declinedReason;

    public String getDeclinedReason() {
        return declinedReason;
    }

    public void setDeclinedReason(String declinedReason) {
        this.declinedReason = declinedReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String proposalId;
    public String status;
    public String psId;
    public String faUid;
    public String heiUid;

    public String getHeiUid() {
        return heiUid;
    }

    public void setHeiUid(String heiUid) {
        this.heiUid = heiUid;
    }

    public String getFaUid() {
        return faUid;
    }

    public void setFaUid(String faUid) {
        this.faUid = faUid;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getHeiName() {
        return heiName;
    }

    public void setHeiName(String heiName) {
        this.heiName = heiName;
    }

    public ArrayList<MilestonePostModel> milestones;
    public ArrayList<BillModel> bills;

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

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(String expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getExpectedBudget() {
        return expectedBudget;
    }

    public void setExpectedBudget(String expectedBudget) {
        this.expectedBudget = expectedBudget;
    }

    public String getProposalDocumentUri() {
        return proposalDocumentUri;
    }

    public void setProposalDocumentUri(String proposalDocumentUri) {
        this.proposalDocumentUri = proposalDocumentUri;
    }

    public ArrayList<MilestonePostModel> getMilestones() {
        return milestones;
    }

    public void setMilestones(ArrayList<MilestonePostModel> milestones) {
        this.milestones = milestones;
    }


    public HeiProposalModel(){}


    public void setBills(ArrayList<BillModel> bills) {
        this.bills = bills;
    }

    public ArrayList<BillModel> getBills() {
        return bills;
    }
}
