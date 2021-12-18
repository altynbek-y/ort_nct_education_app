package  neobis.project.iman_augustine.ort_nct.singleclicklistener;

import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public abstract class OnSingleClickNavigationViewListener implements  NavigationView.OnNavigationItemSelectedListener {
    private static final long MIN_CLICK_INTERVAL = 800;
    private long mLastClickTime;

    public abstract boolean onSingleClick(MenuItem item);

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        long currentClickTime = SystemClock.uptimeMillis();
        long elapsedTIME = currentClickTime - mLastClickTime;

        mLastClickTime = currentClickTime;

        if (elapsedTIME <= MIN_CLICK_INTERVAL)
            return true;

        onSingleClick(item);
        return true;
    }
}
