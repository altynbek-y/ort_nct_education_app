package neobis.project.iman_augustine.ort_nct.model.educationmodel;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EducationlTypeConverter {
    @TypeConverter
    public static List<EducationModel> stringToTutorialList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<EducationModel>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    @TypeConverter
    public static String tutorialListToString(List<EducationModel> tutorialList) {
        Gson gson = new Gson();
        return gson.toJson(tutorialList);
    }
}
