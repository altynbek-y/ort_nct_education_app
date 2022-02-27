package neobis.project.iman_augustine.ort_nct.model.database_model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Language {
    @PrimaryKey
    @ColumnInfo(name = "lang_id")
    public int lang_id;

    @ColumnInfo(name = "language")
    public String language;
}