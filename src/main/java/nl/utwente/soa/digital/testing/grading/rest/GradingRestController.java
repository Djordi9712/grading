package nl.utwente.soa.digital.testing.grading.rest;

import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import nl.utwente.soa.digital.testing.grading.services.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

@RestController
public class GradingRestController {
    @Autowired
    GradingService gradingService;

    /*Rest endpoint to retrieve the grade of a student for a particular exam*/
    @GetMapping("/grading/exam/{examId}/student/{studentId}")
    public double getGradedExam(@PathVariable("examId") Integer examId, @PathVariable("studentId") String studentId) throws ResourceAccessException {
        ExamKey examKey = new ExamKey(examId, studentId);
        try {
            return gradingService.getGrade(examKey);
        } catch (ResourceAccessException e) {
            return 0;
        }
    }
}
