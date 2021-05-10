package no.ntnu.Idatx2001.model;

/**
 * A class that holds all the values for a postal place.
 */
public abstract class Postal {
    //Eks. 6490
private String postalCode;
    //Eks. Eide
private String postalPlace;

public Postal(String postalCode, String postalPlace){

    if(postalCode == null || postalCode.isBlank() || postalPlace == null || postalPlace.isBlank()){
        throw new IllegalArgumentException("postalCode and postalPlace cannot be empty or null");
    }

    this.postalCode = postalCode.trim();
    this.postalPlace = postalPlace.trim();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalPlace(String postalPlace) {
        this.postalPlace = postalPlace;
    }

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
