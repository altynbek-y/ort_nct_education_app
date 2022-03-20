package neobis.project.iman_augustine.ort_nct.ui.main.test;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionAnswerChoice;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionWithAnswers;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.repository.Repository;

public class TestActivityViewModel extends AndroidViewModel
{
    private Repository repository;
    private MutableLiveData<List<Question>> questionsListMutableLiveData;
    private MutableLiveData<List<QuestionAnswerChoice>> answersListMutableLiveData;
    private MutableLiveData<List<QuestionWithAnswers>> questionsWithAnswersMutableLiveData;

    public TestActivityViewModel(Application application)
    {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void getListOfQuestionsListForSubject(int subjectId)
    {
        if(questionsListMutableLiveData==null) {
            questionsListMutableLiveData = new MutableLiveData<>();
        }
        repository.getListOfQuestionsListForSubject(subjectId, questionsListMutableLiveData);
    }

    public LiveData<List<Question>> getDataListOfQuestions()
    {
        return questionsListMutableLiveData;
    }

    public void getListOfAnswersListForSubject(int subjectId)
    {
        if(answersListMutableLiveData==null) {
            answersListMutableLiveData = new MutableLiveData<>();
        }
        repository.getListOfAnswersListForSubject(subjectId, answersListMutableLiveData);
    }

    public LiveData<List<QuestionAnswerChoice>> getDataListOfAnswers()
    {
        return answersListMutableLiveData;
    }

    // Get list of questions with answers for a subject
    public void getListOfQuestionsWithAnswersForSubject(int subjectId)
    {
        if(questionsWithAnswersMutableLiveData==null) {
            questionsWithAnswersMutableLiveData = new MutableLiveData<>();
        }
        repository.getListOfQuestionsWithAnswers(subjectId, questionsWithAnswersMutableLiveData);
    }

    // Get data list of questions with answers
    public LiveData<List<QuestionWithAnswers>> getDataListOfQuestionsWithAnswers()
    {
        return questionsWithAnswersMutableLiveData;
    }


    //----------------------------------------------------------------------------------------------
    public void insertTestResult(String subjectName, String variant , long correct, long incorrect )
    {
      //  repository.insertNctResult(new TestStat(subjectName, grade, variant, "nct", correct, incorrect));
    }
}