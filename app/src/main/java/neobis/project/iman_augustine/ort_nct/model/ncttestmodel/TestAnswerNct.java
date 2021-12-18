package neobis.project.iman_augustine.ort_nct.model.ncttestmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TestAnswerNct implements Serializable {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("question_id")
    @Expose
    private long questionId;

    @SerializedName("is_correct")
    @Expose
    private boolean isCorrect;

    @SerializedName("payload")
    @Expose
    private String payload;

    // Getter Methods
    public long getId() {
        return id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getPayload() {
        return payload;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    // Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}