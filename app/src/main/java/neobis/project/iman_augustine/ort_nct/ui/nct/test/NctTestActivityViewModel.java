package neobis.project.iman_augustine.ort_nct.ui.nct.test;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.repository.NctRepository;

public class NctTestActivityViewModel extends AndroidViewModel {
    private NctRepository repository;

    public NctTestActivityViewModel(Application application) {
        super(application);
        repository = NctRepository.getInstance(application);
    }

    public void insertNctTestResult(String subjectName, int grade, int variant , long correct, long incorrect ) {
      //  repository.insertNctResult(new TestStat(subjectName, grade, variant, "nct", correct, incorrect));
    }
}