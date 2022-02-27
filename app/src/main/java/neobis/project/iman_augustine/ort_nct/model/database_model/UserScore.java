package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class UserScore {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "score")
    public float score;

    @ColumnInfo(name = "test_date")
    public String dateTest;
}
