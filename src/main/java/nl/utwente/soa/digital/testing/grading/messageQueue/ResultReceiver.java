package nl.utwente.soa.digital.testing.grading.messageQueue;

import integration.studentExam.Examination;
import integration.teacherExam.Correctionmodel;
import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import nl.utwente.soa.digital.testing.grading.services.CorrectionService;
import nl.utwente.soa.digital.testing.grading.services.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ResultReceiver {
    @Autowired GradingService gradingService;
    @Autowired CorrectionService correctionService;

    @JmsListener(destination = "${queue.exam}")
    public void pullStudentExam(Examination examination) {
        ExamKey examKey = new ExamKey((int) examination.getExamId(), examination.getStudentId());
        gradingService.gradeExamination(examKey, examination);
    }

    @JmsListener(destination = "${queue.correction}")
    public void pullTeacherExam(Correctionmodel correction) {
        correctionService.addCorrectionModel(correction);
    }

}