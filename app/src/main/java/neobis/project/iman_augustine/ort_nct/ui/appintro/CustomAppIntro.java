package neobis.project.iman_augustine.ort_nct.ui.appintro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroCustomLayoutFragment;


import org.jetbrains.annotations.Nullable;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.ui.main.MainActivity;

public class CustomAppIntro extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.appintro_layout_info_ru));

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.appintro_layout_info_kg));


      /*  addSlide(AppIntroFragment.newInstance(getString(R.string.welcome_title), getString(R.string.mapintro)
                , R.mipmap.ic_launcher_foreground, R.color.colorPrimary));

        addSlide(AppIntroFragment.newInstance(getString(R.string.map_title), getString(R.string.map_description),
                R.mipmap.ic_launcher_foreground, R.color.colorPrimary));

        addSlide(AppIntroFragment.newInstance(getString(R.string.chat_title), getString(R.string.chat_description),
                R.mipmap.ic_launcher_foreground, R.color.colorPrimary));*/

    }

    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(CustomAppIntro.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(CustomAppIntro.this, MainActivity.class));
        finish();
    }
}
