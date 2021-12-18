package neobis.project.iman_augustine.ort_nct.ui.ort.test;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.repository.OrtRepository;

public class OrtTestActivityViewModel extends AndroidViewModel {
    private OrtRepository repository;

    public OrtTestActivityViewModel(Application application) {
        super(application);
        repository = OrtRepository.getInstance(application);
    }

    public void insertOrtTestResult(String subjectName, long correct, long incorrect ) {
        repository.insertOrtResult(
                new TestStat(subjectName,
                0,
                0,
                "ort",
                correct,
                incorrect));
    }
}