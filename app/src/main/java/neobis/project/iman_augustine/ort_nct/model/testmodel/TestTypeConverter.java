package  neobis.project.iman_augustine.ort_nct.model.testmodel;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestTypeConverter {
    @TypeConverter
    public static List<Question> stringToTestQuestionList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Question>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    @TypeConverter
    public static String TestQuestionListToString(List<Question> testQuestionList) {
        Gson gson = new Gson();
        return gson.toJson(testQuestionList);
    }
}
