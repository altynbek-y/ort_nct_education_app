package neobis.project.iman_augustine.ort_nct.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import androidx.lifecycle.MutableLiveData;

import neobis.project.iman_augustine.ort_nct.database.AppDao;
import neobis.project.iman_augustine.ort_nct.database.AppDatabase;
import neobis.project.iman_augustine.ort_nct.model.aboutnctmodel.AboutNctModel;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.SubjectTestNct;
import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.NctTestSubjectInfo;
import neobis.project.iman_augustine.ort_nct.network.NctDao;
import neobis.project.iman_augustine.ort_nct.network.NctRetrofitClientInstance;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.ui.nct.nct_fragments.nct_test.Contract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton Pattern
 */
public class NctRepository {
    private final String TAG = "NctRepository";

    private static NctRepository instance;
    private NctDao service;
    private AppDao database;

    private Context context;

    public static NctRepository getInstance(Application application) {
        if (instance == null) {
            instance = new NctRepository(application);
        }
        return instance;
    }

    private NctRepository(Application application) {
        context = application.getApplicationContext();
        service = NctRetrofitClientInstance.getRetrofitInstance().create(NctDao.class);
        database = AppDatabase.getInMemoryDatabase(application.getApplicationContext()).appDao();
    }
    /*---------------------------------------------NCT-----------------------------------------------------------*/
    public MutableLiveData<List<NctTestSubjectInfo>> getSubjectTestsInfoList(String lang, final MutableLiveData<List<NctTestSubjectInfo>> dataList) {
        service.getSubjectTestsInfo(lang).enqueue(new Callback<List<NctTestSubjectInfo>>() {
            @Override
            public void onResponse(Call<List<NctTestSubjectInfo>> call, Response<List<NctTestSubjectInfo>> response) {
                if (response.body()!=null) {
                    dataList.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<NctTestSubjectInfo>> call, Throwable t) {
                dataList.setValue(null);
                Log.i(TAG, "onResponse: nct subject data downloaded unsuccessfully, message: "+t.getMessage());
            }
        });
        return dataList;
    }

    public void getSubjectTestFor(String lang, final MutableLiveData<SubjectTestNct> dataTest, int id, final Contract nctTestView) {
        service.getSubjectTestFor(lang, id).enqueue(new Callback<SubjectTestNct>() {
            @Override
            public void onResponse(Call<SubjectTestNct> call, Response<SubjectTestNct> response) {
                if (response.body()!=null) {
                    dataTest.setValue(response.body());
                    nctTestView.startTest(response.body().getQuestions().size()>0);
                }
            }
            @Override
            public void onFailure(Call<SubjectTestNct> call, Throwable t) {
                dataTest.setValue(null);
                nctTestView.toastNoConnection();
                Log.i(TAG, "onResponse: nct subject test downloaded unsuccessfully, message: "+t.getMessage());
            }
        });
    }

    public void getAboutNct(String lang, final MutableLiveData<AboutNctModel> aboutNct) {
        service.getAboutNct(lang).enqueue(new Callback<AboutNctModel>() {
            @Override
            public void onResponse(Call<AboutNctModel> call, Response<AboutNctModel> response) {
                if (response.body()!=null) {
                    aboutNct.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<AboutNctModel> call, Throwable t) {
                aboutNct.setValue(null);
                Log.i(TAG, "onResponse: about nct downloaded unsuccessfully, message: "+t.getMessage());
            }
        });
    }

    //----------------------------------------------------------------------------------------------
    public void insertNctResult(TestStat testResult) {
        database.insertTestResultNct(testResult);
    }
    public void getNctStatListFromDatabase(MutableLiveData<List<TestStat>> statList) {
        if(database.getAllTestStatsNct()!=null) {
            statList.setValue(database.getAllTestStatsNct());
        }
    }

    public void removeNctTestStat(TestStat testStat) {
        database.deleteTestStatNct(testStat);
    }
}




