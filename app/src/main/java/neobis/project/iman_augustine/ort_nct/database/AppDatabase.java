package neobis.project.iman_augustine.ort_nct.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import neobis.project.iman_augustine.ort_nct.model.statistics_model.TestStat;
import neobis.project.iman_augustine.ort_nct.model.test_model.TestAnswerTypeConverter;
import neobis.project.iman_augustine.ort_nct.model.test_model.TestTypeConverter;

@Database(entities = { TestStat.class}, version = 3, exportSchema = false)
@TypeConverters({ TestTypeConverter.class, TestAnswerTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String APP_NAME = "app_database";

    public abstract AppDao appDao();

    private static AppDatabase instance;

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, APP_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

