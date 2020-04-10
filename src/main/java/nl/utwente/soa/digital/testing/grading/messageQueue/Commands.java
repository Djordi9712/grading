package nl.utwente.soa.digital.testing.grading.messageQueue;

import nl.utwente.soa.digital.testing.grading.domain.ExamKey;
import nl.utwente.soa.digital.testing.grading.services.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Commands {
    @Autowired
    GradingService gradingService;

    @ShellMethod("Multiply two numbers")
    public void sendexam(Integer examid, String studentid) {
        System.out.println(examid);
        System.out.println(studentid);
        ExamKey key = new ExamKey(examid, studentid);
        System.out.println(gradingService.getGrade(key));
    }
}
