package nami.project.indie.ort_nct.authorization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nami.project.indie.ort_nct.R;
import nami.project.indie.ort_nct.sharedpreference.SharedPreferencesSingleton;
import nami.project.indie.ort_nct.singleclicklistener.OnSingleClickListener;

/**
 * Choose age layout
 * Created by admin on 03/02/20
 */

public class ChooseAge extends AppCompatActivity implements Contract.AgeView {
    private Button nextButton;
    private RadioGroup ageRadioGroup;
    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            proceed();
        }
    };
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton radioButton = findViewById(checkedId);
            String ageStr = radioButton.getText().toString();
            long age = 20;
            if (checkedId != R.id.other_age) {
                age = Long.parseLong(ageStr);
            }
            SharedPreferencesSingleton.getLocalSharedPreferences(ChooseAge.this).addAge(age);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_choose_age_layout);
        getSupportActionBar().setTitle(R.string.age_choice_label);
        // Initialization of views
        initViews();
    }

    public void initViews() {
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(onSingleClickListener);
        ageRadioGroup = findViewById(R.id.age_group);
        ageRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void proceed() {
        int selectedId = ageRadioGroup.getCheckedRadioButtonId();
        if (selectedId > 0) {
            nextButton.setEnabled(false);
            startActivity(new Intent(this, ChooseClass.class));
            finish();
        } else {
            Toast.makeText(this, R.string.warning_message_age, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}



