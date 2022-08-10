package com.unidev.quizapp.contract.impl;

import android.app.Activity;
import android.content.Intent;

import com.unidev.quizapp.MainActivity;
import com.unidev.quizapp.contract.TestScreenContract;
import com.unidev.quizapp.models.TestData;
import com.unidev.quizapp.ui.HomeActivity;

public class PresenterImpl implements TestScreenContract.Presenter {

    private TestScreenContract.View view;
    private TestScreenContract.Repository repository;
    private int level;
    private int selectAnswer = -1;
    private int totalQuestion;
    private int correntAnswerCount;

    public PresenterImpl(TestScreenContract.View view, TestScreenContract.Repository repository) {
        this.view = view;
        this.repository = repository;
        init();
    }

    private void init() {
        view.initViews();
        repository.initQuestions();
        repository.shuffle();
        view.nextButtonState(false);
        totalQuestion = repository.getTotalQuestions();
        view.loadQuestions(repository.getQuestion(level));
        view.changeState((level + 1), totalQuestion);
    }

    @Override
    public void selectAnswer(int postion) {
        view.nextButtonState(true);
        if (selectAnswer > -1 && selectAnswer != postion) {
            view.clearCheck(selectAnswer);
        }
        selectAnswer = postion;
    }

    @Override
    public void nextQuestion(boolean timeOver) {
        boolean isCompeleted = isTestCompleted();
        if(isCompeleted || timeOver){
            view.result(correntAnswerCount);
            return;
        }
        level++;
        view.clearCheck(selectAnswer);
        view.nextButtonState(false);
        TestData testData = repository.getQuestion(level);
        view.loadQuestions(testData);
        view.changeState(level+1,totalQuestion);

    }

    @Override
    public void restartGame() {
        selectAnswer=-1;
        level = 0;
        init();
    }



    private boolean isTestCompleted() {
        TestData testData = repository.getQuestion(level);
        String userAnswer;
        switch (selectAnswer) {
            case 0:
                userAnswer = testData.getVariantA();
                break;
            case 1:
                userAnswer = testData.getVarinatB();
                break;
            case 2:
                userAnswer = testData.getVarinatC();
                break;
            default:
                userAnswer = testData.getVarinatD();
        }
        if(testData.getAnswer().equals(userAnswer)){
            correntAnswerCount++;
        }
        return totalQuestion-1 == level;
    }

}
