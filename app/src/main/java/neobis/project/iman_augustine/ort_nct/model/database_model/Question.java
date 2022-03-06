package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "questions",
        foreignKeys = {
        @ForeignKey(
                entity = Language.class,
                parentColumns = "id",
                childColumns = "language_id"
        ),
        @ForeignKey(
                entity = Subject.class,
                parentColumns = "id",
                childColumns = "subject_id"
        )}
)
public class Question {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "question")
    @NotNull
    public String question;

    @ColumnInfo(name = "is_active")
    public boolean isActive;

    @ColumnInfo(name = "language_id")
    public int langId;

    @ColumnInfo(name = "subject_id")
    public int subjectId;

    public Question() {
        question = null;
    }
}
