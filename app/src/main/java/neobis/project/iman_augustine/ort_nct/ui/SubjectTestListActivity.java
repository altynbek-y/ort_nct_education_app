package  neobis.project.iman_augustine.ort_nct.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.OrtTestListAdapter;
import neobis.project.iman_augustine.ort_nct.adapters.OrtTestListAdapter;
import neobis.project.iman_augustine.ort_nct.adapters.VerticalSpaceItemDecoration;
import neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;
import neobis.project.iman_augustine.ort_nct.ui.nct.nct_fragments.nct_test.SelectVariantActivity;
import neobis.project.iman_augustine.ort_nct.ui.ort.test.TestActivity;

public class SubjectTestListActivity extends AppCompatActivity implements OrtTestListAdapter.OnItemListener {
    View.OnClickListener onNavClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView textView;
    private OrtTestListAdapter subjectOrtListAdapter;
    private List<SubjectTest> testList; // Testing
   // private TestActivity.TestType testType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.subject_test_list_layout);
        try {
            // initialise view model
           // testType = (TestActivity.TestType) getIntent().getSerializableExtra(TestActivity.TEST_TYPE);
          //  testList = getTestListByType(testType);
            initViews();
            initRecyclerView();
            if(testList.size()>0) {
                textView.setVisibility(View.GONE);
            }
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }

    Observer<List<SubjectTest>> testUpdateObserver = new Observer<List<SubjectTest>>() {
        @Override
        public void onChanged(@Nullable List<SubjectTest> subjectTestList) {

        }
    };

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.textView);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle(getIntent().getStringExtra(TestActivity.GRADE));
        toolbar.setNavigationOnClickListener(onNavClickListener);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.ort_subject_list);
        subjectOrtListAdapter = new OrtTestListAdapter(testList, this, SubjectTestListActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VerticalSpaceItemDecoration.VERTICAL_ITEM_SPACE));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(subjectOrtListAdapter);
    }

    // On recyclerview's item click launch Test activity
    @Override
    public void onItemClick(int id) {
      /*  TestActivity.TestType testType = (TestActivity.TestType) getIntent().getSerializableExtra(TestActivity.TEST_TYPE);
        Intent intent;
        if(testType!= TestActivity.TestType.ORT_EXTRA) {
            intent = new Intent(this, SelectVariantActivity.class);
            intent.putExtra(TestActivity.TEST_TYPE, testType);
            intent.putExtra(TestActivity.GRADE, getIntent().getStringExtra(TestActivity.GRADE));
        } else {
            intent = new Intent(this, TestActivity.class);
            intent.putExtra(TestActivity.TEST_TYPE, testType);

        intent.putExtra(TestActivity.ID, id);
        startActivity(intent);*/
    }

   /* public List<SubjectTest> getTestListByType(TestActivity.TestType testType) {
        switch(testType) {
          /*  case ORT_PRACTICE: return appViewModel.getOrtSubjectTestFromDatabase(); // ORT practice test
            case ORT_FINAL: return appViewModel.getOrtFinalTestFromDatabase().getValue(); // ORT final test
            case ORT_EXTRA: return appViewModel.getOrtFinalAdditionalTestFromDatabase().getValue(); // ORT extra test
            case NCT_TEST_GRADE_5: return appViewModel.getNctSubjectTests5();// NCT test grade 5
            case NCT_TEST_GRADE_6: return appViewModel.getNctSubjectTests6();// NCT test grade 6
            case NCT_TEST_GRADE_7: return appViewModel.getNctSubjectTests7();// NCT test grade 7
            case NCT_TEST_GRADE_8: return appViewModel.getNctSubjectTests8();// NCT test grade 8
            case NCT_TEST_GRADE_9: return appViewModel.getNctSubjectTests9();// NCT test grade 9
            case NCT_TEST_GRADE_10: return appViewModel.getNctSubjectTests10();// NCT test grade 10
            case NCT_TEST_GRADE_11: return appViewModel.getNctSubjectTests11();// NCT test grade 11
        }
        return null;
    }*/

    @Override
    public void onBackPressed() {
        finish();
    }
}
