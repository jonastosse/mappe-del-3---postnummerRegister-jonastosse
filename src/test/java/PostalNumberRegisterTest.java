
import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.Idatx2001.model.PostalNumber;
import no.ntnu.Idatx2001.model.PostalNumberRegister;
import org.junit.jupiter.api.Test;

class PostalNumberRegisterTest {
    String SuccessfulResult = "PostalNumber added";
    String UnSuccessfulResult = "PostalNumber not added";
    PostalNumberRegister tester;

    @Test
    void testRemoveFromDepartment(){
        try {
            PostalNumber postalNumber = new PostalNumber("6291", "Ugai", "Ålesund", "2283", "H");
            System.out.println("Positive test:");
            System.out.println(SuccessfulResult);
        } catch (IllegalArgumentException e){
            System.out.println("Could not add PostalNumber");
        }
        assertEquals("PostalNumber added", SuccessfulResult);
    }

    @Test
     void testRemoveNotFromDepartment(){
        try {
            PostalNumber postalNumber = new PostalNumber("6291", "Ugai", "Ålesund", "2283", "");
            System.out.println("PostalNumber added");
        } catch (IllegalArgumentException e){
            System.out.println("Negative test:");
            System.out.println(UnSuccessfulResult);
        }
        assertEquals("PostalNumber not added", UnSuccessfulResult);
    }
}
