package neobis.project.iman_augustine.ort_nct.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.statistics_model.TestStat;
import neobis.project.iman_augustine.ort_nct.repository.Repository;

/**
 *
 * @author Iman Augustine
 *
 * ViewModel subclass of AndroidViewModel.
 *
 * */

public class StatsViewModel extends AndroidViewModel {
    private Repository repository;
    private MutableLiveData<List<TestStat>> statList;

    public StatsViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
       // getNewStatList();
    }

/*    public MutableLiveData<List<TestStat>> getNewStatList() {
         if(statList==null) {
             statList = new MutableLiveData<>();
         }
         repository.getNctStatListFromDatabase(statList);
         return statList;
    }

    public LiveData<List<TestStat>> getStatList() {
        return statList;
    }

    public void removeNctTestStat(TestStat testResult) {
        repository.removeNctTestStat(testResult);
    }*/

    @Override
    public String toString() {
        return "StatsViewModel {"+
                "repository="+repository+
                ",statList="+statList+"}";
    }
}
