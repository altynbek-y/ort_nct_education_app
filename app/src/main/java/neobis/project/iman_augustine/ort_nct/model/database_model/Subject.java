package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity(tableName = "subjects",
        foreignKeys = {@ForeignKey(
                entity = Language.class,
                parentColumns = "id",
                childColumns = "language_id"
        )}
)
public class Subject {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "subject_name")
    @NotNull
    public String subjectName;

    @ColumnInfo(name = "language_id")
    public int langId;

    public Subject() {
        subjectName = null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
