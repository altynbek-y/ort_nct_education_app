package neobis.project.iman_augustine.ort_nct.ui;

import android.content.Context;

public interface Contract {
    interface ChooseTestTypeView {
        void toOrt();
        void toNct();
    }

    interface SettingsView {
        void setToRussian();
        void setToKyrgyz();
    }

    interface TestResultContract {
        void showMessage(String msg);
    }

    interface TestResultPresenter {
        void sendTestResult(String id, long score, Context context);
        void sendTestResultNct(Context context,
                               String userId,
                               String subjectName,
                               String subjectGrade,
                               String subjectVar,
                               long correct,
                               long incorrect);

    }

    interface MainContract {
        void showProgressBar();
        void hideProgressBar();
    }
}
