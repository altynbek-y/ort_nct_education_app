package neobis.project.iman_augustine.ort_nct.model.statistics_model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "TestStats")
public class TestStat implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name="testtype")
    private String testType;

    @ColumnInfo(name="subject_name")
    private String subjectName;

    @ColumnInfo(name="subject_id")
    private long subjectId;

    @ColumnInfo(name="grade")
    private int grade;

    @ColumnInfo(name="variant")
    private int variant;

    @ColumnInfo(name="correct")
    private long correct;

    @ColumnInfo(name="incorrect")
    private long incorrect;

    @Ignore
    public TestStat() {}

    public TestStat(String subjectName, int grade, int variant, String testType , long correct, long incorrect ) {
          this.subjectName = subjectName;
          this.grade = grade;
          this.variant = variant;
          this.testType = testType;
          this.correct = correct;
          this.incorrect = incorrect;
    }

    // Getter functions
    public long getId() { return id; }

    public String getTestType() {
        return testType;
    }

    public long getSubjectId() { return subjectId; }

   public int getGrade() { return grade; }

    public String getSubjectName() { return subjectName; }

    public int getVariant() { return variant; }

    public long getCorrect() { return correct; }

    public long getIncorrect() { return incorrect; }

    // Setter functions
    public void setId(long id) { this.id = id; }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public void setSubjectId(long subjectId) { this.subjectId = subjectId; }

    public void setGrade(int grade) { this.grade = grade; }

    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public void setVariant(int variant) { this.variant = variant; }

    public void setCorrect(long correct) { this.correct = correct; }

    public void setIncorrect(long incorrect) { this.incorrect = incorrect; }
}
