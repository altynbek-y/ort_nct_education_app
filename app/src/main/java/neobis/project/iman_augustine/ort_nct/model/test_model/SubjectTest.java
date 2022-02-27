package  neobis.project.iman_augustine.ort_nct.model.test_model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class SubjectTest implements Serializable
{
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("subject_name")
    @Expose
    private String subjectName;

    @SerializedName("variant")
    @Expose
    private String variant;

    @SerializedName("date_created")
    @Expose
    private String creationDate;

    @SerializedName("questions")
    @Expose
    private List<Question> questions;

    // Getter Methods
    public long getId() {
        return id;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public String getVariant() {
        return variant;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    // Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}
