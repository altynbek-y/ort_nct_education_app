package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions",
        foreignKeys = {
        @ForeignKey(
                entity = Language.class,
                parentColumns = "lang_id",
                childColumns = "quest_id"
        )
}
)
public class Question {
    @PrimaryKey
    @ColumnInfo(name = "quest_id")
    public int quest_id;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "is_active")
    public boolean isActive;
}
