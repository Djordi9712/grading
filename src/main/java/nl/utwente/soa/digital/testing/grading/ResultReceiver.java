package nl.utwente.soa.digital.testing.grading;
import integration.Examination;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ResultReceiver {

    @JmsListener(destination = "${queue.exam}")
    public void receiveMessage(Examination examination) {
        for (Examination.Answers.Entry answers : examination.getAnswers().getEntry()) {
            System.out.println("Key: " + answers.getKey());
            System.out.println("Value: " + answers.getValue());
        }
    }
}