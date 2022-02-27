package neobis.project.iman_augustine.ort_nct.ui.main.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.TestAdapter;
import neobis.project.iman_augustine.ort_nct.model.test_model.SubjectTest;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;
import neobis.project.iman_augustine.ort_nct.ui.Contract;
import neobis.project.iman_augustine.ort_nct.ui.result.DisplayResultActivity;

public class TestActivity extends AppCompatActivity implements TestAdapter.OnItemListener, Contract.TestResultContract {

    //----------------------------NAME-CONSTANTS----------------------------------------------------
    public final static String TEST = "test";
    public final static String TEST_TYPE = "test_type";
    public final static String CORRECT_ANSWER_COUNT = "correct_answer_count";
    public final static String TOTAL_QUESTIONS_COUNT = "total_answer_count";
    public final static String RESULT = "result";
    public final static String TITLE = "title";
    //----------------------------------------------------------------------------------------------
    private ProgressBar progressBar;        // Progress bar
    private TextView ratio;                 // Text view
    private TextView timerTextView;
    private SubjectTest subjectTest;  // List of questions (Question object)
    private TestController testController;
    private TestActivityViewModel viewModel;
    //------------------------------------VARIABLES-------------------------------------------------
    private final long duration = 3600000;
    private List<Boolean> isAnsweredList;
    //-------------------------------------------COUNT-DOWN-TIMER-----------------------------------
    CountDownTimer countDownTimer = new CountDownTimer(duration, 1000) {
        @Override
        public void onTick(long millis) {
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView.setText(String.format("%d:%02d", minutes, seconds)); // Setting timer at every tick
        }

        @Override
        public void onFinish() { // Proceed or finish on time out
            testController.countTotalCorrect();
            completeTest();
        }
    };
    // Cancels timer when activity destroyed
    public void cancelTimer() {
        if(countDownTimer!=null) {
            countDownTimer.cancel();
        }
    }
    //--------------------------------------CLICK-LISTENERS-----------------------------------------
    //--------------------------ON-TOOLBAR-ICON-CLICK-LISTENER--------------------------------------
    View.OnClickListener onToolbarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showAlertDialog(); // Alerts a dialog to prevent user from accidentally exiting from test
        }
    };

    //--------------------------ON-BUTTON-CLICK-LISTENER--------------------------------------------
    View.OnClickListener onFinishClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            testController.countTotalCorrect();
            completeTest();
        }
    };

    //------------------------------------DRIVER-OF-THE-PROGRAM-------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        //shared = SharedPreferencesSingleton.getLocalSharedPreferences(this);
        try {
            subjectTest = (SubjectTest) getIntent().getSerializableExtra(TEST);
            viewModel = ViewModelProviders.of(this).get(TestActivityViewModel.class);

            initViews();                                                                                    // Initializing widgets
            initRecyclerView();                                                                             // Initializing recycler view
            isAnsweredList = new ArrayList<>(Collections.nCopies(subjectTest.getQuestions().size(), false));
            testController = new TestController(subjectTest.getQuestions().size(), progressBar, ratio);     // Initialzing test controller object
            countDownTimer.start();                                                                         // Start count down timer
        } catch (NullPointerException error) {
            Toast.makeText(this, "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //--------------------------------------------------------ON-ANSWER-CLICK-LISTENER--------------
    @Override
    public void onAnswerClick(int position, int userAnswer, RadioGroup answerGroup) {
        if(isAnsweredList.get(position))
            return;
        isAnsweredList.set(position, true);
        testController.countAnswer(position, userAnswer, subjectTest.getQuestions().get(position).getAnswers(), answerGroup);
    }
    //---------------------------------------VIEW-INITIALIZATION-----------------------------------------
    private void initViews() {
        //----------------------------WIDGETS-------------------------------------
        // Toolbar view
        Toolbar toolbar = findViewById(R.id.toolbar); // Finding tool bar
        setSupportActionBar(toolbar); // Setting tool bar
        TextView titleTextView = findViewById(R.id.titleText); // Title
        titleTextView.setText(subjectTest.getSubjectName());
        timerTextView = findViewById(R.id.timerTextView);
        //-----------------------------------------------------------------------------------------------
        progressBar = findViewById(R.id.progressBar); // Setting progress bar widget
        ratio = findViewById(R.id.score_ratio_textview); // Setting text view widget
        // Press to complete the test
        Button finishBtn = findViewById(R.id.finish_button); // Setting a button
        finishBtn.setOnClickListener(onFinishClickListener); // Setting on click listener
        toolbar.setNavigationOnClickListener(onToolbarClickListener); // Setting on tool bar click listener
    }
    //--------------------------INITIALIZATION-OF-RECYCLERVIEW-------------------------------------------
    // Fetches data to inflate test list with questions, answers, etc
    private void initRecyclerView() {
        // List view
        RecyclerView recyclerView = findViewById(R.id.quiz_list); // Finding recycler view widget
        // Adapter for the recyclerview
        TestAdapter testAdapter = new TestAdapter(subjectTest.getQuestions(), this, this); // RecyclewView Adapter
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(testAdapter); // Setting adapter
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    @Override
    public void onItemClick(int i) { }

    //-----------------------------COMPLETING-TEST--------------------------------------------------
    public void completeTest() {
        Intent intent = new Intent(TestActivity.this, DisplayResultActivity.class);
        intent.putExtra(TestActivity.RESULT, testController.toStringScore());
        intent.putExtra(TestActivity.CORRECT_ANSWER_COUNT, testController.getCorrectCount());
        intent.putExtra(TestActivity.TOTAL_QUESTIONS_COUNT, testController.getTotal());
        viewModel.insertTestResult(
                                        subjectTest.getSubjectName(),
                                        subjectTest.getVariant(),
                                        testController.getCorrectCount(),
                                testController.getTotal()-testController.getCorrectCount()
        );
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    //---------------------------------------ALERT=DIALOG------------------------------------------------
    public void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.exit_from_test_title));
        alert.setMessage(getResources().getString(R.string.exit_from_test_message));
        alert.setPositiveButton(getResources().getString(R.string.yes_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        alert.setNegativeButton(getResources().getString(R.string.no_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.create().show();
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}



