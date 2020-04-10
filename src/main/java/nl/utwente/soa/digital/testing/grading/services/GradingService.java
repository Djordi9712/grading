package nl.utwente.soa.digital.testing.grading.services;

import nl.utwente.soa.digital.testing.grading.integration.studentExam.Examination;
import nl.utwente.soa.digital.testing.grading.integration.teacherExam.Correctionmodel;
import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class GradingService {
    @Autowired
    CorrectionService correctionService;

    private static final Map<ExamKey, Examination> ungradedExams = Collections.synchronizedMap(new HashMap<>());
    private static final Map<ExamKey, Examination> gradedExams = Collections.synchronizedMap(new HashMap<>());

    /*Get all the ungraded exams*/
    public static Map<ExamKey, Examination> getUngradedExams() {
        return ungradedExams;
    }

    public double getGrade(ExamKey examKey) {
        Map.Entry<ExamKey, Examination> entry = gradedExams.entrySet().stream().filter(exam -> exam.getKey().equals(examKey)).findFirst()
                .orElseThrow(() -> new ResourceAccessException("The exam has not been found"));
        return entry.getValue().getGrade();
    }

    /*Delete an exam from the ungraded exams*/
    public void removeUngradedExam(ExamKey examKey) {
        Map.Entry<ExamKey, Examination> exam = ungradedExams.entrySet().stream().filter(entry -> entry.getKey().equals(examKey)).findFirst().orElse(null);
        if (exam != null) {
            getUngradedExams().remove(exam);
        }
}

    public void gradeExamination(ExamKey examKey, Examination exam) {
        /*Check if the student has already handed in the exam */
        if (!(ungradedExams.entrySet().stream().filter(examination -> examination.getKey().equals(examKey)).findAny().isPresent() |
                gradedExams.entrySet().stream().filter(examination -> examination.getKey().equals(examKey)).findAny().isPresent())) {

            /*Retrieve the correction model if available, otherwise put the exam in the list of ungraded exams*/
            Correctionmodel correctionmodel = correctionService.getCorrectionModelByID(exam.getExamId());
            if (correctionmodel != null) {
                int totalScore = correctionmodel.getQuestions().getEntry().size();
                int score = 0;
                if (totalScore != 0) {
                    /*Grade every question*/
                    for (Examination.Answers.Entry studentEntry : exam.getAnswers().getEntry()) {
                        Correctionmodel.Questions.Entry teacherEntry = correctionmodel.getQuestions().getEntry().stream()
                                .filter(correctionEntry -> correctionEntry.getKey() == studentEntry.getKey())
                                .findFirst().orElse(null);
                        score += (teacherEntry != null && teacherEntry.getValue().getAnswer().equals(studentEntry.getValue())) ? 1 : 0;
                    }
                    /*Update the grade of the exam*/
                    DecimalFormat df = new DecimalFormat("#.#");
                    double grade = score/totalScore*9+1;
                    exam.setGrade(grade);
                    gradedExams.put(examKey, exam);
                }
            } else {
                ungradedExams.put(examKey, exam);
            }
        }
    }
}
