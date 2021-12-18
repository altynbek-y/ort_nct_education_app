package neobis.project.iman_augustine.ort_nct.ui.nct.nct_fragments.nct_about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.aboutnctmodel.AboutNctModel;
import neobis.project.iman_augustine.ort_nct.ui.nct.nct_fragments.nct_test.NctTestViewModel;

public class NctInformationFragment extends Fragment {
    private WebView webView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textView;
    private InfoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nct_information, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
           // viewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
            viewModel = ViewModelProvider.AndroidViewModelFactory
                    .getInstance(requireActivity().getApplication()).create(InfoViewModel.class);

            webView = view.findViewById(R.id.aboutNctWebView);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
            textView = view.findViewById(R.id.emptyTextView);
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    viewModel.getNewAboutNct();
                }
            });
            viewModel.getAboutNct().observe(this, new Observer<AboutNctModel>() {
                @Override
                public void onChanged(@Nullable AboutNctModel data) {
                    swipeRefreshLayout.setRefreshing(false);
                    if( data!=null && !data.getPayload().isEmpty() ) {
                        webView.setVisibility(View.VISIBLE);
                        webView.loadData(data.getPayload(), "text/html", "UTF-8");
                        textView.setVisibility(View.GONE);
                        return;
                    }
                    webView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            });
        } catch(NullPointerException error) {
            error.printStackTrace();
        }
    }
}