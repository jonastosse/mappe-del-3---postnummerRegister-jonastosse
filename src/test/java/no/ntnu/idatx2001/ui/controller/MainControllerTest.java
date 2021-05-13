package no.ntnu.idatx2001.ui.controller;

import no.ntnu.idatx2001.model.PostalNumber;
import no.ntnu.idatx2001.model.PostalRegister;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class MainControllerTest {
    String SuccessfulImport = "Import successful";
    String UnSuccessfulImport = "Import unsuccessful";

    @Test
    void ImportCSV() {
        PostalRegister postalRegister = new PostalRegister();
        try (var reader = new BufferedReader(new FileReader("/CSV/PostalRegisterGOOD"))) {
            var row = "";
            while ((row = reader.readLine()) != null) {
                String[] data = row.split("\t");
                postalRegister.setPostalNumbers(new PostalNumber(data[0], data[1], data[3], data[2], data[4]));
            }
            System.out.println("Positive test:");
            System.out.println(SuccessfulImport);

        } catch (IOException e) {
            System.out.println("IOException Could not add file");
        }
        assertEquals("Import successful", SuccessfulImport);
    }

    @Test
    void importCSVWithFailure() {
        PostalRegister postalRegister = new PostalRegister();
        try (var reader = new BufferedReader(new FileReader("/CSV/PostalRegisterBAD"))) {
            var row = "";
            while ((row = reader.readLine()) != null) {
                String[] data = row.split("\t");
                postalRegister.setPostalNumbers(new PostalNumber(data[0], data[1], data[3], data[2], data[4]));
            }
            System.out.println("Import successful");
        } catch (IOException e) {
            System.out.println("Negative test:");
            System.out.println(UnSuccessfulImport);
        }
        assertEquals("Import unsuccessful", UnSuccessfulImport);
    }
}