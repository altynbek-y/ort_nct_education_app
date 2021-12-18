package  neobis.project.iman_augustine.ort_nct.ui.nct.nct_fragments.nct_test;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.common.NetworkUtil;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.NctTestSubjectInfo;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.SubjectTestNct;
import neobis.project.iman_augustine.ort_nct.repository.NctRepository;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;

public class NctTestViewModel extends AndroidViewModel {
    private int connectionType;
    private NctRepository repository;
    private MutableLiveData<List<NctTestSubjectInfo>> dataTestInfoList;
    private MutableLiveData<SubjectTestNct> subjectTest;
    private SharedPreferences sharedPreferences;
    private String locale;

    public NctTestViewModel(Application application) {
        super(application);
        repository = NctRepository.getInstance(application);
        connectionType = NetworkUtil.getConnectivityStatusString(application);
        sharedPreferences = PreferenceManager.getMySharedPreferences(application.getApplicationContext());
        locale = sharedPreferences.getString("locale", "ru");
        Log.e("NctTestViewModel", "NctTestViewModel, locale: "+locale);
        getNewNctSubjectTestList(locale);
    }

    public void getNewNctSubjectTestList(String locale) {
        if(dataTestInfoList==null) {
            dataTestInfoList = new MutableLiveData<>();
        }
        if(connectionType==0) {

        } else {
            repository.getSubjectTestsInfoList(locale, dataTestInfoList);
        }
    }

    public void getNctSubjectTestFor(String locale, int id, Contract nctTestView) {
        if(subjectTest==null) {
            subjectTest = new MutableLiveData<>();
        }
        if(connectionType==0) {

        } else {
            repository.getSubjectTestFor(locale, subjectTest, id, nctTestView);
        }
    }

    public LiveData<List<NctTestSubjectInfo>> getNctTestInfoList() {
        return dataTestInfoList;
    }
    public LiveData<SubjectTestNct> getNctSubjectTestList() { return subjectTest; }
}
