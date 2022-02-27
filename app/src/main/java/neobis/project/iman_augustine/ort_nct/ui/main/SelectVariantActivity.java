package neobis.project.iman_augustine.ort_nct.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;

public class SelectVariantActivity extends AppCompatActivity {

    private Button nextButton;
    private RadioGroup gradeRadioGroup;
    private String variantStr = null;

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
           startTesting();
        }
    };
    private RadioButton radioButton;
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
           pickingVariant(checkedId);
        }
    };
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.select_variant_layout);
        // Initialization of views
        try {
            initViews();
        } catch(NullPointerException error) {
            error.printStackTrace();
        }
    }
    public void initViews() {
        try {
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.variant_choice_label);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            // Initialization of views
            nextButton = findViewById(R.id.click_next);
            gradeRadioGroup = findViewById(R.id.variant_group);
            gradeRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
            nextButton.setOnClickListener(onSingleClickListener);
        } catch(NullPointerException error) {
            error.printStackTrace();
        }
    }
    public void pickingVariant(int checkedId) {
        radioButton = findViewById(checkedId);
        variantStr = radioButton.getText().toString().substring(0, 2).trim();
      //  pickedVariant = variantStr.equals("I") ? TestActivity.Variant.VAR1 : TestActivity.Variant.VAR2;
     //   Toast.makeText(SelectVariantActivity.this, "variant: " + pickedVariant.name(), Toast.LENGTH_SHORT).show();
    }
    public void startTesting() {
        Intent intent;
      /*  if(pickedVariant!=null) {
            intent = new Intent(SelectVariantActivity.this, TestActivity.class);
            intent.putExtra(TestActivity.TEST_TYPE, getIntent().getSerializableExtra(TestActivity.TEST_TYPE));
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SelectVariantActivity.this, R.string.warning_message_variant, Toast.LENGTH_LONG).show();
        }*/
    }

}
