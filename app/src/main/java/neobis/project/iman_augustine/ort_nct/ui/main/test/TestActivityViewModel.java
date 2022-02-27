package neobis.project.iman_augustine.ort_nct.ui.main.test;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import neobis.project.iman_augustine.ort_nct.repository.Repository;

public class TestActivityViewModel extends AndroidViewModel {
    private Repository repository;

    public TestActivityViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void insertTestResult(String subjectName, String variant , long correct, long incorrect ) {
      //  repository.insertNctResult(new TestStat(subjectName, grade, variant, "nct", correct, incorrect));
    }
}