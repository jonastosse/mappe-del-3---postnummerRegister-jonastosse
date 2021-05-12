package no.ntnu.idatx2001.model;

import java.util.ArrayList;
import java.util.List;

public class PostalRegister {
    private final ArrayList<PostalNumber> postalNumbers;

    /**
     * The postalNumberRegister represent a collection of Postal.
     * This class can hold any object of type Postal or any subclass, like postal.
     */
    public PostalRegister(){
        this.postalNumbers = new ArrayList<>();
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
     * @param postal the postal to be removed
     */
    public void removePostal(Postal postal) throws RemoveException {
        if(postal instanceof PostalNumber){
            if(postalNumbers.contains((PostalNumber) postal)){
                this.postalNumbers.remove((PostalNumber) postal);
            } else {
                throw new RemoveException("Postal" + postal.getPostalCode() +
                        postal.getPostalPlace() + "does not exist");
            }
        }

    }

    @Override
    public String toString() {
        return "PostalNumberRegister{" +
                "postalNumbers=" + postalNumbers +
                '}';
    }
}
