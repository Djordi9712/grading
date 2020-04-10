package nl.utwente.soa.digital.testing.grading.services;

import integration.studentExam.Examination;
import integration.teacherExam.Correctionmodel;
import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CorrectionService {
    @Autowired
    GradingService gradingService;

    private static final Map<Integer, Correctionmodel> correctionModels = Collections.synchronizedMap(new HashMap<>());

    public void addCorrectionModel(Correctionmodel correction) {
        correctionModels.put((int) correction.getId(), correction);
        for (Map.Entry<ExamKey, Examination> entry : gradingService.getUngradedExams().entrySet()) {
            if (entry.getValue().getExamId() == correction.getId()) {
                gradingService.gradeExamination(entry.getKey(), entry.getValue());
            }
        }
    }

    public Correctionmodel getCorrectionModelByID(int examID) {
        return correctionModels.values().stream().filter(exam -> exam.getId() == examID).findAny().orElse(null);
    }
}
