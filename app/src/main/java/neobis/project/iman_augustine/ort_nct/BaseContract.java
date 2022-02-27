package  neobis.project.iman_augustine.ort_nct;


public interface BaseContract {
    interface BaseSetUp {
        void saveUserId();

        void startTestActivity();

        void startAuthorization();

        void startRefreshInternetActivity();

        void startFailureActivity();

        boolean isConnected();

        void recreateBaseActivity();

        void launchMainActivity();

        void showError();

        void logMessage(String msg);
    }

    interface BasePresenter {
    }
}
