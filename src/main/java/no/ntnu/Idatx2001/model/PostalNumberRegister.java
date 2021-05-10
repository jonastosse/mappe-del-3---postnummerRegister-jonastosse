package no.ntnu.Idatx2001.model;

import java.util.ArrayList;
import java.util.List;

public class PostalNumberRegister {
    private ArrayList<PostalNumber> postalNumbers;

    /**
     * The postalNumberRegister represent a collection of Postal.
     * This class can hold any object of type Postal or any subclass, like postal.
     */
    public PostalNumberRegister(){
        this.postalNumbers = new ArrayList<>();
    }

    /**
     * Fills register with dummies.
     */
    public void fillRegisterWithDummies(){
        postalNumbers.add(new PostalNumber("6490", "Eide", "2819", "Hustadvika", "G"));
        postalNumbers.add(new PostalNumber("6463", "Tunevik", "2125", "Molde", "G"));
        postalNumbers.add(new PostalNumber("6008", "Vika", "2345", "Ålesund", "G"));
        postalNumbers.add(new PostalNumber("6491", "Oblevai", "2278", "Oslo", "G"));
        postalNumbers.add(new PostalNumber("6494", "Kjørsvika", "2032", "Bergen", "H"));
    }

    /**
     * Returns the list of PostalNumbers.
     *
     * @return the list of PostalNumbers
     */
    public List<PostalNumber> getPostalNumbers() {
        return postalNumbers;
    }

    /**
     * Add a postalNumber to the register.
     *
     * @param postalNumber the postalNumber to be added
     */
    public void setPostalNumbers(PostalNumber postalNumber) {
        this.postalNumbers.add(postalNumber);
    }

    /**
     * Removes a postalNumber from the register.
     *
     * @param postalNumber the postalNumber to be removed
     */
    public void removePostalNumber(PostalNumber postalNumber){
        this.postalNumbers.remove(postalNumber);
    }
}
