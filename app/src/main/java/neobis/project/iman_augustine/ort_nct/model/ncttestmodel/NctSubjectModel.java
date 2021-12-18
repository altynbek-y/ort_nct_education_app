package neobis.project.iman_augustine.ort_nct.model.ncttestmodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NctSubjectModel implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // Getter functions
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // Setter functions
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
