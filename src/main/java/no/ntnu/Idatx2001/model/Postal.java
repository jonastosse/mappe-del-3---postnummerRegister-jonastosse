package no.ntnu.Idatx2001.model;

/**
 * A class that holds all the values for a postal place.
 */
public abstract class Postal {
    //Eks. 6490
private String postalCode;
    //Eks. Eide
private String postalPlace;

    /**
     * Creates an instance of Postal
     *
     * @param postalCode the the postalCode of the postal
     * @param postalPlace the postalPlace of the postal
     */
    public Postal(String postalCode, String postalPlace){

    if(postalCode == null || postalCode.isBlank() || postalPlace == null || postalPlace.isBlank()){
        throw new IllegalArgumentException("postalCode and postalPlace cannot be empty or null");
    }

    this.postalCode = postalCode.trim();
    this.postalPlace = postalPlace.trim();
    }

    /**
     * Sets the postalCode.
     *
     * @param postalCode the postalCode to be set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the postalCode.
     *
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postalPlace.
     *
     * @param postalPlace the postalPlace to be set
     */
    public void setPostalPlace(String postalPlace) {
        this.postalPlace = postalPlace;
    }

    /**
     * Returns the postalPlace.
     * 
     * @return the postalPlace
     */
    public String getPostalPlace() {
        return postalPlace;
    }

    @Override
    public String toString() {
        return "Postal{" +
                "postalCode='" + postalCode + '\'' +
                ", postalPlace='" + postalPlace + '\'' +
                '}';
    }
}
