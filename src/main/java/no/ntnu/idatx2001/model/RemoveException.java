package no.ntnu.idatx2001.model;

/**
 * RemoveException handles exceptions based on removal of an object.
 */
public class RemoveException extends Exception {
    private static final long SerialVersionUID = 1L;

    /**
     * A constructor for a RemoveException.'
     *
     * @param message the message to be thrown
     */
    public RemoveException(String message){super(message);}
}
