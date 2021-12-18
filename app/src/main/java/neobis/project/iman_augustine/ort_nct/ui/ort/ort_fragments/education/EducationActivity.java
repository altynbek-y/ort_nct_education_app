package  neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.education;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import neobis.project.iman_augustine.ort_nct.R;


public class EducationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView contentWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_ort_layout);
        // initialise views
        initViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(getIntent().getStringExtra(OrtEducationFragment.SUBJECT_NAME));
        contentWebView = findViewById(R.id.webView);
        contentWebView.loadData(getIntent().getStringExtra(OrtEducationFragment.CONTENT), "text/html", "UTF-8");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
