package neobis.project.iman_augustine.ort_nct.network;


import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.education_model.EducationModel;
import neobis.project.iman_augustine.ort_nct.model.test_model.SubjectTest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrtDao {
    //------------------------------------AUTHORIZATION-API----------------------------------------
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
