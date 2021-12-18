package neobis.project.iman_augustine.ort_nct.ui.nct.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.NctTestAdapter;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.SubjectTestNct;
import neobis.project.iman_augustine.ort_nct.sharedpreference.SharedPreferencesSingleton;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;
import neobis.project.iman_augustine.ort_nct.ui.Contract;
import neobis.project.iman_augustine.ort_nct.ui.result.DisplayResultActivity;
import neobis.project.iman_augustine.ort_nct.ui.nct.test.NctTestController;
import neobis.project.iman_augustine.ort_nct.ui.ort.test.TestActivity;

public class NctTestActivity extends AppCompatActivity implements NctTestAdapter.OnItemListener, Contract.TestResultContract {
    //----------------------------NAME-CONSTANTS--------------------------------
    public final static String TEST = "test";
    public final static String TITLE = "title";
    //----------------------------WIDGETS-------------------------------------
    private Toolbar toolbar; // Toolbar view
    private RecyclerView recyclerView; // List view
    private ProgressBar progressBar; // Progress bar
    private TextView ratio; // Text view
    private TextView timerTextView;
    private TextView titleTextView;
    private Button finishBtn; // Press to complete the test
    private NctTestAdapter testAdapter; // Adapter for the recyclerview
    private SubjectTestNct subjectTestNct; // List of questions (Question object)
    private NctTestController testController;
    private NctTestActivityViewModel viewModel;
    //------------------------------------VARIABLES-------------------------------------------------
    private long duration = 3600000;
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
        setContentView(R.layout.nct_test_layout);

        //shared = SharedPreferencesSingleton.getLocalSharedPreferences(this);
        try {
            subjectTestNct = (SubjectTestNct) getIntent().getSerializableExtra(TEST);
            viewModel = ViewModelProviders.of(this).get(NctTestActivityViewModel.class);

            initViews(); // Initializing widgets
            initRecyclerView(); // Initializing recycler view
            isAnsweredList = new ArrayList<>(Collections.nCopies(subjectTestNct.getQuestions().size(), false));
            testController = new NctTestController(subjectTestNct.getQuestions().size(), progressBar, ratio); // Initialzing test controller object
            // countDownTimer.start();   // Start count down timer
        } catch (NullPointerException error) {
            Toast.makeText(this, "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //-----------------------------------------------------------------------------------------------
    }
    //--------------------------------------------------------ON-ANSWER-CLICK-LISTENER--------------
    @Override
    public void onAnswerClick(int position, int userAnswer, RadioGroup answerGroup) {
        if(isAnsweredList.get(position))
            return;
        isAnsweredList.set(position, true);
        testController.countAnswer(position, userAnswer, subjectTestNct.getQuestions().get(position).getOptions(), answerGroup);
    }
    //---------------------------------------VIEW-INITIALIZATION-----------------------------------------
    private void initViews() {
        toolbar = findViewById(R.id.toolbar); // Finding tool bar
        setSupportActionBar(toolbar); // Setting tool bar
        titleTextView = findViewById(R.id.titleText); // Title
        titleTextView.setText(subjectTestNct.getSubject().getName());
        timerTextView = findViewById(R.id.timerTextView);
        //-----------------------------------------------------------------------------------------------
        progressBar = findViewById(R.id.progressBar); // Setting progress bar widget
        ratio = findViewById(R.id.score_ratio_textview); // Setting text view widget
        finishBtn = findViewById(R.id.finish_button); // Setting a button
        finishBtn.setOnClickListener(onFinishClickListener); // Setting on click listener
        toolbar.setNavigationOnClickListener(onToolbarClickListener); // Setting on tool bar click listener
    }
    //--------------------------INITIALIZATION-OF-RECYCLERVIEW-------------------------------------------
    // Fetches data to inflate test list with questions, answers, etc
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.quiz_list); // Finding recycler view widget
        testAdapter = new NctTestAdapter(subjectTestNct.getQuestions(), this, this); // RecyclewView Adapter
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
        Intent intent = new Intent(NctTestActivity.this, DisplayResultActivity.class);
        intent.putExtra(TestActivity.RESULT, testController.toStringNctScore());
        intent.putExtra(TestActivity.CORRECT_ANSWER_COUNT, testController.getCorrectCount());
        intent.putExtra(TestActivity.TOTAL_QUESTIONS_COUNT, testController.getTotal());
        viewModel.insertNctTestResult(subjectTestNct.getSubject().getName(),
                                      subjectTestNct.getGrade(),
                                      subjectTestNct.getVariant(),
                                      testController.getCorrectCount(),
                             testController.getTotal()-testController.getCorrectCount());
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




