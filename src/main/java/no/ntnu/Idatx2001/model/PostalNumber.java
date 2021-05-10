package no.ntnu.Idatx2001.model;

/**
 * PostalNumber is a class containing all the information about a postalPlace or a postalNumber.
 * This class is a extension of Postal, and it Implements Municipality.
 */
public class PostalNumber extends Postal implements Municipality{
        // Two first numbers for county code and two last for municipality code.
    private String municipalityCode;
        // Eks. Hustadvika.
    private String municipality;
        // Which category the municipality is in.
    private String category;

    /**
     * Creates an instance of PostalNumber.
     *
     * @param postalCode        the postalCode of the postalNumber
     * @param postalPlace       the postalPlace of the postalNumber
     * @param municipalityCode  the municipalityCode of the postalNumber
     * @param municipality      the municipality of the postalNumber
     * @param category          the category of the postalNumber
     */
    public PostalNumber(String postalCode, String postalPlace, String municipalityCode, String municipality, String category){
        super(postalCode, postalPlace);

        if (municipalityCode == null || municipalityCode.isBlank() || municipality == null || municipality.isBlank() || category == null || category.isBlank()){
            throw new IllegalArgumentException("municipalityCode, municipality and category cannot be null");
        }

        this.municipalityCode = municipalityCode.trim();
        this.municipality = municipality.trim();
        this.category = category.trim();
    }


    /**
     * Sets the municipalityCode.
     *
     * @param municipalityCode the municipalityCode to be set
     */
    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    /**
     * Returns the municipalityCode.
     *
     * @return the municipalityCode
     */
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    /**
     * Sets the category.
     *
     * @param category the category to be set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    @Override
    public String getPostalPlace() {
        return super.getPostalPlace();
    }

    @Override
    public String getPostalCode() {
        return super.getPostalCode();
    }

    @Override
    public void setPostalCode(String postalCode) {
        super.setPostalCode(postalCode);
    }

    @Override
    public void setPostalPlace(String postalPlace) {
        super.setPostalPlace(postalPlace);
    }

    @Override
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
}
