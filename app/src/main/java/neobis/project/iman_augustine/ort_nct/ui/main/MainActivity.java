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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.adapters.CustomViewPager;
import neobis.project.iman_augustine.ort_nct.adapters.FragmentAdapter;
import neobis.project.iman_augustine.ort_nct.dialogs.LoadingDialog;
import neobis.project.iman_augustine.ort_nct.sharedpreference.PreferenceManager;
import neobis.project.iman_augustine.ort_nct.sharedpreference.SharedPreferencesSingleton;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickNavigationViewListener;
import neobis.project.iman_augustine.ort_nct.ui.Contract;
import neobis.project.iman_augustine.ort_nct.ui.aboutus.AboutUsActivity;
import neobis.project.iman_augustine.ort_nct.ui.settings.SettingsActivity;

    /**
     * 
     *
     *
     */

    public class MainActivity extends AppCompatActivity implements Contract.MainContract {

        // Global variables
        private FragmentAdapter fragmentAdapter;
        private CustomViewPager viewPager;
        private BottomNavigationView bottomNav;
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle toggle;
        private LoadingDialog loadingDialog;

        // Shared preferences
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        // On navigation item selected listener in the drawer layout
        private final NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new OnSingleClickNavigationViewListener() {
                    @Override
                    public boolean onSingleClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.choose_ort_test:
                                mDrawerLayout.closeDrawers();
                                Toast.makeText(MainActivity.this,
                                        "Пока недоступно/Азырынча жок", Toast.LENGTH_SHORT)
                                        .show();
                                // startOrtTestActivity();
                                break;
                            case R.id.choose_nct_test:
                                mDrawerLayout.closeDrawers();
                                break;
                            case R.id.settings:
                                startSettingsActivity();
                                break;
                        }
                        return true;
                    }
                };

        // On navigation item selected listener in bottom navigation bar
        private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
                item -> {
                    switch (item.getItemId()) {

                        case R.id.info:
                            viewPager.setCurrentItem(0);
                            Objects.requireNonNull(getSupportActionBar()).setTitle(
                                    bottomNav.getMenu().findItem(R.id.info).getTitle()
                            );
                            break;

                        case R.id.diag_test:
                            viewPager.setCurrentItem(1);
                            Objects.requireNonNull(getSupportActionBar()).setTitle(
                                    bottomNav.getMenu().findItem(R.id.diag_test).getTitle()
                            );
                            break;

                        case R.id.statistics:
                            viewPager.setCurrentItem(2);
                            Objects.requireNonNull(getSupportActionBar()).setTitle(
                                    bottomNav.getMenu().findItem(R.id.statistics).getTitle()
                            );
                            break;
                    }
                    return true;
                };

        // On create
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.drawer_layout);

            try {
                sharedPreferences = PreferenceManager.getMySharedPreferences(this); // Gets shared preferences
                editor = sharedPreferences.edit();                                          // Shared pref editor

                initViews();                                                                // Initializes views
            } catch (NullPointerException error) {
                error.printStackTrace();
            }
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

            // Navigation view
            NavigationView navigationView = findViewById(R.id.navigationView);
            navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

            mDrawerLayout = findViewById(R.id.drawer);
            toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            // Find the view pager that will allow the user to swipe between fragments
            bottomNav = findViewById(R.id.bottom_navigation);
            viewPager = findViewById(R.id.viewPagerAppActivity);

            fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
            fragmentAdapter.addFragment(new InformationFragment());
            fragmentAdapter.addFragment(new SubjectsListFragment());
            fragmentAdapter.addFragment(new StatisticsFragment());

            viewPager.setAdapter(fragmentAdapter);
            viewPager.setCurrentItem(0);

            bottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
            bottomNav.setOnNavigationItemSelectedListener(navListener);

            // Set initial toolbar title
            Objects.requireNonNull(getSupportActionBar()).setTitle(bottomNav.getMenu().findItem(R.id.info).getTitle());
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