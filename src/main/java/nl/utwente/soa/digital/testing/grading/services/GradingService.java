package nl.utwente.soa.digital.testing.grading.services;

import integration.studentExam.Examination;
import integration.teacherExam.Correctionmodel;
import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class GradingService {
    @Autowired
    CorrectionService correctionService;

    private static final Map<ExamKey, Examination> ungradedExams = Collections.synchronizedMap(new HashMap<>());
    private static final Map<ExamKey, Examination> gradedExams = Collections.synchronizedMap(new HashMap<>());

    public static Map<ExamKey, Examination> getUngradedExams() {
        return ungradedExams;
    }

    public static Map<ExamKey, Examination> getGrades() {
        return gradedExams;
    }

    public double getGrade(ExamKey examKey) {
        Map.Entry<ExamKey, Examination> entry = gradedExams.entrySet().stream().filter(exam -> exam.getKey().equals(examKey)).findFirst().orElse(null);
        return entry != null ? entry.getValue().getGrade() : null;
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
                    for (Examination.Answers.Entry studentEntry : exam.getAnswers().getEntry()) {
                        Correctionmodel.Questions.Entry teacherEntry = correctionmodel.getQuestions().getEntry().stream()
                                .filter(correctionEntry -> correctionEntry.getKey() == studentEntry.getKey())
                                .findFirst().orElse(null);
                        score += (teacherEntry != null && teacherEntry.getValue().getAnswer().equals(studentEntry.getValue())) ? 1 : 0;
                    }
                    DecimalFormat df = new DecimalFormat("#.#");
                    double grade = score/totalScore*9+1;
                    exam.setGrade(grade);
                    System.out.println(grade);
                    gradedExams.put(examKey, exam);
                }
            } else {
                ungradedExams.put(examKey, exam);
            }
        }
    }
}
