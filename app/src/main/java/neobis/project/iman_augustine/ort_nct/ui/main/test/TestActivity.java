package neobis.project.iman_augustine.ort_nct.ui.main.test;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.QuestionListAdapter;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionWithAnswers;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;
import neobis.project.iman_augustine.ort_nct.ui.Contract;
import neobis.project.iman_augustine.ort_nct.ui.result.DisplayResultActivity;

public class TestActivity extends AppCompatActivity implements QuestionListAdapter.OnItemListener,
        Contract.TestResultContract {

    public final static String SUBJECT_NAME = "subject_name";
    public final static String SUBJECT_ID = "subject_id";
    public final static String TEST_TYPE = "test_type";
    public final static String CORRECT_ANSWER_COUNT = "correct_answer_count";
    public final static String TOTAL_QUESTIONS_COUNT = "total_answer_count";
    public final static String RESULT = "result";
    public final static String TITLE = "title";

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView timerTextView;
    private TestActivityViewModel viewModel;

    private final long duration = 3600000;
    private List<Boolean> isAnsweredList;
    private String subjectName;
    private int subjectId;
    private int correctAnswer = 0;
    private int countTotalAnswer = 0;
    private int total;

    CountDownTimer countDownTimer = new CountDownTimer(duration, 1000)
    {
        @Override
        public void onTick(long millis)
        {
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
        }

        @Override
        public void onFinish()
        {
            completeTest();
        }
    };

    public void cancelTimer()
    {
        if(countDownTimer!=null) {
            countDownTimer.cancel();
        }
    }

    View.OnClickListener onToolbarClickListener = view -> {
            showAlertDialog();                                                                                      // Alert a dialog
    };

    View.OnClickListener onFinishClickListener = new OnSingleClickListener()
    {
        @Override
        public void onSingleClick(View view) {
            completeTest();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);

        try {
            subjectId = (int) getIntent().getSerializableExtra(SUBJECT_ID);
            subjectName = (String) getIntent().getSerializableExtra(SUBJECT_NAME);

            viewModel = ViewModelProviders.of(this).get(TestActivityViewModel.class);
            viewModel.getListOfQuestionsListForSubject(subjectId);
            viewModel.getListOfQuestionsWithAnswersForSubject(subjectId);

            initViews();

        } catch (NullPointerException error)
        {
            Toast.makeText(this, "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnswerClick(int userAnswer, QuestionWithAnswers answers, RadioGroup answerGroup)
    {
        if(answers.isAnswered())
            return;

        for (int i = 0; i < 4; i++)
            answerGroup.getChildAt(i).setEnabled(false);

        ++countTotalAnswer;

        answers.setAnswered(true);

        ((RadioButton)answerGroup.getChildAt(userAnswer))
                .setButtonDrawable(R.drawable.ic_incorrect_icon);

        if(
                (answers.isA_is_correct() && userAnswer==0) || (answers.isB_is_correct() && userAnswer==1) ||
                (answers.isC_is_correct() && userAnswer==2) || (answers.isD_is_correct() && userAnswer==3)
        )
            ++correctAnswer;

        int correctChoice = -1;

        if(answers.isA_is_correct())
            correctChoice = 0;
        else if(answers.isB_is_correct())
            correctChoice = 1;
        else if(answers.isC_is_correct())
            correctChoice = 2;
        else if(answers.isD_is_correct())
            correctChoice = 3;

        ((RadioButton)answerGroup.getChildAt(correctChoice))
                .setButtonDrawable(R.drawable.ic_correct_icon);

        progressBar.setProgress(countTotalAnswer);
        progressText.setText(String.valueOf(countTotalAnswer).concat("/")
                .concat(String.valueOf(total)));
    }

    private void initViews()
    {
        // Toolbar view
        Toolbar toolbar = findViewById(R.id.toolbar);                                                           // Finding tool bar
        setSupportActionBar(toolbar);                                                                           // Setting tool bar

        TextView titleTextView = findViewById(R.id.titleText);                                                  // Title
        titleTextView.setText(subjectName);

        progressText = findViewById(R.id.progress_textview);

        timerTextView = findViewById(R.id.timerTextView);

        progressBar = findViewById(R.id.progressBar);                                                           // Setting progress bar widget

        Button finishBtn = findViewById(R.id.finish_button);                                                    // Setting a button
        finishBtn.setOnClickListener(onFinishClickListener);                                                    // Setting on click listener
        toolbar.setNavigationOnClickListener(onToolbarClickListener);                                           // Setting on tool bar click listener

        RecyclerView recyclerView = findViewById(R.id.quiz_list);                                               // Finding recycler view widget
        QuestionListAdapter questionsAdapter = new QuestionListAdapter(new ArrayList<>(),
                this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        viewModel.getDataListOfQuestionsWithAnswers().observe(this, questionWithAnswers -> {
            total = questionWithAnswers.size();
            if(total==0) {
                Toast.makeText(this, getString(R.string.sorry_for_inconvience_absent_test), Toast.LENGTH_LONG).show();
                finish();
            }
            progressBar.setMax(total);
            progressText.setText(String.valueOf(0).concat("/")
                    .concat(String.valueOf(total)));
            questionsAdapter.setValues(questionWithAnswers);
            countDownTimer.start();
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(questionsAdapter);
    }

    @Override
    public void onBackPressed()
    {
        showAlertDialog();
    }

    @Override
    public void onItemClick(int i) { }

    public void completeTest()
    {
        Intent intent = new Intent(TestActivity.this, DisplayResultActivity.class);
        intent.putExtra(TestActivity.RESULT, 0);
        intent.putExtra(TestActivity.CORRECT_ANSWER_COUNT, correctAnswer);
        intent.putExtra(TestActivity.TOTAL_QUESTIONS_COUNT, total);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
       /* String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        viewModel.insertTestResult(subjectId, (double)(correctAnswer/total), date);*/
        finish();
    }

    public void showAlertDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.exit_from_test_title));
        alert.setMessage(getResources().getString(R.string.exit_from_test_message));
        alert.setPositiveButton(getResources().getString(R.string.yes_exit), (dialogInterface, i) -> {
            dialogInterface.dismiss();
            finish();
        });
        alert.setNegativeButton(getResources().getString(R.string.no_exit), (dialogInterface, i) -> dialogInterface.dismiss());
        alert.create().show();
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cancelTimer();
    }
}