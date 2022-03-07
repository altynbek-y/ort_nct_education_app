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
    private int id;

    @ColumnInfo(name = "question")
    @NotNull
    private String question;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    @ColumnInfo(name = "language_id")
    private int langId;

    @ColumnInfo(name = "subject_id")
    private int subjectId;

    public Question() {
        question = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NotNull String question) {
        this.question = question;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
