package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "question_answer_choices",
        foreignKeys = {
                @ForeignKey(
                        entity = Question.class,
                        parentColumns = "id",
                        childColumns = "question_id"
                ),
                @ForeignKey(
                        entity = Language.class,
                        parentColumns = "id",
                        childColumns = "language_id"
                )}
)
public class QuestionAnswerChoice {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "answer_choice")
    @NotNull
    private String choiceAnswer;

    @ColumnInfo(name = "is_correct")
    private boolean isCorrect;

    @ColumnInfo(name = "question_id")
    private int questionId;

    @ColumnInfo(name = "language_id")
    private int langId;

    public QuestionAnswerChoice() {
        choiceAnswer = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getChoiceAnswer() {
        return choiceAnswer;
    }

    public void setChoiceAnswer(@NotNull String choiceAnswer) {
        this.choiceAnswer = choiceAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }
}
