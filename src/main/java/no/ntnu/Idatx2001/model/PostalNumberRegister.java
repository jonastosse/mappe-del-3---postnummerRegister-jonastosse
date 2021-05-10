package no.ntnu.Idatx2001.model;

import java.util.ArrayList;
import java.util.List;

public class PostalNumberRegister {
    private ArrayList<PostalNumber> postalNumbers;

    /**
     *
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
     *
     * @return
     */
    public List<PostalNumber> getPostalNumbers() {
        return postalNumbers;
    }

    /**
     *
     * @param postalNumber
     */
    public void setPostalNumbers(PostalNumber postalNumber) {
        this.postalNumbers.add(postalNumber);
    }

    /**
     *
     * @param postalNumber
     */
    public void removePostalNumber(PostalNumber postalNumber){
        this.postalNumbers.remove(postalNumber);
    }
}
