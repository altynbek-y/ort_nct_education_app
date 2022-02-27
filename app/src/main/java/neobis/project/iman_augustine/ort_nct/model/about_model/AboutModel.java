package neobis.project.iman_augustine.ort_nct.model.about_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AboutModel implements Serializable {
    @SerializedName("payload")
    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
