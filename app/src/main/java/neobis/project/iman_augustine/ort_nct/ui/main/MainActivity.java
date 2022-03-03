package neobis.project.iman_augustine.ort_nct.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.CustomViewPager;
import neobis.project.iman_augustine.ort_nct.dialogs.LoadingDialog;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;
import neobis.project.iman_augustine.ort_nct.sharedpreference.SharedPreferencesSingleton;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickNavigationViewListener;
import neobis.project.iman_augustine.ort_nct.ui.Contract;
import neobis.project.iman_augustine.ort_nct.ui.aboutus.AboutUsActivity;
import neobis.project.iman_augustine.ort_nct.ui.settings.SettingsActivity;

    /**
     * @author Nami Augustine
     *
     *
     */

    public class MainActivity extends AppCompatActivity implements Contract.MainContract {
        // Global variables
        public static final String NCT_TEST_ACTIVITY = "nct_test_activity";
        private final int[] titlesResId = {R.string.information, R.string.practice_test, R.string.statistics_title};
        private CustomViewPager viewPager;
        private MenuItem prevMenuItem;
        private final InformationFragment informationFragment = new InformationFragment();
        private final TestFragment testFragment = new TestFragment();
        private final StatisticsFragment statisticsFragment = new StatisticsFragment();
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle toggle;
        private LoadingDialog loadingDialog;
        private SharedPreferencesSingleton shared;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        // On navigation item selected listener in the drawer layout
        private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new OnSingleClickNavigationViewListener() {
                    @Override
                    public boolean onSingleClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.choose_ort_test:
                                mDrawerLayout.closeDrawers();
                                Toast.makeText(MainActivity.this, "Пока недоступно/Азырынча жок", Toast.LENGTH_SHORT).show();
                                // startOrtTestActivity();
                                break;
                            case R.id.choose_nct_test:
                                mDrawerLayout.closeDrawers();
                                break;
                            case R.id.about_us:
                                startAboutUsActivity();
                                break;
                            case R.id.settings:
                                startSettingsActivity();
                                break;
                        }
                        return true;
                    }
                };
        // On navigation item selected listener in bottom navigation bar
        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            // About test information
                            case R.id.info:
                                openFragment(informationFragment); // Opens fragment
                                getSupportActionBar().setTitle(titlesResId[0]); // Sets title
                                break;
                            // Test fragment
                            case R.id.diag_test:
                                openFragment(testFragment); // Opens fragment
                                getSupportActionBar().setTitle(titlesResId[1]); // Sets title
                                break;
                            // Test statistics fragment
                            case R.id.statistics:
                                openFragment(statisticsFragment); // Opens fragment
                                getSupportActionBar().setTitle(titlesResId[2]); // Sets title
                                break;

                        }
                        return true;
                    }
                };

        // On creation
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.drawer_layout);
            try {
                // shared = SharedPreferencesSingleton.getLocalSharedPreferences(this); // Shared preferences
                sharedPreferences = PreferenceManager.getMySharedPreferences(this); // Gets shared preferences
                editor = sharedPreferences.edit(); // Shared pref editor
                initViews(); // Initializes views
            } catch (NullPointerException error) {
                error.printStackTrace();
            }
        }

        // Opens the given fragment
        private void openFragment(Fragment fragment) {
            getSupportFragmentManager().beginTransaction() // Begins transaction
                    .replace(R.id.container, fragment) // FrameLayout with id container gets replaced with another one
                    .commit();
        }

        // On resume
        @Override
        protected void onResume() {
            super.onResume();
            if(sharedPreferences.getBoolean("locale_changed", false)) {
                editor.remove("locale_changed");
                editor.putBoolean("locale_changed", false);
                editor.apply();
                startActivity(getIntent());
                finish();
            }
        }

        // Initialization of views
        private void initViews() {
            // Custom top navigation bar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            // FrameLayout
            FrameLayout fragmentContainer = findViewById(R.id.container);
            // Navigation view
            NavigationView navigationView = findViewById(R.id.navigationView);
            navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
            // navigationView.getMenu().getItem(1).setChecked(true);
            mDrawerLayout = findViewById(R.id.drawer);
            toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            // Find the view pager that will allow the user to swipe between fragments
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            bottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
            bottomNav.setOnNavigationItemSelectedListener(navListener);
            // Initial state
            openFragment(informationFragment); // Opens inital fragment
            Objects.requireNonNull(getSupportActionBar()).setTitle(titlesResId[0]); // Sets toolbar title
        }

        // On options item selected
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        // Opens settings activity
        private void startSettingsActivity() {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        // Opens about us activity
        private void startAboutUsActivity() {
            startActivity(new Intent(this, AboutUsActivity.class));
        }

        // On post creation
        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            toggle.syncState();
        }

        // On configuration changed
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            toggle.onConfigurationChanged(newConfig);
        }

        // Shows progress bar
        @Override
        public void showProgressBar() {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.startLoadingDialog();
        }

        // Hides progress bar
        @Override
        public void hideProgressBar() {
            loadingDialog.dismissDialog();
        }

        // Prevents from being redirected to the previous activity
        @Override
        public void onBackPressed() {
            moveTaskToBack(true);
        }
    }
