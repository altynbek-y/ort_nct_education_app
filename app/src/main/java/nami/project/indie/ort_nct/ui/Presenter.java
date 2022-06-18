package nami.project.indie.ort_nct.ui;

import nami.project.indie.ort_nct.database.AppDao;

public class Presenter {//implements Contract.TestResultPresenter{
    private AppDao appDatabase;
    Contract.TestResultContract testView;

    public Presenter(Contract.TestResultContract testView) {
        this.testView = testView;
    }

  /*  @Override
    public void sendTestResult(String userId, long score, Context context) {
       // appDatabase = AppDatabase.getInMemoryDatabase(context).appDao();
       // appDatabase.insertTestResultOrt(new TestResult(score, userId));
        if (CommonMethodConnectivity.isNetworkAvailable(context)) {
            service.sendTestResult(new TestResult(score,userId)).enqueue(new Callback<Void>()
            {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                            Log.i("presenter", "testresultort: response successful");
                    } else {
                        testView.showMessage("Результат итового теста не был отправлен из за ошибки сети!");
                        Log.i("presenter", "testresultort: response unsuccessful");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Log.i("presenter", "onFailure: "+t.getMessage());
                }
            });
        } else {
            testView.showMessage("Нету сети! Результат итового теста не был отправлен!");
        }
    }
    //-----------------------------------NCT-RESULT-DISPATCH-------------------------------------------------//
    @Override
    public void sendTestResultNct(Context context,
                                  String userId,
                                  String subjectName,
                                  String subjectGrade,
                                  String subjectVar,
                                  long correct,
                                  long incorrect) {
        appDatabase = AppDatabase.getInMemoryDatabase(context).appDao();
        TestResultNct testResultNct = new TestResultNct(userId,
                                                        subjectName,
                                                        subjectGrade,
                                                        subjectVar, 1, correct, incorrect);
        appDatabase.insertTestResultNct(testResultNct); // Saving result to database
        if (CommonMethodConnectivity.isNetworkAvailable(context)) {
            service.sendTestResultNct(null).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        Log.i("testresultnct", "onResponse: response successful");
                    } else {
                        testView.showMessage("Результат предметного теста НЦТ не был отправлен из за ошибки сети!");
                        Log.i("testresultnct", "onResponse: response unsuccessful. "+response.toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Log.i("testresultnct", "onFailure: "+t.getMessage());
                }
            });
        } else {
            testView.showMessage("Нету сети! Результат предметного теста НЦТ не был отправлен!");
        }
    }*/
}
