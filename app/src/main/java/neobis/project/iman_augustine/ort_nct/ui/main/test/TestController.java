package neobis.project.iman_augustine.ort_nct.ui.main.test;

import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.test_model.Answer;

public class TestController implements Serializable {
    private final double maxOrtFinalScore = 245.0;      // For ORT (might be changed)
    private final double maxOrtAdditionalScore = 150;   // For ORT (might be changed)

    private int correctAnswer = 0;                      // Total of user's correct answers
    private int countTotalAnswer;                       // Total of answered answers (both correct and incorrect)
    private final int total;
    private final ProgressBar progressBar;              // Progress bar widget
    private final TextView progressText;                // Displays answered to total ratio

    private final ArrayList<Integer> userAnswerList;
    private final ArrayList<Integer> correctAnswerList;

    public TestController(int total, ProgressBar progressBar, TextView progressText) {
        this.userAnswerList =  new ArrayList<>(Collections.nCopies(total, -1));             // User answer list
        this.correctAnswerList = new ArrayList<>(Collections.nCopies(total, -2));           // Correct answers
        this.total = total;
        this.progressBar = progressBar;
        this.progressBar.setMax(total);
        this.progressText = progressText;
        progressText.setText(String.valueOf(this.countTotalAnswer).concat("/").concat(String.valueOf(this.total)));                         // Setting ratio of answered questions to total of questions
    }

    public void countAnswer(int position, int userAnswer, List<Answer> options, RadioGroup answerGroup) {
        if(correctAnswerList.get(position) == -2 )
        {
            for (int i = 0; i < 4; i++)
            {
                if (options.get(i).isCorrect())
                {
                    ((RadioButton)answerGroup.getChildAt(i)).setButtonDrawable(R.drawable.ic_correct_icon);
                    correctAnswerList.set(position, i);
                    break;
                }
            }
        }

        // explanationCard.setVisibility(View.VISIBLE);
        if(userAnswerList.get(position) == -1) this.countTotalAnswer++; // Total user has answered

        userAnswerList.set(position, userAnswer); // Setting user info
        progressBar.setProgress(this.countTotalAnswer);// Setting progress
        progressText.setText(this.countTotalAnswer + "/" + this.total); // Setting ratio of answered questions to total of questions
    }

    public int getCorrectCount() {
        return this.correctAnswer;
    }

    public int getTotal()
    {
        return this.total;
    }

    public String toStringScore()
    {
        return this.correctAnswer+"/"+this.total;
    }

    public void countTotalCorrect()
    {
        for(int i=0; i<total; i++)
        {
            if(userAnswerList.get(i).equals(correctAnswerList.get(i)))
                this.correctAnswer++;
        }
    }
}
