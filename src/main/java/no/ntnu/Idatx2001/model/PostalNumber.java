package no.ntnu.Idatx2001.model;

/**
 *
 */
public class PostalNumber extends Postal implements Municipality{
        // Two first numbers for county code and two last for municipality code.
    private String municipalityCode;
        // Eks. Hustadvika.
    private String municipality;
        // Which category the municipality is in.
    private String category;

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
     *
     * @param municipalityCode
     */
    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    /**
     *
     * @return
     */
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
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
     *
     * @return
     */
    public String getMunicipality() {
        return municipality;
    }
}
