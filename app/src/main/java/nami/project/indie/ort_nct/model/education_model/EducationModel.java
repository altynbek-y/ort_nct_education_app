package nami.project.indie.ort_nct.model.education_model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "OrtEducation")
public class EducationModel implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("subject_name")
    private String name;

    @SerializedName("translations")
    private TranslationsModel content;

    @SerializedName("date_created")
    private String dateCreated;

    // Setter functions
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(TranslationsModel content) {
        this.content = content;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    // Getter functions
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TranslationsModel getContent() {
        return content;
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
