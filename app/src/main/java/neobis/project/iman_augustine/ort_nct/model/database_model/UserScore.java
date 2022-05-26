package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(tableName = "user_scores",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id"
                ),
                @ForeignKey(
                        entity = Subject.class,
                        parentColumns = "id",
                        childColumns = "subject_id"
                )}
)
public class UserScore {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "score")
    private double score;

    @ColumnInfo(name = "test_date")
    @NotNull
    private String dateTest;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "subject_id")
    private int subjectId;

    public UserScore() {
        dateTest = null;
    }

    public UserScore(int subject_id, double score, @NonNull String test_date) {
        this.subjectId = subject_id;
        this.score = score;
        this.dateTest = test_date;
        this.userId = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @NotNull
    public String getDateTest() {
        return dateTest;
    }

    public void setDateTest(@NotNull String dateTest) {
        this.dateTest = dateTest;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
