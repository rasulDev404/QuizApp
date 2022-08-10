package com.unidev.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.unidev.quizapp.contract.TestScreenContract;
import com.unidev.quizapp.contract.impl.PresenterImpl;
import com.unidev.quizapp.contract.impl.RepositoryImpl;
import com.unidev.quizapp.models.TestData;
import com.unidev.quizapp.ui.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TestScreenContract.View {
    TestScreenContract.Presenter presenter;
    List<CardView> list;
    List<TextView> listTitleVariants;
    private TextView idQuestion;
    private List<TextView> variantBtns;
    private Button btnNext;
    private TextView idLevel;
    private TextView textViewCountDown;
    private static final long START_TIMER_IN_MILLIS = 60000;
    private long mTimerLeftCount = START_TIMER_IN_MILLIS;
    private boolean timeOver = false;
    private Button btnRestart;
    private Button btnFinish;
    private Button btnFinishMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl(this, new RepositoryImpl());
        startTimer();
    }
    private void startTimer(){
        new CountDownTimer(mTimerLeftCount, 1000) {
            @Override
            public void onTick(long l) {
                mTimerLeftCount = l;
                updateTextViewTimer();
            }

            @Override
            public void onFinish() {
                getResult();
            }
        }.start();
    }
    public void showResult(int correctCount){
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_gameover,null);
        TextView tvResult = view.findViewById(R.id.correctAnswerCount);
        btnRestart = view.findViewById(R.id.dialog_btn_restart);
        btnFinish = view.findViewById(R.id.dialog_btn_finish);

        tvResult.setText(String.valueOf(correctCount));
        dialog.setContentView(view);
        dialog.setCancelable(false);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                restartGame();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }
    private void restartGame(){
        presenter.restartGame();
    }
    private void getResult(){
        timeOver = true;
        presenter.nextQuestion(timeOver);
    }
    private void updateTextViewTimer(){
        int minuts = (int) ((mTimerLeftCount/1000)/60);
        int secunds = (int) ((mTimerLeftCount/1000)%60);
        String timerFormated = String.format(Locale.getDefault(),"%02d:%02d",minuts,secunds);
        textViewCountDown.setText(timerFormated);
    }

    @Override
    public void initViews() {
        list = new ArrayList<>();
        listTitleVariants = new ArrayList<>();
        variantBtns = new ArrayList<>();
        listTitleVariants.add(findViewById(R.id.title_variantsA));
        listTitleVariants.add(findViewById(R.id.title_variantsB));
        listTitleVariants.add(findViewById(R.id.title_variantsC));
        listTitleVariants.add(findViewById(R.id.title_variantsD));
        variantBtns.add(findViewById(R.id.variantBtnA));
        variantBtns.add(findViewById(R.id.variantBtnB));
        variantBtns.add(findViewById(R.id.variantBtnC));
        variantBtns.add(findViewById(R.id.variantBtnD));
        list.add(findViewById(R.id.cardViewA));
        list.add(findViewById(R.id.cardViewB));
        list.add(findViewById(R.id.cardViewC));
        list.add(findViewById(R.id.cardViewD));
        onCardClicked();
        startTimer();
        btnFinishMain = findViewById(R.id.btn_Finish);
        textViewCountDown = findViewById(R.id.textView_count_down);
        idQuestion = findViewById(R.id.idQuestion);
        btnNext = findViewById(R.id.btn_next);
        idLevel = findViewById(R.id.idLevel);
        btnNext.setOnClickListener(this::onClick);
        btnFinishMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void onCardClicked() {
        for (int i = 0; i < list.size(); i++) {
            CardView view = list.get(i);
            view.setTag(i);
            view.setOnClickListener(v -> {
                view.setCardBackgroundColor(Color.parseColor("#8B52D1"));
                int tag = (Integer) view.getTag();
                variantBtns.get(tag).setTextColor(Color.WHITE);
                presenter.selectAnswer(tag);
                listTitleVariants.get(tag).setTextColor(Color.WHITE);
            });
        }
    }

    @Override
    public void loadQuestions(TestData testData) {
        idQuestion.setText(testData.getQuestion());
        variantBtns.get(0).setText(testData.getVariantA());
        variantBtns.get(1).setText(testData.getVarinatB());
        variantBtns.get(2).setText(testData.getVarinatC());
        variantBtns.get(3).setText(testData.getVarinatD());
    }

    @Override
    public void clearCheck(int position) {
        list.get(position).setCardBackgroundColor(Color.WHITE);
        variantBtns.get(position).setTextColor(Color.BLACK);
        listTitleVariants.get(position).setTextColor(Color.BLACK);
    }

    @Override
    public void result(int currentAnswersCount) {
        showResult(currentAnswersCount);
    }

    @Override
    public void changeState(int currentAnswerIndex, int totalQuestionsCount) {
        idLevel.setText(currentAnswerIndex + " of " + totalQuestionsCount);
    }

    @Override
    public void nextButtonState(boolean state) {
        btnNext.setEnabled(state);
    }

    @Override
    public void finishGame() {
        finish();
    }


    private void onClick(View view) {
        presenter.nextQuestion(false);
    }
}