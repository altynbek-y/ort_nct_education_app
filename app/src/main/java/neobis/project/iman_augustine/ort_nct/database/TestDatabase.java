package neobis.project.iman_augustine.ort_nct.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import neobis.project.iman_augustine.ort_nct.model.database_model.Language;
import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionAnswerChoice;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.model.database_model.User;
import neobis.project.iman_augustine.ort_nct.model.database_model.UserScore;

@Database(
        entities = {
                        Language.class,
                        Question.class,
                        QuestionAnswerChoice.class,
                        Subject.class,
                        User.class,
                        UserScore.class
                },
        version = 4,
        exportSchema = false
)
public abstract class TestDatabase extends RoomDatabase {

    private static final String DB_NAME = "Test_database";

    public abstract AppDao appDao();

    private static TestDatabase instance;

    public static TestDatabase getInMemoryDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TestDatabase.class, DB_NAME)
                    .createFromAsset("database/test_db.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

