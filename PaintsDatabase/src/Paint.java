public class Paint {
    /**
     * Holds the information about each pot of paint including brand, colour and reference
     */

    // initialise instance variables
    String brand;
    String colour;
    String reference;

    /**
     * Constructor for object of type Paint
     *
     * @param aBrand String the brand of paint e.g., Tamiya
     * @param aColour String the colour of the paint
     * @param aReference String the reference e.g., XF-1
     */

    public Paint (String aBrand, String aColour, String aReference)
    {
        brand = aBrand;
        colour = aColour;
        reference = aReference;
    }

    /**
     * Getter for brand
     * @return brand
     */

    public String getBrand()
    {
        return brand;
    }


    /**
     * Getter for colour
     * @return colour
     */

    public String getColour()
    {
        return colour;
    }


    /**
     * Getter for reference
     * @return reference
     */

    public String getReference()
    {
        return reference;
    }

    /**
     * Setter for brand
     * @param aBrand String The brand of the paint
     */

    public void setBrand(String aBrand)
    {
        brand = aBrand;
    }


    /**
     * Setter for colour
     * @param aColour String The colour of the paint
     */

    public void setColour(String aColour)
    {
        brand = aColour;
    }


    /**
     * Setter for reference
     * @param aReference String The reference of the paint
     */

    public void setReference(String aReference)
    {
        brand = aReference;
    }

}
