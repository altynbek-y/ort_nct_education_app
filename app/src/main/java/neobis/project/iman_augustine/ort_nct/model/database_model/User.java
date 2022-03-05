package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "user"
        /*,
        foreignKeys =
                {
                        @ForeignKey
                                (
                                    entity = Language.class,
                                    parentColumns = "language",
                                    childColumns = "language",
                                    onDelete = ForeignKey.CASCADE
                                )
                }*/
)
public class User {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "user_name")
    public String uName;
}
