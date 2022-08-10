package com.unidev.quizapp.contract.impl;

import com.unidev.quizapp.contract.TestScreenContract;
import com.unidev.quizapp.models.TestData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoryImpl implements TestScreenContract.Repository {
    private List<TestData> questions;
    private List<TestData> tests;

    @Override
    public void initQuestions() {
        questions = new ArrayList<>();
        questions.add(new TestData("3*4+2 = ?","14","16","13","12","14"));
        questions.add(new TestData("3+2+2 = ?","7","8","9","10","7"));
        questions.add(new TestData("3*(4+2) = ?","14","12","16","18","18"));
        questions.add(new TestData("(3+4)*2 = ?","14","16","13","12","14"));
        questions.add(new TestData("3*4*2 = ?","22","28","24","26","24"));
        questions.add(new TestData("10*5-14 = ?","38","34","90","36","36"));
        questions.add(new TestData("2*(4+10)+6 = ?","34","32","33","28","34"));
        questions.add(new TestData("2*(22-8)/4 = ?","6","7","5","9","7"));
        questions.add(new TestData("(8+4)*(4+2) = ?","74","78","72","76","72"));
        questions.add(new TestData("3/5 + 2/5 = ?", "1","0","0.5","2","1"));

        tests=questions;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(tests);
    }

    @Override
    public TestData getQuestion(int index) {
        return tests.get(index);
    }

    @Override
    public int getTotalQuestions() {
        return tests.size();
    }
}
