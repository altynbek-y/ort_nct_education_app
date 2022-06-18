package nami.project.indie.ort_nct.singleclicklistener;

import android.os.SystemClock;
import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener {
    private static final long MIN_CLICK_INTERVAL = 800;
    private long mLastClickTime;

    public abstract void onSingleClick(View view);

    @Override
    public void onClick(View view) {
        long currentClickTime = SystemClock.uptimeMillis();
        long elapsedTIME = currentClickTime - mLastClickTime;

        mLastClickTime = currentClickTime;

        if (elapsedTIME <= MIN_CLICK_INTERVAL)
            return;

        onSingleClick(view);
    }
}
