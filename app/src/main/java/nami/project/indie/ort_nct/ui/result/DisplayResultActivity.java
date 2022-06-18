package nami.project.indie.ort_nct.ui.result;


import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.github.jinatonic.confetti.CommonConfetti;

import nami.project.indie.ort_nct.R;
import nami.project.indie.ort_nct.ui.main.test.TestActivity;


public class DisplayResultActivity extends AppCompatActivity implements View.OnClickListener {

    private long score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result_layout);

        try {
            initViews();
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }

    private void initViews() {
        TextView commentText = findViewById(R.id.comment_textview);
        TextView resultText = findViewById(R.id.result_textview);
        ImageView smileImage = findViewById(R.id.lamp_smile);
        Button okay = findViewById(R.id.click_okay);
        okay.setOnClickListener(this);
        int correct = getIntent().getIntExtra(TestActivity.CORRECT_ANSWER_COUNT, 0);
        int total = getIntent().getIntExtra(TestActivity.TOTAL_QUESTIONS_COUNT, 0);
        resultText.setText(getIntent().getStringExtra(TestActivity.RESULT));

        resultText.setText(String.valueOf(correct).concat("/").concat(String.valueOf(total)));

        if (correct <0.7 * total) {
            smileImage.setImageResource(R.drawable.anime_girl_turning_back);
            commentText.setText(getResources().getString(R.string.try_again_text));

            MediaPlayer ring= MediaPlayer.create(getApplicationContext(), R.raw.failure);
            ring.start();
        } else {
            MediaPlayer ring= MediaPlayer.create(getApplicationContext(), R.raw.success);
            ring.start();

            new Handler()
                    .postDelayed(() -> CommonConfetti.rainingConfetti
                            (
                                    findViewById(R.id.displayResultLayout),
                                    new int[] { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE }
                            ).oneShot(), 100);
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
