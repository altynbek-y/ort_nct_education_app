package  neobis.project.iman_augustine.ort_nct.repository;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import neobis.project.iman_augustine.ort_nct.database.AppDao;
import neobis.project.iman_augustine.ort_nct.database.AppDatabase;
import neobis.project.iman_augustine.ort_nct.model.educationmodel.EducationModel;
import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;
import neobis.project.iman_augustine.ort_nct.network.OrtDao;
import neobis.project.iman_augustine.ort_nct.network.OrtRetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton Pattern
 */
public class OrtRepository {
    public final String TAG = "OrtRepository";

    private static OrtRepository instance;
    private OrtDao service;
    private AppDao database;

    private Context context;

    public static OrtRepository getInstance(Application application) {
        if (instance == null) {
            instance = new OrtRepository(application);
        }
        return instance;
    }

    private OrtRepository(Application application) {
        context = application.getApplicationContext();
        service = OrtRetrofitClientInstance.getRetrofitInstance().create(OrtDao.class);
        database = AppDatabase.getInMemoryDatabase(application.getApplicationContext()).appDao();
    }
    //--------------------------------------ORT-EDUCATION---------------------------------------------------------------------------
    public MutableLiveData<List<EducationModel>> getEducationList(String lang, final MutableLiveData<List<EducationModel>> educationList) {
        service.getOrtEducation(lang).enqueue(new Callback<List<EducationModel>>() {
            @Override
            public void onResponse(Call<List<EducationModel>> call, Response<List<EducationModel>> response) {
                if (response.body() != null) {
                    educationList.setValue(response.body());
                    // database.deleteOrtEducation();
                    // database.insertOrtEducation(response.body());
                    Log.e(TAG, "onResponse: successful: "+response.message());
                }
            }
            @Override
            public void onFailure(Call<List<EducationModel>> call, Throwable t) {
                educationList.setValue(null);
                Log.e(TAG, "onFailure: unsuccessful: "+t.getMessage());
            }
        });
        return educationList;
    }

    public void getEducationFor(final MutableLiveData<EducationModel> educationList, int id) {
        service.getOrtEducationFor(id).enqueue(new Callback<EducationModel>() {
            @Override
            public void onResponse(Call<EducationModel> call, Response<EducationModel> response) {
                if (response.body() != null) {
                    educationList.setValue(response.body());
                    // database.deleteOrtEducation();
                    // database.insertOrtEducation(response.body());
                    Log.e(TAG, "onResponse: successful: "+response.message());
                }
            }
            @Override
            public void onFailure(Call<EducationModel> call, Throwable t) {
                educationList.setValue(null);
                Log.e(TAG, "onFailure: unsuccessful: "+t.getMessage());
            }
        });
    }

    public void getEducationListFromDatabase(MutableLiveData<List<EducationModel>> educationList) {
        // if(database.getOrtEducation()!=null) {
        //    educationList.setValue(database.getOrtEducation());
        // }
    }
    //-----------------------------------------------ORT-SUBJECT-TEST----------------------------------------------------------------
    public MutableLiveData<List<SubjectTest>> getTestsList(String lang, final MutableLiveData<List<SubjectTest>> testList) {
        service.getTestsList(lang)
                .enqueue(new Callback<List<SubjectTest>>() {
                    @Override
                    public void onResponse(Call<List<SubjectTest>> call, Response<List<SubjectTest>> response) {
                        if (response.body() != null) {
                            testList.setValue(response.body());
                          //  database.deleteOrtSubjectPracticeTest();
                           // database.insertOrtSubjectPracticeTest(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<SubjectTest>> call, Throwable t) {
                        testList.setValue(null);
                    }
                });
        return testList;
    }

    public MutableLiveData<SubjectTest> getTestFor(final MutableLiveData<SubjectTest> test, int id) {
        service.getTestFor(id).enqueue(new Callback<SubjectTest>() {
                    @Override
                    public void onResponse(Call<SubjectTest> call, Response<SubjectTest> response) {
                        if (response.body() != null) {
                            test.setValue(response.body());
                            //  database.deleteOrtSubjectPracticeTest();
                            // database.insertOrtSubjectPracticeTest(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<SubjectTest> call, Throwable t) {
                        test.setValue(null);
                    }
                });
        return test;
    }

    public void getOrtSubjectTestListFromDatabase(MutableLiveData<List<SubjectTest>> testList) {
        // if(database.getOrtSubjectPracticeTest()!=null) {
        //    testList.setValue(database.getOrtSubjectPracticeTest());
        // }
    }

    //------------------------------ORT-STATISTICS--------------------------------------------------
    public void insertOrtResult(TestStat testResult) {
        database.insertTestResultNct(testResult);
    }
    public void getOrtStatListFromDatabase(MutableLiveData<List<TestStat>> statList) {
        if(database.getAllTestStatsOrt()!=null) {
            statList.setValue(database.getAllTestStatsOrt());
        }
    }

    public void removeOrtTestStat(TestStat testStat) {
        database.deleteTestStatOrt(testStat);
    }
}


