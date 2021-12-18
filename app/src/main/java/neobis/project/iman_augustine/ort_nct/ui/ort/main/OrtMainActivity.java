package neobis.project.iman_augustine.ort_nct.ui.ort.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationView;

import neobis.project.iman_augustine.ort_nct.R;

import neobis.project.iman_augustine.ort_nct.adapters.CustomViewPager;
import neobis.project.iman_augustine.ort_nct.adapters.ViewPageAdapter;
import neobis.project.iman_augustine.ort_nct.dialogs.LoadingDialog;
import neobis.project.iman_augustine.ort_nct.sharedpreference.SharedPreferencesSingleton;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickNavigationViewListener;
import neobis.project.iman_augustine.ort_nct.ui.BottomNavigationViewHelper;
import neobis.project.iman_augustine.ort_nct.ui.Contract;
import neobis.project.iman_augustine.ort_nct.ui.aboutus.AboutUsActivity;
import neobis.project.iman_augustine.ort_nct.ui.nct.main.NctMainActivity;
import neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.education.OrtEducationFragment;
import neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_test.OrtTestFragment;
import neobis.project.iman_augustine.ort_nct.ui.ort.ort_fragments.ort_stats.OrtStatisticsFragment;
import neobis.project.iman_augustine.ort_nct.ui.settings.SettingsActivity;

/**
 * OrtTestActivity layout
 * Created by admin on 03/02/20
 */

public class OrtMainActivity extends AppCompatActivity implements Contract.MainContract {
    public static final String ORT_TEST_ACTIVITY = "ort_test_activity";
    /*------------------------------------------------MVVM----------------------------------------------------------------*/
    private final int[] titlesResId = {R.string.learning_material_txt, R.string.practice_test_txt, /*R.string.final_test_txt,*/ R.string.statistics_ort_title};
    /*------------------------------------------------VIEWS,etc-----------------------------------------------------------*/
    private CustomViewPager viewPager;
    private BottomNavigationView bottomNav;
    private MenuItem prevMenuItem;
    private Fragment ortEducationFragment, ortPracticeTestFragment, /*ortFinalTestFragment,*/ ortStatisticsFragment;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LoadingDialog loadingDialog;
    private SharedPreferencesSingleton shared;
    /*--------------------------------------------------------------------------------------------------------------------*/
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            if (prevMenuItem != null) {
                prevMenuItem.setChecked(false);
            } else {
                bottomNav.getMenu().getItem(position).setChecked(false);
            }
            getSupportActionBar().setTitle(titlesResId[position]);
            bottomNav.getMenu().getItem(position).setChecked(true);
            prevMenuItem = bottomNav.getMenu().getItem(position);
            shared.setCurrentFragmentOrt(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

     private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new OnSingleClickNavigationViewListener() {
                @Override
                public boolean onSingleClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.choose_ort_test:
                            mDrawerLayout.closeDrawers();
                            break;
                        case R.id.choose_nct_test:
                            startNctTestActivity();
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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.material:
                            viewPager.setCurrentItem(0, false);
                            break;
                        case R.id.diag_test:
                            viewPager.setCurrentItem(1, false);
                            break;
                        /*case R.id.final_test:
                            viewPager.setCurrentItem(2, false);
                            break;*/
                        case R.id.statistics:
                            viewPager.setCurrentItem(2, false);
                            break;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ort_drawer_layout);
        try {
            shared = SharedPreferencesSingleton.getLocalSharedPreferences(this);
            initViews();
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        setContentView(R.layout.ort_drawer_layout);
        initViews();
        super.onResume();
    }

    private void initViews() {
        // Custom top navigation bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Navigation view
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        navigationView.getMenu().getItem(0).setChecked(true);
        mDrawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.fragment_container);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
       // BottomNavigationViewHelper.disableShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        setUpViewPager(viewPager);
        getSupportActionBar().setTitle(titlesResId[shared.getCurrentFragmentOrt()]);
        // getSupportActionBar().setTitle(titlesResId[0]);
        viewPager.setCurrentItem(shared.getCurrentFragmentOrt());  // Set current fragment
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        ortEducationFragment = new OrtEducationFragment();
        ortPracticeTestFragment = new OrtTestFragment();
       // ortFinalTestFragment = new OrtFinalTestFragment();
        ortStatisticsFragment = new OrtStatisticsFragment();
        viewPageAdapter.addFragment(ortEducationFragment);
        viewPageAdapter.addFragment(ortPracticeTestFragment);
       // viewPageAdapter.addFragment(ortFinalTestFragment);
        viewPageAdapter.addFragment(ortStatisticsFragment);
        viewPager.setAdapter(viewPageAdapter);
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(ORT_TEST_ACTIVITY, true);
        startActivity(intent);
    }

    private void startAboutUsActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void startNctTestActivity() {
        SharedPreferencesSingleton.getLocalSharedPreferences(this).setNctTestLaunchNextTime();
        startActivity(new Intent(this, NctMainActivity.class));
        finish();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void showProgressBar() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
    }

    @Override
    public void hideProgressBar() {
        loadingDialog.dismissDialog();
    }

    /**
     * Prevents from being redirected to any previous activity
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
