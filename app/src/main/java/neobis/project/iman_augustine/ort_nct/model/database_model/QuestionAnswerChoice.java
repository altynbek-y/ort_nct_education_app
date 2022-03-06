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
    public int id;

    @ColumnInfo(name = "answer_choice")
    @NotNull
    public String choiceAnswer;

    @ColumnInfo(name = "is_correct")
    public boolean isCorrect;

    @ColumnInfo(name = "question_id")
    public int questionId;

    @ColumnInfo(name = "language_id")
    public int langId;

    public QuestionAnswerChoice() {
        choiceAnswer = null;
    }
}
