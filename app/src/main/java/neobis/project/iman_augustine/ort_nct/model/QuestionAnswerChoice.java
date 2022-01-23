package neobis.project.iman_augustine.ort_nct.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionAnswerChoice {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "answer_choice")
    public String choiceAnswer;

    @ColumnInfo(name = "is_correct")
    public boolean isCorrect;
}
