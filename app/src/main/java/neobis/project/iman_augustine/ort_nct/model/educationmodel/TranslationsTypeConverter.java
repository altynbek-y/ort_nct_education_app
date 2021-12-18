package  neobis.project.iman_augustine.ort_nct.model.educationmodel;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class TranslationsTypeConverter {
    @TypeConverter
    public static TranslationsModel stringToSubjectModel(String data) {
        if (data == null) {
            return null;
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<TranslationsModel>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    @TypeConverter
    public static String tutorialListToString(TranslationsModel subjectModels) {
        Gson gson = new Gson();
        return gson.toJson(subjectModels);
    }
}
