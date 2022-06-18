package nami.project.indie.ort_nct.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import nami.project.indie.ort_nct.R;
import nami.project.indie.ort_nct.singleclicklistener.OnSingleClickListener;
import nami.project.indie.ort_nct.ui.settings.SettingsActivity;

/**
 * Choose test type layout
 * Created by admin on 03/02/20
 */

public class ChooseTestType extends AppCompatActivity implements Contract.ChooseTestTypeView {
    private final String CHOOSE_TEST_TYPE = "ChooseTestType";
    private Toolbar toolbar;
    private Button ortButton, nctButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_choose_test_type_layout);

        Log.i(CHOOSE_TEST_TYPE, "Launched ChooseTestType activity...");
        // The main code goes here

        initViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ortButton = findViewById(R.id.to_ort);
        nctButton = findViewById(R.id.to_nct);

        ortButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toOrt();
            }
        });

        nctButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                toNct();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_single_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void toOrt() {
        //startActivity(new Intent(this, OrtMainActivity.class));
    }

    @Override
    public void toNct() {
        //startActivity(new Intent(this, NctMainActivity.class));
    }

    /**
     * Prevents from being redirected to the previous activity
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
