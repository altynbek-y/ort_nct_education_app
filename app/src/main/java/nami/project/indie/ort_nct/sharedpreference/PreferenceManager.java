package nami.project.indie.ort_nct.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getMySharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
