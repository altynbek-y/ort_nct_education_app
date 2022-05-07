package neobis.project.iman_augustine.ort_nct.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.yariksoffice.lingver.Lingver;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;
import neobis.project.iman_augustine.ort_nct.ui.Contract;

public class SettingsActivity extends AppCompatActivity implements Contract.SettingsView {
    RadioGroup.OnCheckedChangeListener onLanguagedCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.switch_to_russian:
                    setToRussian();
                    break;
                default:
                    setToKyrgyz();
            }
            editor.putBoolean("locale_changed", true);
            editor.commit();
            finish();
        }
    };
    private RadioGroup language_option;
    private RadioButton languageRadioButton;
    private Toolbar toolbar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_layout);

        sharedPreferences = PreferenceManager.getMySharedPreferences(this);
        editor = sharedPreferences.edit();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.language_settings);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViews();
    }

    private void initViews() {
        language_option = findViewById(R.id.languageRadioGroup);
        String currentLocale = sharedPreferences.getString("locale", "ru");
        if (currentLocale.equals("ru")) {
            languageRadioButton = findViewById(R.id.switch_to_russian);
        } else {
            languageRadioButton = findViewById(R.id.switch_to_kyrgyz);
        }
        languageRadioButton.setChecked(true);
        language_option.setOnCheckedChangeListener(onLanguagedCheckedListener);
    }
    /*
     * Sets Russian as a default language
     * */
    @Override
    public void setToRussian() {
        setSelectedLanguage("ru");
    }
    /*
     * Sets Kyrgyz as a default language
     * */
    @Override
    public void setToKyrgyz() {
        setSelectedLanguage("ky");
    }

    private void setSelectedLanguage(String language) {
        Lingver.getInstance().setLocale(this, language);
        editor.remove("locale");
        editor.putString("locale", language).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
