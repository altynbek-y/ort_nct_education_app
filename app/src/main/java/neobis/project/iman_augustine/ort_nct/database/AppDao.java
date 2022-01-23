package  neobis.project.iman_augustine.ort_nct.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import neobis.project.iman_augustine.ort_nct.model.statmodel.TestStat;
import retrofit2.http.Query;

import java.util.List;

@Dao
public interface AppDao {
    //-----------------------------------TEST-RESULT-NCT----------------------------------
/*    @Delete
    void deleteTestStatNct(TestStat testResult); // Deletes an object from database*/

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTestStatsNct(List<TestStat> testResultNct);*/

/*    @Insert
    void insertTestResultNct(TestStat testResult);*/

/*    @androidx.room.Query("select * from TestStats where testtype='nct'")
    List<TestStat> getAllTestStatsNct();*/

/*    @androidx.room.Query("delete from TestStats where testtype=='nct'")
    void deleteAllTestResultsNct();*/
    //-----------------------------------TEST-RESULT-ORT----------------------------------
/*
    @Delete
    void deleteTestStatOrt(TestStat testStatOrt);
*/

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTestStatsOrt(List<TestStat> testStatsOrt);

    @Insert
    void insertTestResultOrt(TestStat testResultOrt);*/

/*
    @androidx.room.Query("select * from TestStats where testtype='ort'")
    List<TestStat> getAllTestStatsOrt();

    @androidx.room.Query("delete from TestStats where testtype='ort'")
    void deleteAllTestStatstOrt();
*/



    //-----------------------------------ORT-EDUCATION------------------------------------
   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrtEducation(List<EducationModel> educations);*/

   /* @Query("SELECT * FROM OrtEducation")
    List<EducationModel> getOrtEducation();

    @Query("DELETE FROM OrtEducation")
    void deleteOrtEducation();*/
    //-----------------------------------ORT-PRACTICE-TEST--------------------------------
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrtSubjectPracticeTest(List<SubjectTest> testList);

    @Query("SELECT * FROM SubjectTest WHERE testType='ORT_COMMON'")
    List<SubjectTest> getOrtSubjectPracticeTest();

    @Query("DELETE FROM SubjectTest WHERE testType='ORT_COMMON'")
    void deleteOrtSubjectPracticeTest();
    //-----------------------------------ORT-FINAL-TEST-----------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrtFinalSubjectTest(List<SubjectTest> testList);

    @Query("SELECT * FROM SubjectTests WHERE testType='ORT_BASIC'")
    List<SubjectTest> getOrtFinalSubjectTest();

    @Query("DELETE FROM SubjectTests")
    void deleteOrtFinalSubjectTest();
    //------------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrtFinalAdditionalSubjectTest(List<SubjectTest> testList);

    @Query("SELECT * FROM SubjectTests WHERE testType='ORT_ADDITIONAL'")
    List<SubjectTest> getOrtFinalAdditionalSubjectTest();

    @Query("DELETE FROM SubjectTests WHERE testType='ORT_ADDITIONAL'")
    void deleteOrtFinalAdditionalSubjectTest();*/
}
