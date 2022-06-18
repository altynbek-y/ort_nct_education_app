package nami.project.indie.ort_nct.ui.error;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import nami.project.indie.ort_nct.BaseActivity;
import nami.project.indie.ort_nct.R;


public class RefreshInternetActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ops_no_internet_layout);

        swipeRefresh = findViewById(R.id.refresh_internet);
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()) {
                    swipeRefresh.setRefreshing(false);
                    startBaseActivity();
                    finish();
                } else {
                    Toast.makeText(RefreshInternetActivity.this, "Сеть не найдена...", Toast.LENGTH_SHORT).show();
                }
                swipeRefresh.setRefreshing(false);
            }
        }, 2000);

    }

    private void startBaseActivity() {
        startActivity(new Intent(this, BaseActivity.class));
        finish();
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
