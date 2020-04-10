
package nl.utwente.soa.digital.testing.grading.integration.teacherExam;

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
     * Create an instance of {@link Correctionmodel }
     * 
     */
    public Correctionmodel createCorrectionmodel() {
        return new Correctionmodel();
    }

    /**
     * Create an instance of {@link Correctionmodel.Questions }
     * 
     */
    public Correctionmodel.Questions createCorrectionmodelQuestions() {
        return new Correctionmodel.Questions();
    }

    /**
     * Create an instance of {@link Correctionmodel.Questions.Entry }
     * 
     */
    public Correctionmodel.Questions.Entry createCorrectionmodelQuestionsEntry() {
        return new Correctionmodel.Questions.Entry();
    }

    /**
     * Create an instance of {@link Correctionmodel.Questions.Entry.Value }
     * 
     */
    public Correctionmodel.Questions.Entry.Value createCorrectionmodelQuestionsEntryValue() {
        return new Correctionmodel.Questions.Entry.Value();
    }

}
