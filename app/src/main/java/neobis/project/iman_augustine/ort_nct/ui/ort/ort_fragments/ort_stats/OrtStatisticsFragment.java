package neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.StatListAdapter;
import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_stats.StatsViewModel;

public class OrtStatisticsFragment extends Fragment implements StatListAdapter.OnItemListener {
    private StatListAdapter testStatListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView textView;
    private StatsViewModel viewModel;
    private List<TestStat> statList;

    public OrtStatisticsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_ort_statistics, container, false);
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
        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    viewModel.getNewStatList();
            }
        });
        textView = view.findViewById(R.id.textView);
        recyclerView = view.findViewById(R.id.statistics_list);
        viewModel = ViewModelProviders.of(this).get(StatsViewModel.class);
        testStatListAdapter = new StatListAdapter(new ArrayList<TestStat>(), this, getContext());
        viewModel.getStatList().observe(this, new Observer<List<TestStat>>() {
            @Override
            public void onChanged(@Nullable List<TestStat> testNctStats) {
                statList = testNctStats;
                testStatListAdapter.setValues(testNctStats);
                swipeRefreshLayout.setRefreshing(false);
                if( statList.size() > 0 ) {
                    recyclerView.setVisibility(View.VISIBLE); return;
                }
                textView.setVisibility(View.VISIBLE);
            }
        });
        recyclerView.setAdapter(testStatListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onRemoveClick(int i) {
        viewModel.removeOrtTestStat(statList.get(i));
        viewModel.getNewStatList();
    }
    @Override
    public void onItemClick(int i) {}
}
