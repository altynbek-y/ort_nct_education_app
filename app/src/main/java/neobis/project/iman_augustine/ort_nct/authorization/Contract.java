package neobis.project.iman_augustine.ort_nct.authorization;

public interface Contract {

    interface LanguageView {
        void setToRussian();
        void setToKyrgyz();
        void startRefreshNetActivity();
    }

    interface AgeView {
        void proceed();
    }

    interface ClassView {
        void proceed();
    }

    interface RegionView {
        void showError();
        void toastMessage(int res_id);
        void proceed();
        void finishSetUp();
        void disableButton();
        void showProgressBar();
        void hideProgressBar();
    }
}
