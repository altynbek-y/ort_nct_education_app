package nami.project.indie.ort_nct.model.test_model;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*---------------------------SINGLE-ANSWER-MODEL------------------------------------*/
public class Answer implements Serializable
{
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("question")
    @Expose
    private int question;

    @SerializedName("answer")
    @Expose
    private String answer;

    @SerializedName("is_correct")
    @Expose
    private boolean correct;

    // Getter Methods
    public long getId() {
        return this.id;
    }

    public int getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    // Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
/*---------------------------SINGLE-ANSWER-MODEL------------------------------------*/