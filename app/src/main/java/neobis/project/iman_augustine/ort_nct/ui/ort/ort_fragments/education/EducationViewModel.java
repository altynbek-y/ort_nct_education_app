package neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.education;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.common.NetworkUtil;
import neobis.project.iman_augustine.ort_nct.model.educationmodel.EducationModel;
import neobis.project.iman_augustine.ort_nct.repository.OrtRepository;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;

public class EducationViewModel extends AndroidViewModel {
    private int connectionType;
    private OrtRepository repository;
    private MutableLiveData<List<EducationModel>> educationList;
    private SharedPreferences sharedPreferences;
    private String locale;

    public EducationViewModel(Application application) {
        super(application);
        repository = OrtRepository.getInstance(application);
        connectionType = NetworkUtil.getConnectivityStatusString(application);
        sharedPreferences = PreferenceManager.getMySharedPreferences(application);
        locale = sharedPreferences.getString("locale", "ru");
        getNewEducation();
    }

    public MutableLiveData<List<EducationModel>> getNewEducation() {
         if(educationList==null) {
             educationList = new MutableLiveData<>();
         }
         
         if(connectionType==0) {
             // repository.getEducationListFromDatabase(educationList);
         } else {
             repository.getEducationList(locale, educationList);
         }
         return educationList;
    }

    public LiveData<List<EducationModel>> getEducationList() {
        return educationList;
    }

    @Override
    public String toString() {
        return "EducationViewModel {"+
                "appRepository="+repository+
                ",educationList="+educationList+"}";
    }
}
