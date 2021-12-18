package neobis.project.iman_augustine.ort_nct.ui.result;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.ui.ort.test.TestActivity;


public class DisplayResultActivity extends AppCompatActivity implements View.OnClickListener {
    private Button okay;
    private ImageView smileImage;
    private TextView resultText, comment;
    private int correct;
    private int total;
    private long score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_result_layout);

        try {
            initViews();
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }

    private void initViews() {
        comment = findViewById(R.id.comment_textview);
        resultText = findViewById(R.id.result_textview);
        smileImage = findViewById(R.id.lamp_smile);
        okay = findViewById(R.id.click_okay);
        okay.setOnClickListener(this);
        correct = getIntent().getIntExtra(TestActivity.CORRECT_ANSWER_COUNT, 0); // !!!! PROBLEMATIC
        total = getIntent().getIntExtra(TestActivity.TOTAL_QUESTIONS_COUNT, 0); // !!!! PROBLEMATIC
        resultText.setText(getIntent().getStringExtra(TestActivity.RESULT));

        if (correct==0 || correct<0.8*total) {
            smileImage.setImageResource(R.drawable.ic_bad_result);
            comment.setText(getResources().getString(R.string.try_again_text));
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
