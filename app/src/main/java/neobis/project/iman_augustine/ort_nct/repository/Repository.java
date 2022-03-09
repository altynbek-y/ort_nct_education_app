package neobis.project.iman_augustine.ort_nct.repository;

import android.app.Application;
import android.content.Context;


import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.database.AppDao;
import neobis.project.iman_augustine.ort_nct.database.TestDatabase;
import neobis.project.iman_augustine.ort_nct.model.about_model.AboutModel;
import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionAnswerChoice;
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

    public static Repository getInstance(Application application)
    {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

    private Repository(Application application)
    {
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

    public void getListOfAnswersListForSubject(int subjectId, MutableLiveData<List<QuestionAnswerChoice>> answersMutableLiveData)
    {
        answersMutableLiveData.setValue(database.getListOfAnswersForSubject(subjectId));
    }
}



