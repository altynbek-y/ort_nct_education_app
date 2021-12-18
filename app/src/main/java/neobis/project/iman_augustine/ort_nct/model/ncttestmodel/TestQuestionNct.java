package neobis.project.iman_augustine.ort_nct.model.ncttestmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TestQuestionNct implements Serializable {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("test")
    @Expose
    private long test;

    @SerializedName("payload")
    @Expose
    private String payload;

    @SerializedName("option_set")
    @Expose
    List<TestAnswerNct> options;

    // Getter Methods
    public long getId() {
        return id;
    }

    public long getTest() {
        return test;
    }

    public String getPayload() {
        return payload;
    }

    public List<TestAnswerNct> getOptions() {
        return options;
    }

    // Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setTest(long test) {
        this.test = test;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setOptions(List<TestAnswerNct> options) {
        this.options = options;
    }
}
