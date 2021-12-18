package  neobis.project.iman_augustine.ort_nct.network;

import neobis.project.iman_augustine.ort_nct.model.aboutnctmodel.AboutNctModel;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.NctTestSubjectInfo;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.SubjectTestNct;
import  neobis.project.iman_augustine.ort_nct.model.testmodel.SubjectTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
/**
 *  NCT Data Access Object interface
 *  Accept-Language: ru, ky
 * */
/*-----------------------------------------NCT-API------------------------------------------------*/
public interface NctDao {
    // Fetches info about nct testing
    @GET("/api/v1/about_nct")
    Call<AboutNctModel> getAboutNct(@Header("Accept-Language") String language);

    // Fetches data about each subject test
    @GET("/api/v1/subject_tests")
    Call<List<NctTestSubjectInfo>> getSubjectTestsInfo(@Header("Accept-Language") String language);

    // Fetches a quiz for a specific subject by its id
    @GET("/api/v1/subject_tests/{id}")
    Call<SubjectTestNct> getSubjectTestFor(@Header("Accept-Language") String language, @Path("id") int id);
}
