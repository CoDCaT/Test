package com.google.developer.bugmaster;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.views.AnswerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity implements AnswerView.OnAnswerSelectedListener implement QuizMvpView {

    //Number of quiz answers
    public static final int ANSWER_COUNT = 5;
    public static final String EXTRA_INSECTS = "insectList";
    public static final String EXTRA_ANSWER = "selectedInsect";
    private QuizActivityPresenter<QuizMvpView> mPresenter;
    
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.text_question) TextView mQuestionText;
    @BindView(R.id.text_correct) TextView mCorrectText;
    @BindView(R.id.answer_select) AnswerView mAnswerSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        attachPresenter();
        
        init();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selected", mAnswerSelect.getCheckedIndex());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAnswerSelect.setCheckedIndex(savedInstanceState.getInt("selected"));
    }

    /* Answer Selection Callbacks */

    @Override
    public void onCorrectAnswerSelected() {
        updateResultText();
    }

    @Override
    public void onWrongAnswerSelected() {
        updateResultText();
    }

    private void updateResultText() {
        mCorrectText.setTextColor(mAnswerSelect.isCorrectAnswerSelected() ? ContextCompat.getColor(this, R.color.colorCorrect) : ContextCompat.getColor(this, R.color.colorWrong));
        mCorrectText.setText(mAnswerSelect.isCorrectAnswerSelected() ? R.string.answer_correct : R.string.answer_wrong);
    }


    private void init() {
        ButterKnife.bind(this);

        setToolbar();
        setAnswerView();
        setQuestion();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setAnswerView() {
        mAnswerSelect.setOnAnswerSelectedListener(this);
    }

    private void setQuestion(){
        mPresenter.onViewInitialized(getIntent());
    }

    private void attachPresenter() {
        mPresenter = new QuizActivityPresenter<>(this);
    }
    
    @Override
    void showQuestion(String question, ArrayList<String> options, String scientificName){
        mQuestionText.setText(question);
        mAnswerSelect.loadAnswers(options, scientificName);
    }

}
