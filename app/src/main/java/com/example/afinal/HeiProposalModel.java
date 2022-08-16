package com.example.afinal;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class HeiProposalModel {

    public String problemStatement,description,expectedDuration;
    public String expectedBudget;
    public String proposalDocumentUri;
    public ArrayList<MilestonePostModel> milestones;

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



}
