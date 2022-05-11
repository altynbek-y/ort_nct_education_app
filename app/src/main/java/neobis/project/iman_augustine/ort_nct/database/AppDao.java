package  neobis.project.iman_augustine.ort_nct.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import neobis.project.iman_augustine.ort_nct.model.database_model.Language;
import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionAnswerChoice;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionWithAnswers;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.model.database_model.UserScore;

@Dao
public interface AppDao {

   // Get all from subjects table
   @Query("SELECT * FROM subjects")
   List<Subject> getListOfSubjects();

   // Get all questions for a particular subject
   @Query("SELECT * FROM questions WHERE subject_id = :subject_id")
   List<Question> getListOfQuestionsListForSubject(int subject_id);

   // Get all answer choices for a particular question
   @Query("SELECT * FROM question_answer_choices WHERE question_id = :question_id")
   List<QuestionAnswerChoice> getListOfAnswersForQuestion(int question_id);

   // Get all answer choices for a particular subject
   @Query("SELECT * FROM question_answer_choices " +
           "WHERE question_id IN (SELECT id FROM questions WHERE subject_id = :subject_id)")
   List<QuestionAnswerChoice> getListOfAnswersForSubject(int subject_id);

   // Get list of questions with four answers each
   @Query("SELECT DISTINCT q.question question, " +
           "a.answer_choice answer_a, a.is_correct a_is_correct, " +
           "b.answer_choice answer_b, b.is_correct b_is_correct, " +
           "c.answer_choice answer_c, c.is_correct c_is_correct, " +
           "d.answer_choice answer_d, d.is_correct d_is_correct " +
           "FROM questions q " +
           "JOIN question_answer_choices a " +
           "ON a.question_id = q.id AND a.id % 4 = 1 " +
           "JOIN question_answer_choices b " +
           "ON b.question_id = q.id AND b.id % 4 = 2 " +
           "JOIN question_answer_choices c " +
           "on c.question_id = q.id AND c.id % 4 = 3 " +
           "JOIN question_answer_choices d " +
           "ON d.question_id = q.id AND d.id % 4 = 0 " +
           "WHERE q.subject_id = :subject_id ")

   List<QuestionWithAnswers> getListOfQuestionsWithAnswers(int subject_id);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertTestScore(UserScore score);

   @Query("SELECT * FROM user_scores")
   List<UserScore> getListOfScores();

   @Delete
   void deleteUserScore(UserScore score);















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
