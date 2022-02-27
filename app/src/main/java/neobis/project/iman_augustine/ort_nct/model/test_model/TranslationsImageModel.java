package neobis.project.iman_augustine.ort_nct.model.test_model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class TranslationsImageModel implements Serializable {
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
    public class KyrgyzTranslation implements Serializable{
        @SerializedName("image")
        private String imageUrl;

        @SerializedName("image_height")
        private int height;

        @SerializedName("image_width")
        private int width;

        public String getImageUrl() {
            return imageUrl;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
    // Russian translation
    public class RussianTranslation implements Serializable{
        @SerializedName("image")
        private String imageUrl;

        @SerializedName("image_height")
        private int height;

        @SerializedName("image_width")
        private int width;

        public String getImageUrl() {
            return imageUrl;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
}
