package neobis.project.iman_augustine.ort_nct.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subject {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "subject_name")
    public String subjectName;

    @ColumnInfo(name = "variant")
    public int variant;

    @ColumnInfo(name = "type")
    public String type;
}
