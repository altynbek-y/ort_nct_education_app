package neobis.project.iman_augustine.ort_nct.model.ncttestmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.NctSubjectModel;
import neobis.project.iman_augustine.ort_nct.model.testmodel.Question;

public class SubjectTestNct implements Serializable
{
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("subject_id")
    @Expose
    private long subjectId;

    @SerializedName("subject")
    @Expose
    private NctSubjectModel subject;

    @SerializedName("grade")
    @Expose
    private int grade;

    @SerializedName("variant")
    @Expose
    private int variant;

    @SerializedName("question_set")
    @Expose
    private List<TestQuestionNct> questions;

    // Getter Methods
    public long getId() {
        return id;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public NctSubjectModel getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public int getVariant() {
        return variant;
    }

    public List<TestQuestionNct> getQuestions() {
        return questions;
    }

    // Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubject(NctSubjectModel subject) {
        this.subject = subject;
    }

    public void setQuestions(List<TestQuestionNct> questions) {
        this.questions = questions;
    }
}
