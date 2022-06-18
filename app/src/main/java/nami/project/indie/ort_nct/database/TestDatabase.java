package nami.project.indie.ort_nct.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import nami.project.indie.ort_nct.model.database_model.*;

@Database(
        entities = {
                        Language.class,
                        QuestionAnswerChoice.class,
                        Question.class,
                        Subject.class,
                        User.class,
                        UserScore.class
                },
        version = 1
)
public abstract class TestDatabase extends RoomDatabase {

    // Migration path definition from version 2 to version 3.
    static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    private static final String DB_NAME = "Test_database";
    public abstract AppDao appDao();
    private static TestDatabase instance;

    public static TestDatabase getInMemoryDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TestDatabase.class, DB_NAME)
                    .createFromAsset("database/test_db.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

