package com.unidev.quizapp.models;

public class TestData {
    private String question;
    private String variantA;
    private String varinatB;
    private String varinatC;
    private String varinatD;
    private String answer;

    public TestData(String question, String variantA, String varinatB, String varinatC, String varinatD,String answer) {
        this.question = question;
        this.variantA = variantA;
        this.varinatB = varinatB;
        this.varinatC = varinatC;
        this.varinatD = varinatD;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getVariantA() {
        return variantA;
    }

    public String getVarinatB() {
        return varinatB;
    }

    public String getVarinatC() {
        return varinatC;
    }

    public String getVarinatD() {
        return varinatD;
    }
    public String getAnswer(){
        return answer;
    }
}
