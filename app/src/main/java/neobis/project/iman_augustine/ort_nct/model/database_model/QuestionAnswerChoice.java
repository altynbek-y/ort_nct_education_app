package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_answer_choices")
public class QuestionAnswerChoice {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "answer_choice")
    public String choiceAnswer;

    @ColumnInfo(name = "is_correct")
    public boolean isCorrect;
}
