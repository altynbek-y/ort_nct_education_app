package neobis.project.iman_augustine.ort_nct.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.common.NetworkUtil;
import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.repository.Repository;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;

public class TestViewModel extends AndroidViewModel {
    private int connectionType;
    private Repository repository;
//    private MutableLiveData<List<NctTestSubjectInfo>> dataTestInfoList;
    private MutableLiveData<List<Subject>> subjectsListMutableLiveData;
    private SharedPreferences sharedPreferences;
    private String locale;

    public TestViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        connectionType = NetworkUtil.getConnectivityStatusString(application);
        sharedPreferences = PreferenceManager.getMySharedPreferences(application.getApplicationContext());
        locale = sharedPreferences.getString("locale", "ru");
        getListOfSubjects(locale);

        //Log.e("TestViewModel", "TestViewModel, locale: "+locale);
    }

    public void getListOfSubjects(String locale)
    {
        if(subjectsListMutableLiveData==null) {
            subjectsListMutableLiveData = new MutableLiveData<>();
        }
        repository.getListOfSubjects(locale, subjectsListMutableLiveData);
    }

    public LiveData<List<Subject>> getDataListOfSubjects() {
        return subjectsListMutableLiveData;
    }





 /*   public void getNewNctSubjectTestList(String locale) {
        if(dataTestInfoList==null) {
            dataTestInfoList = new MutableLiveData<>();
        }
        if(connectionType==0) {

        } else {
            repository.getSubjectTestsInfoList(locale, dataTestInfoList);
        }
    }
*/
/*    public void getNctSubjectTestFor(String locale, int id, Contract nctTestView) {
        if(subjectMutableLiveData==null) {
            subjectMutableLiveData = new MutableLiveData<>();
        }
        repository.getListOfSubjects(locale, subjectMutableLiveData, id, nctTestView);
    }*/


}
