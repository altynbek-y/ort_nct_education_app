package neobis.project.iman_augustine.ort_nct.model.database_model;

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
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "score")
    public double score;

    @ColumnInfo(name = "test_date")
    @NotNull
    public String dateTest;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "subject_id")
    public int subjectId;

    public UserScore() {
        dateTest = null;
    }
}
