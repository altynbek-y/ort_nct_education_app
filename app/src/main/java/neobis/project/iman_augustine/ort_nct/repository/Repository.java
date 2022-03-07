package neobis.project.iman_augustine.ort_nct.repository;

import android.app.Application;
import android.content.Context;


import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.database.AppDao;
import neobis.project.iman_augustine.ort_nct.database.TestDatabase;
import neobis.project.iman_augustine.ort_nct.model.about_model.AboutModel;
import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;

/**
 * Singleton Pattern
 */
public class Repository
{
    private final String TAG = "Repository";

    private static Repository instance;
    private AppDao database;

    private Context context;

    public static Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

    private Repository(Application application) {
        context = application.getApplicationContext();
        database = TestDatabase.getInMemoryDatabase(application.getApplicationContext()).appDao();
    }

    public void getListOfSubjects(String locale, MutableLiveData<List<Subject>> subjectsMutableLiveData)
    {
        subjectsMutableLiveData.setValue(database.getListOfSubjects());
    }

    public void getListOfQuestionsListForSubject(int subjectId, MutableLiveData<List<Question>> questionsMutableLiveData)
    {
        questionsMutableLiveData.setValue(database.getListOfQuestionsListForSubject(subjectId));
    }






    /*---------------------------------------------NCT-----------------------------------------------------------*/
    // service = NctRetrofitClientInstance.getRetrofitInstance().create(NctDao.class);

   /* public MutableLiveData<List<NctTestSubjectInfo>> getSubjectTestsInfoList(String lang, final MutableLiveData<List<NctTestSubjectInfo>> dataList) {
        service.getSubjectTestsInfo(lang).enqueue(new Callback<List<NctTestSubjectInfo>>() {
            @Override
            public void onResponse(Call<List<NctTestSubjectInfo>> call, Response<List<NctTestSubjectInfo>> response) {
                if (response.body() != null) {
                    dataList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<NctTestSubjectInfo>> call, Throwable t) {
                dataList.setValue(null);
                Log.i(TAG, "onResponse: nct subject data downloaded unsuccessfully, message: " + t.getMessage());
            }
        });
        return dataList;
    }*/

  /*  public void getSubjectTestFor(String lang, final MutableLiveData<SubjectTest> dataTest, int id, final Contract nctTestView) {
        service.getSubjectTestFor(lang, id).enqueue(new Callback<SubjectTest>() {
            @Override
            public void onResponse(Call<SubjectTest> call, Response<SubjectTest> response) {
                if (response.body() != null) {
                    dataTest.setValue(response.body());
                    nctTestView.startTest(response.body().getQuestions().size() > 0);
                }
            }

            @Override
            public void onFailure(Call<SubjectTest> call, Throwable t) {
                dataTest.setValue(null);
                nctTestView.toastNoConnection();
                Log.i(TAG, "onResponse: nct subject test downloaded unsuccessfully, message: " + t.getMessage());
            }
        });
    }*/

   /* public void getAboutNct(String lang, final MutableLiveData<AboutModel> aboutNct) {
        service.getAboutNct(lang).enqueue(new Callback<AboutModel>() {
            @Override
            public void onResponse(Call<AboutModel> call, Response<AboutModel> response) {
                if (response.body() != null) {
                    aboutNct.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AboutModel> call, Throwable t) {
                aboutNct.setValue(null);
                Log.i(TAG, "onResponse: about nct downloaded unsuccessfully, message: " + t.getMessage());
            }
        });
    }*/
}



