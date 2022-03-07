package neobis.project.iman_augustine.ort_nct.ui.main.test;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.repository.Repository;

public class TestActivityViewModel extends AndroidViewModel
{
    private Repository repository;
    private MutableLiveData<List<Question>> questionsListMutableLiveData;

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


    //----------------------------------------------------------------------------------------------
    public void insertTestResult(String subjectName, String variant , long correct, long incorrect )
    {
      //  repository.insertNctResult(new TestStat(subjectName, grade, variant, "nct", correct, incorrect));
    }
}