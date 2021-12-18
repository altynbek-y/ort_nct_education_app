package neobis.project.iman_augustine.ort_nct.network;


import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.educationmodel.EducationModel;
import neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;
import neobis.project.iman_augustine.ort_nct.model.usermodel.UserData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrtDao {
    //------------------------------------AUTHORIZATION-API----------------------------------------
   /* @GET("user/{user_id}")
    Call<Boolean> getUser(@Path("user_id") String id);

    // Creates a user with associated information
    @POST("user")
    Call<Void> createUser(@Body UserData userData);*/
    /*------------------------------------------ORT-API------------------------------------------*/
    // Fetches list of educational material for each subject
    @GET("/api/study_materials")
    Call<List<EducationModel>> getOrtEducation(@Query("lang") String language);

    // Fetches list of educational material for a subject with a unique id
    @GET("/api/study_materials/{id}")
    Call<EducationModel> getOrtEducationFor(@Path("id") int id);

    // Fetches list of test for each subject in training mode
    @GET("/main/tests")
    Call<List<SubjectTest>> getTestsList(@Query("lang") String language);

    @GET("/main/tests/{id}")
    Call<SubjectTest> getTestFor(@Path("id") int id);

}
