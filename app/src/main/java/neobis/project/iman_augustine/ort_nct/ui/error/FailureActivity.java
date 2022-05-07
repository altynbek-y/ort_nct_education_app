package neobis.project.iman_augustine.ort_nct.ui.error;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import neobis.project.iman_augustine.ort_nct.R;

public class FailureActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_layout);

        /*swipeRefresh = findViewById(R.id.refresh_internet);
        swipeRefresh.setOnRefreshListener(this);*/
    }

  /*  @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()) {
                    swipeRefresh.setRefreshing(false);
                    startBaseActivity();
                    finish();
                } else {
                    Toast.makeText(FailureActivity.this, "Сеть не найдена...", Toast.LENGTH_SHORT).show();
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
    }*/
}
