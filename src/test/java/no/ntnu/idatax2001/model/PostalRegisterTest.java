package no.ntnu.idatax2001.model;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatx2001.model.PostalNumber;
import no.ntnu.idatx2001.model.PostalRegister;
import no.ntnu.idatx2001.model.RemoveException;
import org.junit.jupiter.api.Test;

class PostalRegisterTest {
    String SuccessfulResultAdd = "PostalNumber added";
    String UnSuccessfulResultAdd = "PostalNumber not added";
    String SuccessfulResultRemove = "PostalNumber removed";
    String UnSuccessfulResultRemove = "PostalNumber not removed";

    @Test
    void testAddPostalNumberToRegister(){
        try {
            new PostalNumber("6291", "Ugai", "Ålesund", "2283", "H");
            System.out.println("Positive test:");
            System.out.println(SuccessfulResultAdd);
        } catch (IllegalArgumentException e){
            System.out.println("Could not add PostalNumber");
        }
        assertEquals("PostalNumber added", SuccessfulResultAdd);
    }

    @Test
     void testAddEmptyCategoryPostalNumberToRegister(){
        try {
            new PostalNumber("6291", "Ugai", "Ålesund", "2283", "");
            System.out.println("PostalNumber added");
        } catch (IllegalArgumentException e){
            System.out.println("Negative test:");
            System.out.println(UnSuccessfulResultAdd);
        }
        assertEquals("PostalNumber not added", UnSuccessfulResultAdd);
    }

    @Test
    void testRemovePostalFromRegister(){
        PostalNumber postalNumber = new PostalNumber("6291", "Ugai", "Ålesund", "2283", "G");
        PostalRegister postalRegister = new PostalRegister();
        postalRegister.setPostalNumbers(postalNumber);
        try {
            postalRegister.removePostal(postalNumber);
            System.out.println("PostalNumber removed");
        } catch (RemoveException e){
            System.out.println("Could not remove postalNumber");
        }
        assertEquals("PostalNumber removed", SuccessfulResultRemove);
    }

    @Test
    void testRemovePostalNotInRegister(){
        PostalNumber postalNumber = new PostalNumber("6291", "Ugai", "Ålesund", "2283", "G");
        PostalRegister postalRegister = new PostalRegister();
        try {
            postalRegister.removePostal(postalNumber);
            System.out.println("PostalNumber removed");
        } catch (RemoveException e){
            System.out.println("Negative test:");
            System.out.println(UnSuccessfulResultRemove);
        }
        assertEquals("PostalNumber not removed", UnSuccessfulResultRemove);
    }
}
