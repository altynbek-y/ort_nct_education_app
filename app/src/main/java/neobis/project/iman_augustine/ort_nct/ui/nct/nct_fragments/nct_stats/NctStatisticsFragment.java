package neobis.project.iman_augustine.ort_nct.ui.nct.nct_fragments.nct_stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import neobis.project.iman_augustine.ort_nct.R;


import java.util.ArrayList;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.adapters.StatListAdapter;
import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;

/**
 *
 * @author Iman Augustine
 *
 * Nct test statistics fragment.
 *
 * */

public class NctStatisticsFragment extends Fragment implements StatListAdapter.OnItemListener {
    // Variables
    private StatListAdapter testStatListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView textView;
    private StatsViewModel viewModel;
    private List<TestStat> statList;

    public NctStatisticsFragment() {}

    // On view creation
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nct_statistics, container, false);
    }

    // On creation
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    // On view already created
   /* @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Variables initiated
        textView = view.findViewById(R.id.textView);
        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getNewStatList();
            }
        });
        // Recycler view list
        recyclerView = view.findViewById(R.id.statistics_list);
        // View model initialized
        // viewModel = ViewModelProviders.of(this).get(StatsViewModel.class);
        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication()).create(StatsViewModel.class);
        // Test statistics list adapter
        testStatListAdapter = new StatListAdapter(new ArrayList<TestStat>(), this, getContext());
        viewModel.getStatList().observe(this, new Observer<List<TestStat>>() {
            @Override
            public void onChanged(@Nullable List<TestStat> testNctStats) {
                statList = testNctStats;
                testStatListAdapter.setValues(testNctStats);
                swipeRefreshLayout.setRefreshing(false);
                if( statList.size() > 0 ) {
                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    return;
                }
                textView.setVisibility(View.VISIBLE);
            }
        });
        // RecyclerView adapter set
        recyclerView.setAdapter(testStatListAdapter);
        // Renders recyclerview as a grid with two columns in each row
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Item touch helper
        // Allows to remove an item from RecyclerView with a swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            // On item move
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            // On item swiped
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // viewModel.removeNctTestStat(testStatListAdapter.get);
                // viewModel.removeNctTestStat(testStatListAdapter.);
                onRemoveClick(viewHolder.getAdapterPosition());
            }
            // Attaches ItemTouchHelper to the RecyclerView list
        }).attachToRecyclerView(recyclerView);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onRemoveClick(int i) {
        viewModel.removeNctTestStat(statList.get(i));
        viewModel.getNewStatList();
    }
    @Override
    public void onItemClick(int i) {}*/

    @Override
    public void onRemoveClick(int i) {

    }

    @Override
    public void onItemClick(int i) {

    }
}
