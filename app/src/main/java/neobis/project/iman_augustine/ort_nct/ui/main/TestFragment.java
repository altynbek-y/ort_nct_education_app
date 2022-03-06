package neobis.project.iman_augustine.ort_nct.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import neobis.project.iman_augustine.ort_nct.MyApplication;
import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.TestListAdapter;
import neobis.project.iman_augustine.ort_nct.adapters.VerticalSpaceItemDecoration;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;
import neobis.project.iman_augustine.ort_nct.ui.main.test.TestActivity;

public class TestFragment extends Fragment implements TestListAdapter.OnItemListener, Contract {//, SwipeRefreshLayout.OnRefreshListener {
    //------------------------------VIEW=INITIALIZATION---------------------------------------------
    private RecyclerView recyclerView;
    private TextView textView;
    private ProgressBar progressBar;
    private TestListAdapter testListAdapter;
    private TestViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SharedPreferences sharedPreferences;
    private String locale;

    public TestFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //-----------------------------INITIALIZATION-----------------------------------------------
        try {
            sharedPreferences = PreferenceManager.getMySharedPreferences(getContext());
            locale = sharedPreferences.getString("locale", "ru");
            textView = view.findViewById(R.id.emptyTextView);
            swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
            progressBar = view.findViewById(R.id.progressBar);
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    viewModel.getListOfSubjects(locale);
                }
            });
            recyclerView = view.findViewById(R.id.subject_test_list);
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VerticalSpaceItemDecoration.VERTICAL_ITEM_SPACE));

            //viewModel = ViewModelProviders.of(this).get(NctTestViewModel.class);
            viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(TestViewModel.class);

            //Toast.makeText(getContext(), ""+viewModel.getListOfSubjects(locale).get.size(), Toast.LENGTH_LONG).show();

            testListAdapter = new TestListAdapter(new ArrayList<Subject>(), this, getContext());
            viewModel.getDataListOfSubjects().observe(this, new Observer<List<Subject>>() {
                @Override
                public void onChanged(@Nullable List<Subject> testDataList) {
                    testListAdapter.setValues(testDataList);
                    swipeRefreshLayout.setRefreshing(false);

                    if(testDataList !=null) {
                        textView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        return;
                    }
                    textView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            });
            recyclerView.setAdapter(testListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        } catch(NullPointerException error) {
            error.printStackTrace();
        }
    }
    //------------------------------------------ON-ITEM-CLICK---------------------------------------
    @Override
    public void onItemClick(int i) {
        progressBar.setVisibility(View.VISIBLE);
        //viewModel.getNctSubjectTestFor(locale, dataTestList.get(i).getId(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startTest(boolean isNotEmpty) {
        if(isNotEmpty) {
//            Intent intent = new Intent(getActivity(), TestActivity.class);
//            intent.putExtra(TestActivity.TEST, viewModel.getNctSubjectTestList().getValue());
//            startActivity(intent);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), R.string.practice_ort_test_list_empty_text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void toastNoConnection() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.no_connection_toast, Toast.LENGTH_LONG).show();
    }
}
