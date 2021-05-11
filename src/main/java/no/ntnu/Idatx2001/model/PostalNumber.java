package no.ntnu.Idatx2001.model;

/**
 * PostalNumber is a class containing all the information about a postalPlace or a postalNumber.
 * This class is a extension of Postal, and it Implements Municipality.
 */
public class PostalNumber extends Postal implements Comment{
        // Two first numbers for county code and two last for municipality code.
    private String municipalityCode;
        // Eks. Hustadvika.
    private String comment = "";
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
    public PostalNumber(String postalCode, String postalPlace, String municipality, String municipalityCode,
                        String category){

        super(postalCode, postalPlace, municipality);

            //Check if municipalityCode is empty or null. If "true" -> throw exception.
        if (municipalityCode == null || municipalityCode.isBlank()){
            throw new IllegalArgumentException("municipalityCode cannot be null");
        }
            //Check if category is empty or null. If "true" -> throw exception.
        if (category == null || category.isBlank()){
            throw new IllegalArgumentException("category cannot be null");
        }

        this.municipalityCode = municipalityCode.trim();
        this.category = category.trim();
        this.comment = comment;
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

    /**
     * Returns the comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }
    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }
}
