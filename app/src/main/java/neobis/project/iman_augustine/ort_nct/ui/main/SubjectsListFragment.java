package neobis.project.iman_augustine.ort_nct.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.SubjectListAdapter;
import neobis.project.iman_augustine.ort_nct.adapters.VerticalSpaceItemDecoration;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;
import neobis.project.iman_augustine.ort_nct.ui.main.test.TestActivity;

public class SubjectsListFragment extends Fragment implements SubjectListAdapter.OnItemListener, Contract {    //, SwipeRefreshLayout.OnRefreshListener {
    //------------------------------VIEW=INITIALIZATION---------------------------------------------
    private RecyclerView recyclerView;
    private TextView textView;
    private FrameLayout progressLayout;
    private SubjectListAdapter testListAdapter;
    private TestViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SharedPreferences sharedPreferences;
    private String locale;
    private List<Subject> globalTestDataList;

    public SubjectsListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subjects_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //-----------------------------INITIALIZATION-----------------------------------------------
        try {
            sharedPreferences = PreferenceManager.getMySharedPreferences(getContext());
            // locale = sharedPreferences.getString("locale", "ru");
            textView = view.findViewById(R.id.emptyTextView);
            swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
            progressLayout = view.findViewById(R.id.progressLayout);
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setOnRefreshListener(() -> viewModel.getListOfSubjects(locale));
            recyclerView = view.findViewById(R.id.subject_test_list);
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VerticalSpaceItemDecoration.VERTICAL_ITEM_SPACE));

            viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(TestViewModel.class);

            // Load data into recyclerview with the help of adapter
            testListAdapter = new SubjectListAdapter(new ArrayList<>(), this, getContext());

            viewModel.getDataListOfSubjects().observe(this, testDataList -> {
                testListAdapter.setValues(testDataList);
                swipeRefreshLayout.setRefreshing(false);
                globalTestDataList = testDataList;

                if(testDataList !=null) {
                    textView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    return;
                }

                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            });

            recyclerView.setAdapter(testListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        } catch(NullPointerException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int i)
    {
        progressLayout.setVisibility(View.VISIBLE);
        Subject subject = globalTestDataList.get(i);
        startTestActivity(subject.getId(), subject.getSubjectName());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void startTestActivity(int subject_id, String subject_name)
    {
        Intent intent = new Intent(getActivity(), TestActivity.class);
        intent.putExtra(TestActivity.SUBJECT_NAME, subject_name);
        intent.putExtra(TestActivity.SUBJECT_ID, subject_id);
        startActivity(intent);
    }

    @Override
    public void toastNoConnection() {
        progressLayout.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.no_connection_toast, Toast.LENGTH_LONG).show();
    }
}
