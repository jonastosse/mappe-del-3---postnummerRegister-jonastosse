package no.ntnu.idatx2001.model;

/**
 * A class that holds all the values for a postal place.
 */
public abstract class Postal {
    //Eks. 6490
private String postalCode;
    //Eks. Eide
private String postalPlace;
    //Eks. Hustadvika
private String municipality;

    /**
     * Creates an instance of Postal
     *
     * @param postalCode the the postalCode of the postal
     * @param postalPlace the postalPlace of the postal
     */
    public Postal(String postalCode, String postalPlace, String municipality){

        //Check if postalCode is empty or null. If "empty/null" -> throw exception.
    if(postalCode == null || postalCode.isBlank()) {
        throw new IllegalArgumentException("postalCode cannot be empty or null");
    }
        //Check if postalPlace is empty or null. If "empty/null" -> throw exception.
    if(postalPlace == null || postalPlace.isBlank()){
        throw new IllegalArgumentException("postalPlace cannot be empty or null");
    }
        //Check if municipality is empty or null. If "empty/null" -> throw exception.
    if (municipality == null || municipality.isBlank()) {
        throw new IllegalArgumentException("municipality cannot be empty or null");
    }
    this.municipality = municipality.trim();
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

    /**
     * Sets the municipality.
     *
     * @param municipality the municipality to be set
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * Returns the municipality.
     *
     * @return the municipality
     */
    public String getMunicipality() {
        return municipality;
    }

    @Override
    public String toString() {
        return "Postal{" +
                "postalCode='" + postalCode + '\'' +
                ", postalPlace='" + postalPlace + '\'' +
                '}';
    }
}
