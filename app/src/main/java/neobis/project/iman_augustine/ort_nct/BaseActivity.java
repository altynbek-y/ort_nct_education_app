package neobis.project.iman_augustine.ort_nct;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import neobis.project.iman_augustine.ort_nct.ui.ChooseTestType;
import neobis.project.iman_augustine.ort_nct.ui.appintro.CustomAppIntro;
import neobis.project.iman_augustine.ort_nct.ui.main.MainActivity;
import neobis.project.iman_augustine.ort_nct.ui.result.DisplayResultActivity;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 400);
    }

    public void startMainActivity() {
        //startActivity(new Intent(this, DisplayResultActivity.class));
        startActivity(new Intent(this, CustomAppIntro.class));
        // startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void chooseTestType() {
        startActivity(new Intent(this, ChooseTestType.class));
        finish();
    }

}
