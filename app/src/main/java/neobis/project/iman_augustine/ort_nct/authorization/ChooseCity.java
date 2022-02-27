package neobis.project.iman_augustine.ort_nct.authorization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import neobis.project.iman_augustine.ort_nct.common.CommonMethod;
import neobis.project.iman_augustine.ort_nct.R;
import  neobis.project.iman_augustine.ort_nct.dialogs.LoadingDialog;
import  neobis.project.iman_augustine.ort_nct.sharedpreference.SharedPreferencesSingleton;
import  neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;
import neobis.project.iman_augustine.ort_nct.ui.main.MainActivity;

/**
 * Choose city layout
 * Created by admin on 03/02/20
 */

public class ChooseCity extends AppCompatActivity implements Contract.RegionView {
    private RadioGroup cityRadioGroup;
    private RadioButton radioButton;
    private Button finishButton;
    private SharedPreferencesSingleton sps;
    private LoadingDialog loadingDialog;
    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            proceed();
        }
    };
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioButton = findViewById(checkedId);
            SharedPreferencesSingleton.getLocalSharedPreferences(ChooseCity.this).addRegion(radioButton.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.choose_city_layout);
        try {
            sps = new SharedPreferencesSingleton(getApplicationContext()); // SharedPreferences singleton
            getSupportActionBar().setTitle(R.string.city_choice_label);
            // Initialization of views
            initViews();
        } catch(NullPointerException error) {
            error.printStackTrace();
        }
    }

    public void initViews() {
        finishButton = findViewById(R.id.click_ready);
        finishButton.setOnClickListener(onSingleClickListener);
        cityRadioGroup = findViewById(R.id.city_group);
        cityRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void proceed() {
        int selectedId = cityRadioGroup.getCheckedRadioButtonId();
        if (selectedId > 0) {
            showProgressBar();
            radioButton = findViewById(selectedId);
            // appViewModel.createUser(CommonMethod.getDeviceId(this), sps.getAge(), sps.getGrade(), sps.getRegion(), this);
        } else {
            Toast.makeText(this, R.string.warning_message_city, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finishSetUp() {
        hideProgressBar();
        sps.setUserCode(CommonMethod.getDeviceId(this));
        sps.destroyUserData();
        Intent intent = new Intent(this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.try_once_more_toast, Toast.LENGTH_LONG).show();
    }
    @Override
    public void toastMessage(int res_id) {
        Toast.makeText(this, res_id, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void disableButton() {
        finishButton.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
    }

    @Override
    public void hideProgressBar() {
        loadingDialog.dismissDialog();
    }
}
