
package nl.utwente.soa.digital.testing.grading.integration.studentExam;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nl.utwente.soa.digital.testing.grading.integration package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.utwente.soa.digital.testing.grading.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Examination }
     * 
     */
    public Examination createExamination() {
        return new Examination();
    }

    /**
     * Create an instance of {@link Examination.Answers }
     * 
     */
    public Examination.Answers createExaminationAnswers() {
        return new Examination.Answers();
    }

    /**
     * Create an instance of {@link Examination.Answers.Entry }
     * 
     */
    public Examination.Answers.Entry createExaminationAnswersEntry() {
        return new Examination.Answers.Entry();
    }

}
