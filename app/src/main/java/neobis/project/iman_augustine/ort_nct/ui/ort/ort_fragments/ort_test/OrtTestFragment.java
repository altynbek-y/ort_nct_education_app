package neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.OrtTestListAdapter;
import neobis.project.iman_augustine.ort_nct.adapters.VerticalSpaceItemDecoration;
import neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;
import neobis.project.iman_augustine.ort_nct.ui.ort.test.*;

/**
 * ORT subject test list fragment
 * */
public class OrtTestFragment extends Fragment implements LifecycleOwner, OrtTestListAdapter.OnItemListener {
    //-------------------------------------------VIEW-INITIALIZATION--------------------------------
    private RecyclerView recyclerView;
    private OrtTestListAdapter subjectOrtListAdapter;
    private TextView emptyTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TestViewModel viewModel;
    private List<SubjectTest> testList;

    public OrtTestFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ort_practice_test, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(false);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //-----------------------------INITIALIZATION-----------------------------------------------
        try {
            swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    viewModel.getNewOrtSubjectTestList();
                }
            });
            recyclerView = view.findViewById(R.id.ort_practice_test_list);
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VerticalSpaceItemDecoration.VERTICAL_ITEM_SPACE));
            emptyTextView = view.findViewById(R.id.textView);
            viewModel = ViewModelProviders.of(this).get(TestViewModel.class);
            subjectOrtListAdapter = new OrtTestListAdapter(new ArrayList<SubjectTest>(), this, getContext());
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            viewModel.getOrtSubjectTestList().observe(this, new Observer<List<SubjectTest>>() {
                @Override
                public void onChanged(@Nullable List<SubjectTest> subjectTestList) {
                    testList = subjectTestList;
                    subjectOrtListAdapter.setValues(subjectTestList);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            recyclerView.setAdapter(subjectOrtListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }
    //------------------------------------------ON-ITEM-CLICK---------------------------------------
    @Override
    public void onItemClick(int i) {
        Intent intent = new Intent(getActivity(), TestActivity.class);
        intent.putExtra(TestActivity.TEST, testList.get(i));
        startActivity(intent);
    }
}
//--------------------------------------------------------------------------------------------------