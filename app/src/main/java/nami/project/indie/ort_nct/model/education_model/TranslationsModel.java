package  nami.project.indie.ort_nct.model.education_model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class TranslationsModel {
    @PrimaryKey
    private long id;

    @SerializedName("ru")
    private RussianTranslation russian;

    @SerializedName("ky")
    private KyrgyzTranslation kyrgyz;

    // Getter functions
    public long getId() {
        return id;
    }

    public RussianTranslation getRussian() {
        return russian;
    }

    public KyrgyzTranslation getKyrgyz() {
        return kyrgyz;
    }
    // Setter functions
    public void setId(long id) {
        this.id = id;
    }

    public void setRussian(RussianTranslation russian) {
        this.russian = russian;
    }

    public void setKyrgyz(KyrgyzTranslation kyrgyz) {
        this.kyrgyz = kyrgyz;
    }

    // Kyrgyz translation
    public class KyrgyzTranslation {
        @SerializedName("content")
        private String content;

        public String getContent() {
            return content;
        }
    }
    // Russian translation
    public class RussianTranslation {
        @SerializedName("content")
        private String content;

        public String getContent() {
            return content;
        }
    }
}
