package neobis.project.iman_augustine.ort_nct.model.database_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class QuestionWithAnswers {
    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "answer_a")
    public String answer_a;
    @ColumnInfo(name = "a_is_correct")
    public boolean a_is_correct;

    @ColumnInfo(name = "answer_b")
    public String answer_b;
    @ColumnInfo(name = "b_is_correct")
    public boolean b_is_correct;

    @ColumnInfo(name = "answer_c")
    public String answer_c;
    @ColumnInfo(name = "c_is_correct")
    public boolean c_is_correct;

    @ColumnInfo(name = "answer_d")
    public String answer_d;
    @ColumnInfo(name = "d_is_correct")
    public boolean d_is_correct;
}
