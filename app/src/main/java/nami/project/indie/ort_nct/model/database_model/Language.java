package nami.project.indie.ort_nct.model.database_model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "languages")
public class Language {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int lang_id;

    @ColumnInfo(name = "language")
    private String language;

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}