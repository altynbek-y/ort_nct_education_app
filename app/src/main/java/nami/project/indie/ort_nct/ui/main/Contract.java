package nami.project.indie.ort_nct.ui.main;

public interface Contract {
    // void startTest(boolean b);
    void startTestActivity(int subject_id, String subject_name);
    void toastNoConnection();
}
