package  neobis.project.iman_augustine.ort_nct.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Base64;

public class CommonMethod {
    public static String getDeviceId(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static Bitmap convertBase64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    public static Drawable convertBase64ToDrawable(Context context, String b64) {
        Bitmap bitmap = convertBase64ToBitmap(b64);
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }
}
