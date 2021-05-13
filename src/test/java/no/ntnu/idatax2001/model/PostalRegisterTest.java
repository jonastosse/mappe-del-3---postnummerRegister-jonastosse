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

    /**
     * A test hoping for positive result.
     * Tests the adding of a postalNumber:
     *
     * Adds the postalNumber.
     *
     * If unsuccessful adding: "Could not add PostalNumber"
     *
     * If successful adding: "PostalNumber added"
     * <code>SuccessfulResultAdd</code>
     */
    @Test
    void testAddPostalNumberToRegister(){
        try {
            new PostalNumber("6291", "Kjørsvika", "Ålesund", "2283", "H");
            System.out.println("Positive test:");
            System.out.println(SuccessfulResultAdd);
        } catch (IllegalArgumentException e){
            System.out.println("Could not add PostalNumber");
        }
        assertEquals("PostalNumber added", SuccessfulResultAdd);
    }

    /**
     * A test hoping for negative result.
     * Tests the adding of a postalNumber with a empty string:
     *
     * Adds the postalNumber.
     *
     * If successful adding: "PostalNumber added"
     *
     * If unsuccessful adding: "PostalNumber not added"
     * <code>UnSuccessfulResultAdd</code>
     */
    @Test
     void testAddEmptyCategoryPostalNumberToRegister(){
        try {
            new PostalNumber("6291", "Kjørsvika", "Ålesund", "2283", "");
            System.out.println("PostalNumber added");
        } catch (IllegalArgumentException e){
            System.out.println("Negative test:");
            System.out.println(UnSuccessfulResultAdd);
        }
        assertEquals("PostalNumber not added", UnSuccessfulResultAdd);
    }

    /**
     * A test hoping for positive result.
     * Tests the removal of a postalNumber in the register:
     *
     * Adds a postalNumber then creates a register.
     * Adds the postalNumber to the register.
     * Removes the postalNumber with the registers removal method.
     *
     * If unsuccessful removal: "Could not remove postalNumber"
     *
     * If successful removal: "PostalNumber removed"
     * <code>SuccessfulResultRemove</code>
     */
    @Test
    void testRemovePostalFromRegister(){
        PostalNumber postalNumber = new PostalNumber("6291", "Kjørsvika", "Ålesund", "2283", "G");
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

    /**
     * A test hoping for negative result.
     * Tests the removal of a postalNumber not in the register:
     *
     * Adds a postalNumber then creates a register.
     * Removes the postalNumber with the registers removal method.
     *
     * If successful removal: "PostalNumber removed"
     *
     * If unsuccessful removal: "PostalNumber not removed"
     * <code>UnSuccessfulResultRemove</code>
     */
    @Test
    void testRemovePostalNotInRegister(){
        PostalNumber postalNumber = new PostalNumber("6291", "Kjørsvika", "Ålesund", "2283", "G");
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
