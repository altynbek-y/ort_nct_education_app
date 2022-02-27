package  neobis.project.iman_augustine.ort_nct.model.test_model;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestAnswerTypeConverter {
    @TypeConverter
    public static List<Answer> stringToTestAnswerList(String data) {
        if(data==null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Answer>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    @TypeConverter
    public static String TestAnswerListToString(List<Answer> testAnswerList) {
        Gson gson = new Gson();
        return gson.toJson(testAnswerList);
    }
}
