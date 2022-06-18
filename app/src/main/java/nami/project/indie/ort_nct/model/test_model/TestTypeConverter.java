package  nami.project.indie.ort_nct.model.test_model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nami.project.indie.ort_nct.model.database_model.Subject;

public class TestTypeConverter {
    @TypeConverter
    public static List<Subject> stringToTestQuestionList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Subject>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    @TypeConverter
    public static String TestQuestionListToString(List<Subject> testQuestionList) {
        Gson gson = new Gson();
        return gson.toJson(testQuestionList);
    }
}
