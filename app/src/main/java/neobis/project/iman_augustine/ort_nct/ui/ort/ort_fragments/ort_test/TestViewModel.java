package neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_test;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.common.NetworkUtil;
import neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;
import neobis.project.iman_augustine.ort_nct.repository.OrtRepository;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;

public class TestViewModel extends AndroidViewModel {
    private int connectionType;
    private OrtRepository repository;
    private MutableLiveData<List<SubjectTest>> testList;
    private SharedPreferences sharedPreferences;
    private String locale;

    public TestViewModel(Application application) {
        super(application);
        repository = OrtRepository.getInstance(application);
        connectionType = NetworkUtil.getConnectivityStatusString(application);
        sharedPreferences = PreferenceManager.getMySharedPreferences(application);
        locale = sharedPreferences.getString("locale", "ru");
        getNewOrtSubjectTestList();
    }

    public MutableLiveData<List<SubjectTest>> getNewOrtSubjectTestList() {
         if(testList==null) {
             testList = new MutableLiveData<>();
         }
         if(connectionType==0) {
            // repository.getOrtSubjectTestListFromDatabase(testList);
         } else {
             repository.getTestsList(locale, testList);
         }
         return testList;
    }

    public LiveData<List<SubjectTest>> getOrtSubjectTestList() {
        return testList;
    }

    @Override
    public String toString() {
        return "TestViewModel {"+
                "appRepository="+repository+
                ",educationList="+testList+"}";
    }
}
