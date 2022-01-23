package neobis.project.iman_augustine.ort_nct.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import neobis.project.iman_augustine.ort_nct.model.Language;
import neobis.project.iman_augustine.ort_nct.model.Question;
import neobis.project.iman_augustine.ort_nct.model.QuestionAnswerChoice;
import neobis.project.iman_augustine.ort_nct.model.Subject;
import neobis.project.iman_augustine.ort_nct.model.User;
import neobis.project.iman_augustine.ort_nct.model.UserScore;
import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;
import neobis.project.iman_augustine.ort_nct.model.testmodel.TestAnswerTypeConverter;
import neobis.project.iman_augustine.ort_nct.model.testmodel.TestTypeConverter;

@Database(
        entities = {
                        Language.class,
                        Question.class,
                        QuestionAnswerChoice.class,
                        Subject.class,
                        User.class,
                        UserScore.class
                },
        version = 3,
        exportSchema = false
)
public abstract class TestDatabase extends RoomDatabase {
    private static final String APP_NAME = "test_database";

    public abstract AppDao TestDatabase();

    private static TestDatabase instance;

    public static TestDatabase getInMemoryDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TestDatabase.class, APP_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

