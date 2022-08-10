package com.unidev.quizapp.contract;

import com.unidev.quizapp.models.TestData;

public interface TestScreenContract {
    interface View {
        void initViews();

        void loadQuestions(TestData testData);

        void clearCheck(int position);

        void result(int currentAnswersCount);

        void changeState(int currentAnswerIndex, int totalQuestionsCount);

        void nextButtonState(boolean state);
        void finishGame();
    }

    interface Repository {
        void initQuestions();

        void shuffle();

        TestData getQuestion(int index);

        int getTotalQuestions();
    }

    interface Presenter {
        void selectAnswer(int position);

        void nextQuestion(boolean timeOver);

        void restartGame();

    }
}
