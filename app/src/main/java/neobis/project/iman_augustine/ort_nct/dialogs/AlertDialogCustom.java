package neobis.project.iman_augustine.ort_nct.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertDialogCustom {
    private Activity activity;
    private android.app.AlertDialog dialog;

    public AlertDialogCustom(Activity activity) {
        this.activity = activity;
    }

    public void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Are you sure?");
        alert.setMessage("Are you sure to exit from testing?");
        alert.setCancelable(false);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismissAlertDialog();
                activity.finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismissAlertDialog();
            }
        });
        dialog = alert.create();
        dialog.show();
    }

    public void dismissAlertDialog() {
        // dialog.dismiss();
    }
}
