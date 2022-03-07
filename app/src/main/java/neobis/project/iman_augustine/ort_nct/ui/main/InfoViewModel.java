package neobis.project.iman_augustine.ort_nct.ui.main;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import neobis.project.iman_augustine.ort_nct.common.NetworkUtil;
import neobis.project.iman_augustine.ort_nct.model.about_model.AboutModel;
import neobis.project.iman_augustine.ort_nct.repository.Repository;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;

public class InfoViewModel extends AndroidViewModel {
    private int connectionType;
    private Repository repository;
    private MutableLiveData<AboutModel> aboutNct;
    private SharedPreferences sharedPreferences;

    public InfoViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        connectionType = NetworkUtil.getConnectivityStatusString(application);
        sharedPreferences = PreferenceManager.getMySharedPreferences(application);
        getNewAboutNct();
    }

    public void getNewAboutNct() {
//         if(aboutNct==null) {
//             aboutNct = new MutableLiveData<>();
//         }
//         if(connectionType==0) {
//
//         } else {
//             repository.getAboutNct(sharedPreferences.getString("locale", "ru"), aboutNct);
//         }
    }

    public LiveData<AboutModel> getAboutNct() {
        return aboutNct;
    }

    @Override
    public String toString() {
        return "EducationViewModel {"+
                "appRepository="+repository+
                ",aboutNct="+aboutNct+"}";
    }
}
