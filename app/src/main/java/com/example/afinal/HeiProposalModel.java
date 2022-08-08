package com.example.afinal;

import android.widget.ArrayAdapter;

public class HeiProposalModel {

    public String problemStatement,description,expectedDuration;
    public String expectedBudgetUri;
//    public Milestone milestone;
//    public String budgetDivision;
//    public String amountCategory;
    public String proposalDocumentUri;
    public ArrayAdapter<Milestone> milestones;

    public HeiProposalModel(){

    }

    public ArrayAdapter<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(ArrayAdapter<Milestone> milestones) {
        this.milestones = milestones;
    }
    //
//    public Milestone getMilestone() {
//        return milestone;
//    }
//
//    public void setMilestone(Milestone milestone) {
//        this.milestone = milestone;
//    }

    public HeiProposalModel(String problemStatement, String description, String expectedDuration,
                            String expectedBudget, Milestone milestone, String budgetDivision,
                            String amountCategory, String proposalDocument){

        this.problemStatement = problemStatement;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.expectedBudgetUri = expectedBudget;
        this.milestones = milestones;
//        this.budgetDivision = budgetDivision;
//        this.amountCategory = amountCategory;
        this.proposalDocumentUri = proposalDocument;


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

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(String expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getExpectedBudgetUri() {
        return expectedBudgetUri;
    }

    public void setExpectedBudgetUri(String expectedBudgetUri) {
        this.expectedBudgetUri = expectedBudgetUri;
    }




//    public String getBudgetDivision() {
//        return budgetDivision;
//    }
//
//    public void setBudgetDivision(String budgetDivision) {
//        this.budgetDivision = budgetDivision;
//    }


    public String getProposalDocumentUri() {
        return proposalDocumentUri;
    }

    public void setProposalDocumentUri(String proposalDocumentUri) {
        this.proposalDocumentUri = proposalDocumentUri;
    }
}
