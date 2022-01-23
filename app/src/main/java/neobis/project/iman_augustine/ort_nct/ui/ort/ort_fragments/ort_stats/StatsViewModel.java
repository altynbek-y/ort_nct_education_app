package neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_stats;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.repository.OrtRepository;

public class StatsViewModel extends AndroidViewModel {
    private OrtRepository repository;
    private MutableLiveData<List<TestStat>> statList;

    public StatsViewModel(Application application) {
        super(application);
        repository = OrtRepository.getInstance(application);
        getNewStatList();
    }

    public MutableLiveData<List<TestStat>> getNewStatList() {
         if(statList==null) {
             statList = new MutableLiveData<>();
         }
        // repository.getOrtStatListFromDatabase(statList);
         return statList;
    }

    public LiveData<List<TestStat>> getStatList() {
        return statList;
    }

    public void removeOrtTestStat(TestStat testResult) {
        // repository.removeOrtTestStat(testResult);
    }

    @Override
    public String toString() {
        return "StatsViewModel {"+
                "ortRepository="+repository+
                ",statList="+statList+"}";
    }
}
