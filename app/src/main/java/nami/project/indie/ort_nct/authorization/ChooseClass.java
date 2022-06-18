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
import androidx.appcompat.widget.Toolbar;

import nami.project.indie.ort_nct.R;
import nami.project.indie.ort_nct.sharedpreference.SharedPreferencesSingleton;
import nami.project.indie.ort_nct.singleclicklistener.OnSingleClickListener;


public class ChooseClass extends AppCompatActivity implements Contract.ClassView {
    private final String CHOOSE_CLASS = "ChooseClass";
    private Button nextButton;
    private RadioGroup gradeRadioGroup;
    private RadioButton radioButton;
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
            String gradeStr = radioButton.getText().toString().substring(0, 2).trim();
            long grade = Long.parseLong(gradeStr);
            SharedPreferencesSingleton.getLocalSharedPreferences(ChooseClass.this).addGrade(grade);
        }
    };
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_choose_class_layout);
        getSupportActionBar().setTitle(R.string.class_choice_label);
        // Initialization of views
        initViews();
    }

    public void initViews() {
        nextButton = findViewById(R.id.click_next);
        nextButton.setOnClickListener(onSingleClickListener);
        gradeRadioGroup = findViewById(R.id.class_group);
        gradeRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void proceed() {
        int selectedId = gradeRadioGroup.getCheckedRadioButtonId();
        if (selectedId > 0) {
            nextButton.setEnabled(false);
            startActivity(new Intent(this, ChooseCity.class));
            finish();
        } else {
            Toast.makeText(this, R.string.warning_message_class, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}




