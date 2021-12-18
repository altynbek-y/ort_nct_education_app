package neobis.project.iman_augustine.ort_nct.model.ncttestmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.NctSubjectModel;

public class NctTestSubjectInfo implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("subject_id")
    @Expose
    private int subjectId;

    @SerializedName("subject")
    @Expose
    private NctSubjectModel subject;

    @SerializedName("grade")
    @Expose
    private int grade;

    @SerializedName("variant")
    @Expose
    private int variant;

    // Setter functions
    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(NctSubjectModel subject) {
        this.subject = subject;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    // Getter functions
    public int getId() {
        return id;
    }

    public NctSubjectModel getSubject() {
        return subject;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public int getGrade() {
        return grade;
    }

    public int getVariant() {
        return variant;
    }
}
