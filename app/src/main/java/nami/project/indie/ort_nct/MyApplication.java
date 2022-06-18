package nami.project.indie.ort_nct;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.yariksoffice.lingver.Lingver;

import java.util.Locale;

import nami.project.indie.ort_nct.sharedpreference.PreferenceManager;


public class MyApplication extends Application {
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        sharedPreferences = PreferenceManager.getMySharedPreferences(this.getApplicationContext());
        String currentLanguage = sharedPreferences.getString("locale", "ru");
        Lingver.init(this, currentLanguage);
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        sharedPreferences = PreferenceManager.getMySharedPreferences(this.getApplicationContext());
        String currentLanguage = sharedPreferences.getString("locale", "ru");
        // Create a new Locale object
        Locale locale = new Locale(currentLanguage);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        super.onConfigurationChanged(newConfig);
    }
}
