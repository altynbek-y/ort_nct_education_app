package neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.education;

import android.content.Intent;
import android.content.SharedPreferences;
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
import neobis.project.iman_augustine.ort_nct.adapters.EducationAdapter;
import neobis.project.iman_augustine.ort_nct.adapters.VerticalSpaceItemDecoration;
import neobis.project.iman_augustine.ort_nct.model.educationmodel.EducationModel;
import neobis.project.iman_augustine.ort_nct.model.educationmodel.TranslationsModel;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;
import neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.education.EducationViewModel;

public class OrtEducationFragment extends Fragment implements LifecycleOwner, EducationAdapter.OnItemListener {
    public final static String SUBJECT_NAME = "subject_name";
    public final static String CONTENT = "content";
    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EducationAdapter educationAdapter;
    private EducationViewModel viewModel;
    private List<EducationModel> educationList;
    private SharedPreferences sharedPreferences;
    private String locale;

    public OrtEducationFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ort_education, container, false);
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

            swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    viewModel.getNewEducation();
                    educationAdapter.notifyDataSetChanged();
                }
            });
            emptyTextView = view.findViewById(R.id.textView);
            recyclerView = view.findViewById(R.id.ort_subject_list);
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VerticalSpaceItemDecoration.VERTICAL_ITEM_SPACE));
            viewModel = ViewModelProviders.of(this).get(EducationViewModel.class);
            educationAdapter = new EducationAdapter(new ArrayList<EducationModel>(), this, getContext());
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            viewModel.getEducationList().observe(this, new Observer<List<EducationModel>>() {
                @Override
                public void onChanged(@Nullable List<EducationModel> educationListNew) {
                    educationList = educationListNew;
                    educationAdapter.setValues(educationListNew);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            recyclerView.setAdapter(educationAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int i) {
        startEducationActivity(i);
    }

    private void startEducationActivity(int i) {
        TranslationsModel content = educationList.get(i).getContent();
        Intent intent = new Intent(getActivity(), EducationActivity.class);
        intent.putExtra(SUBJECT_NAME, educationList.get(i).getName());
        if(locale.equals("ru")) {
            intent.putExtra(CONTENT, content.getRussian().getContent());
        } else {
            intent.putExtra(CONTENT, content.getKyrgyz().getContent());
        }
        startActivity(intent);
    }
}
