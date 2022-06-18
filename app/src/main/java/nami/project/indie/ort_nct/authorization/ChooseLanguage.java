package  nami.project.indie.ort_nct.authorization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import nami.project.indie.ort_nct.R;
import nami.project.indie.ort_nct.common.CommonMethodConnectivity;
import nami.project.indie.ort_nct.sharedpreference.SharedPreferencesSingleton;
import nami.project.indie.ort_nct.singleclicklistener.OnSingleClickListener;
import nami.project.indie.ort_nct.ui.error.RefreshInternetActivity;

/**
 * Choose language layout
 * Created by admin on 1/27/20
 * Uses MVP architecture
 */

public class ChooseLanguage extends AppCompatActivity implements Contract.LanguageView {
    private final String CHOOSE_LANGUAGE = "ChooseLanguage";

    // LanguagePresenter languagePresenter = new LanguagePresenter(this);
    private Button kyrgyz;
    private Button russian;
    private SharedPreferencesSingleton sps;
    // On click listener for choose russian button
    private OnSingleClickListener onRussianClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            russian.setEnabled(false);
            if (CommonMethodConnectivity.isNetworkAvailable(getApplicationContext())) {
                setToRussian();
            } else {
                startRefreshNetActivity();
            }
        }
    };
    // On click listener for choose kyrgyz button
    private OnSingleClickListener onKyrgyzClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            kyrgyz.setEnabled(false);
            if (CommonMethodConnectivity.isNetworkAvailable(getApplicationContext())) {
                setToKyrgyz();
            } else {
                startRefreshNetActivity();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choose_layout);

        sps = SharedPreferencesSingleton.getLocalSharedPreferences(this);
        Log.i(CHOOSE_LANGUAGE, "Launched ChooseLanguage activity...");
        /*
         * The main code goes here
         * */
        initViews();
    }

    /*
     * Initializing button widgets and setting click listeners
     */
    private void initViews() {
        kyrgyz = findViewById(R.id.to_kyrgyz);
        russian = findViewById(R.id.to_russian);
        kyrgyz.setOnClickListener(onKyrgyzClickListener);
        russian.setOnClickListener(onRussianClickListener);
    }
    /*
     * Sets Russian as a default language
     * */
    @Override
    public void setToRussian() {
      //  LocaleHelper.setLocale(this, LocaleHelper.RUSSIAN_LANGUAGE);
        startActivity(new Intent(this, ChooseAge.class));
        finish();
    }
    /*
     * Sets Kyrgyz as a default language
     * */
    @Override
    public void setToKyrgyz() {
      //  LocaleHelper.setLocale(this, LocaleHelper.KYRGYZ_LANGUAGE);
        startActivity(new Intent(this, ChooseAge.class));
        finish();
    }
    /*
     * Proceeds to RefreshInternet activity
     * */
    @Override
    public void startRefreshNetActivity() {
        Log.i(CHOOSE_LANGUAGE, "Launching RefreshInternetActivity activity...");
        startActivity(new Intent(this, RefreshInternetActivity.class));
        finish();
    }
    /*
     * Check if connection is available, otherwise program will terminate
     * */

    /*
     *  Prevents from being redirected to the previous activity
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
