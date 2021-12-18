package neobis.project.iman_augustine.ort_nct;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import neobis.project.iman_augustine.ort_nct.ui.nct.main.NctMainActivity;
import neobis.project.iman_augustine.ort_nct.ui.ort.main.OrtMainActivity;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNctTestActivity();
            }
        }, 400);
        // launchMainActivity();
        // startNctTestActivity();
    }

    public void startOrtTestActivity() {
        startActivity(new Intent(this, OrtMainActivity.class));
        finish();
    }

    public void startNctTestActivity() {
        startActivity(new Intent(this, NctMainActivity.class));
        finish();
    }

//    public void startAuthorization() {
//        SharedPreferencesSingleton.getLocalSharedPreferences(this).setRbIDsNone();
//        startActivity(new Intent(this, ChooseLanguage.class));
//        finish();
//    }
//
//    public void startRefreshInternetActivity() {
//        startActivity(new Intent(this, RefreshInternetActivity.class));
//        overridePendingTransition(0, 0);
//        finish();
//    }
//
//    public void startFailureActivity() {
//        startActivity(new Intent(this, FailureActivity.class));
//        overridePendingTransition(0, 0);
//        finish();
//    }
//
//    public void launchMainActivity() {
//        if (SharedPreferencesSingleton.getLocalSharedPreferences(this).isOrtTestLaunchNextTime()) {
//            startOrtTestActivity();
//        } else {
//            startNctTestActivity();
//        }
//    }
//
//    public void showError() {
//        Toast.makeText(this, "Попробуйте ещё раз!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void recreateBaseActivity() {
//        finish();
//        startActivity(getIntent());
//        overridePendingTransition(0, 0);
//    }
//
//    public void saveUserId() {
//        SharedPreferencesSingleton.getLocalSharedPreferences(this).setUserCode(CommonMethod.getDeviceId(this));
//    }
//
//    public void logMessage(String msg) {
//        Toast.makeText(this, "msg: " + msg, Toast.LENGTH_SHORT).show();
//    }
}
