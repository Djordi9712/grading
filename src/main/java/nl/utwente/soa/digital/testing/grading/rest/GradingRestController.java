package nl.utwente.soa.digital.testing.grading.rest;

import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import nl.utwente.soa.digital.testing.grading.services.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradingRestController {
    @Autowired
    GradingService gradingService;

    @GetMapping("/grading/exam/{examId}/student/{studentId}")
    public double getGradedExam(@PathVariable("examId") Integer examId, @PathVariable("studentId") String studentId) {
        ExamKey examKey = new ExamKey(examId, studentId);
        return gradingService.getGrade(examKey);
    }
}
