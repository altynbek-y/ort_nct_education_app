package nami.project.indie.ort_nct.model.database_model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import org.jetbrains.annotations.NotNull;

public class QuestionWithAnswers {
    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer_a")
    @NotNull
    private String answer_a;

    @ColumnInfo(name = "a_is_correct")
    private boolean a_is_correct;

    @ColumnInfo(name = "answer_b")
    @NotNull
    private String answer_b;

    @ColumnInfo(name = "b_is_correct")
    private boolean b_is_correct;

    @ColumnInfo(name = "answer_c")
    @NotNull
    private String answer_c;

    @ColumnInfo(name = "c_is_correct")
    private boolean c_is_correct;

    @ColumnInfo(name = "answer_d")
    @NotNull
    private String answer_d;

    @ColumnInfo(name = "d_is_correct")
    private boolean d_is_correct;

    @Ignore
    private boolean isAnswered;

    public String getQuestion() {
        return question;
    }

    @NotNull
    public String getAnswer_a() {
        return answer_a;
    }

    @NotNull
    public String getAnswer_b() {
        return answer_b;
    }

    @NotNull
    public String getAnswer_c() {
        return answer_c;
    }

    @NotNull
    public String getAnswer_d() {
        return answer_d;
    }

    public void setAnswer_a(@NotNull String answer_a) {
        this.answer_a = answer_a;
    }

    public void setAnswer_b(@NotNull String answer_b) {
        this.answer_b = answer_b;
    }

    public void setAnswer_c(@NotNull String answer_c) {
        this.answer_c = answer_c;
    }

    public void setAnswer_d(@NotNull String answer_d) {
        this.answer_d = answer_d;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public boolean isA_is_correct() {
        return a_is_correct;
    }

    public boolean isB_is_correct() {
        return b_is_correct;
    }

    public boolean isC_is_correct() {
        return c_is_correct;
    }

    public boolean isD_is_correct() {
        return d_is_correct;
    }

    public void setA_is_correct(boolean a_is_correct) {
        this.a_is_correct = a_is_correct;
    }

    public void setB_is_correct(boolean b_is_correct) {
        this.b_is_correct = b_is_correct;
    }

    public void setC_is_correct(boolean c_is_correct) {
        this.c_is_correct = c_is_correct;
    }

    public void setD_is_correct(boolean d_is_correct) {
        this.d_is_correct = d_is_correct;
    }
}
