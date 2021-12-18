package neobis.project.iman_augustine.ort_nct.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import neobis.project.iman_augustine.ort_nct.R;

public class LoadingDialog {
    private final Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading_dialog, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }
}
