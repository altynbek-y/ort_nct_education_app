package neobis.project.iman_augustine.ort_nct.ui.result;


import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.particles.ParticleSystem;


import com.github.jinatonic.confetti.CommonConfetti;

import java.util.List;
import java.util.Random;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.ui.main.test.TestActivity;


public class DisplayResultActivity extends AppCompatActivity implements View.OnClickListener {

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
        TextView commentText = findViewById(R.id.comment_textview);
        TextView resultText = findViewById(R.id.result_textview);
        ImageView smileImage = findViewById(R.id.lamp_smile);
        Button okay = findViewById(R.id.click_okay);
        okay.setOnClickListener(this);
        int correct = getIntent().getIntExtra(TestActivity.CORRECT_ANSWER_COUNT, 0); // !!!! PROBLEMATIC
        int total = getIntent().getIntExtra(TestActivity.TOTAL_QUESTIONS_COUNT, 0); // !!!! PROBLEMATIC
        resultText.setText(getIntent().getStringExtra(TestActivity.RESULT));

        if (correct ==0 || correct <0.8* total) {
            smileImage.setImageResource(R.drawable.ic_bad_result);
            commentText.setText(getResources().getString(R.string.try_again_text));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        onClick(null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        onClick(null);
    }

    @Override
    public void onClick(View view) {
        CommonConfetti.rainingConfetti(findViewById(R.id.displayResultLayout), new int[] { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE })
                .oneShot();
               // .stream(2000);
        // finish();
    }
}
