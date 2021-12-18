package  neobis.project.iman_augustine.ort_nct.model.testmodel;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.educationmodel.TranslationsModel;

public class Question implements Serializable
{
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("translations")
    @Expose
    private TranslationsImageModel translation;

    @SerializedName("answers")
    @Expose
    List<Answer> answers = new ArrayList<>();

    // Getter Methods
    public long getId() {
        return this.id;
    }

    public int getNumber() {
        return number;
    }

    public TranslationsImageModel getTranslation() {
        return translation;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    // Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTranslation(TranslationsImageModel translation) {
        this.translation = translation;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}