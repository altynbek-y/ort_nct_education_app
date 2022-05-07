package  neobis.project.iman_augustine.ort_nct.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences singleton class
 */

public class SharedPreferencesSingleton {

    public static final String PREFERENCE = "user_preference";
    public static final String USER_CODE = "user_code";
    public static final String AUTH_TOKEN = "auth_token";
    public static final String CURRENT_FRAGMENT_ORT = "current_fragment_ort";
    public static final String CURRENT_FRAGMENT_NCT = "current_fragment_nct";
    public static final String TOTAL_QUESTIONS_COUNT = "total_questions_count";
    public static final String TOTAL_CORRECT_ANSWERED = "total_correct_answered";
    public static final String AGE = "age";
    public static final String AGE_RB_ID = "age_rb_id";
    public static final String GRADE = "grade";
    public static final String GRADE_RB_ID = "grade_rb_id";
    public static final String REGION = "region";
    public static final String REGION_RB_ID = "region_rb_id";
    public static final String SWITCH_SHOW_CORRECT_ANSWER = "switch_show_correct_answer";
    public static final String SWITCH_SHOW_TIMER = "switch_show_timer";
    public static final String LAUNCH_ORT_NEXT_TIME = "launch_ort_next_time";
    public static final String LAUNCH_NCT_NEXT_TIME = "launch_nct_next_time";
    public static final String FIRST_LAUNCH = "launch_nct_next_time";

    private SharedPreferences sharedPreferences;
    private Context applicationContext;

    public SharedPreferencesSingleton(Context applicationContext) {
        this.applicationContext = applicationContext;
        this.sharedPreferences = this.applicationContext.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesSingleton getLocalSharedPreferences(Context applicationContext) {
        return new SharedPreferencesSingleton(applicationContext);
    }

     public boolean isFirstLaunch() {
         return this.sharedPreferences.getBoolean(FIRST_LAUNCH, true);
     }

    public void addAuthToken(String user_token) {
        this.sharedPreferences.edit().putString(AUTH_TOKEN, user_token).apply();
    }

    public void addAge(long age) {
        this.sharedPreferences.edit().putLong(AGE, age).apply();
    }

    public void addAgeRbId(int id) {
        this.sharedPreferences.edit().putInt(AGE_RB_ID, id).apply();
    }


    public long getAge() {
        return this.sharedPreferences.getLong(AGE, -1);
    }

    public int getAgeRbId() {
        return this.sharedPreferences.getInt(AGE_RB_ID, -1);
    }


    public void addGrade(long grade) {
        this.sharedPreferences.edit().putLong(GRADE, grade).apply();
    }

    public void addGradeRbId(int id) {
        this.sharedPreferences.edit().putInt(GRADE_RB_ID, id).apply();
    }

    public long getGrade() {
        return this.sharedPreferences.getLong(GRADE, -1);
    }

    public int getGradeRbId() {
        return this.sharedPreferences.getInt(GRADE_RB_ID, -1);
    }


    public void addRegion(String region) {
        this.sharedPreferences.edit().putString(REGION, region).apply();
    }

    public void addRegionRbId(int id) {
        this.sharedPreferences.edit().putInt(REGION_RB_ID, id).apply();
    }

    public String getRegion() {
        return this.sharedPreferences.getString(REGION, null);
    }

    public int getRegionRbId() {
        return this.sharedPreferences.getInt(REGION_RB_ID, -1);
    }

    public void destroyUserData() {
        this.sharedPreferences.edit().remove(AGE).apply();
        this.sharedPreferences.edit().remove(GRADE).apply();
        this.sharedPreferences.edit().remove(REGION).apply();
        this.sharedPreferences.edit().remove(AGE_RB_ID).apply();
        this.sharedPreferences.edit().remove(GRADE_RB_ID).apply();
        this.sharedPreferences.edit().remove(REGION_RB_ID).apply();
    }

    public void setRbIDsNone() {
        addAgeRbId(-1);
        addGradeRbId(-1);
        addRegionRbId(-1);
    }

    public boolean isShowCorrectAnswerAllowed() {
        return this.sharedPreferences.getBoolean(SWITCH_SHOW_CORRECT_ANSWER, false);
    }

    /**
     * Settings related variables
     */
    public void setShowCorrectAnswerAllowed(boolean allow) {
        this.sharedPreferences.edit().putBoolean(SWITCH_SHOW_CORRECT_ANSWER, allow).apply();
    }

    public boolean isShowTimerAllowed() {
        return this.sharedPreferences.getBoolean(SWITCH_SHOW_TIMER, false);
    }

    public void setShowTimerAllowed(boolean allow) {
        this.sharedPreferences.edit().putBoolean(SWITCH_SHOW_TIMER, allow).apply();
    }

    /**
     * Remember what activity to launch next time
     */
    public void setOrtTestLaunchNextTime() {
        this.sharedPreferences.edit().putBoolean(LAUNCH_ORT_NEXT_TIME, true).apply();
        this.sharedPreferences.edit().putBoolean(LAUNCH_NCT_NEXT_TIME, false).apply();
    }

    public void setNctTestLaunchNextTime() {
        this.sharedPreferences.edit().putBoolean(LAUNCH_NCT_NEXT_TIME, true).apply();
        this.sharedPreferences.edit().putBoolean(LAUNCH_ORT_NEXT_TIME, false).apply();
    }

    public boolean isOrtTestLaunchNextTime() {
        return this.sharedPreferences.getBoolean(LAUNCH_ORT_NEXT_TIME, true);
    }

    public boolean isNctTestLaunchNextTime() {
        return this.sharedPreferences.getBoolean(LAUNCH_NCT_NEXT_TIME, false);
    }

    public String getUserCode() {
        return this.sharedPreferences.getString(USER_CODE, null);
    }

    public void setUserCode(String user_code) {
        this.sharedPreferences.edit().putString(USER_CODE, user_code).apply();
    }

    public void setCurrentFragmentOrt(int position) {
        this.sharedPreferences.edit().putInt(CURRENT_FRAGMENT_ORT, position).apply();
    }
    public int getCurrentFragmentOrt() {
        return this.sharedPreferences.getInt(CURRENT_FRAGMENT_ORT, 0);
    }

    public void setCurrentFragmentNct(int position) {
        this.sharedPreferences.edit().putInt(CURRENT_FRAGMENT_NCT, position).apply();
    }
    public int getCurrentFragmentNct() {
        return this.sharedPreferences.getInt(CURRENT_FRAGMENT_NCT, 0);
    }

    public void saveTotalCorrectlyAnswered(int correctAnswerCount) {
        this.sharedPreferences.edit().putInt(TOTAL_CORRECT_ANSWERED, correctAnswerCount).apply();
    }
    public int getTotalCorrectlyAnswered() {
        return this.sharedPreferences.getInt(TOTAL_CORRECT_ANSWERED, 0);
    }
    public void saveTotalQuestionsCount(int questionsCount) {
        this.sharedPreferences.edit().putInt(TOTAL_QUESTIONS_COUNT, questionsCount).apply();
    }
    public int getTotalQuestionsCount() {
        return this.sharedPreferences.getInt(TOTAL_QUESTIONS_COUNT, 0);
    }

    public void destroyOrtFinalResult() {
        this.sharedPreferences.edit().remove(TOTAL_CORRECT_ANSWERED).apply();
        this.sharedPreferences.edit().remove(TOTAL_QUESTIONS_COUNT).apply();
    }
}
