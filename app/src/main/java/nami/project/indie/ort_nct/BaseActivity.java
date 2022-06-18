package nami.project.indie.ort_nct;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import nami.project.indie.ort_nct.sharedpreference.SharedPreferencesSingleton;
import nami.project.indie.ort_nct.ui.appintro.CustomAppIntro;
import nami.project.indie.ort_nct.ui.main.MainActivity;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        new Handler().postDelayed(this::startMainActivity, 400);
    }

    public void startMainActivity() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean isFirstLaunch = sharedPreferences.getBoolean(SharedPreferencesSingleton.FIRST_LAUNCH, true);

        if(isFirstLaunch) {
            startActivity(new Intent(this, CustomAppIntro.class));
            sharedPreferences.edit().putBoolean(SharedPreferencesSingleton.FIRST_LAUNCH, false)
                .apply();
        } else
            startActivity(new Intent(this, MainActivity.class));

        finish();
    }

   /* public void chooseTestType() {
        startActivity(new Intent(this, ChooseTestType.class));
        finish();
    }*/
}
