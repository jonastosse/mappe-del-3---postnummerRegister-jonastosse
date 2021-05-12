package no.ntnu.idatx2001.model;

/**
 * A interface to be used in any class who needs a comment.
 *
 * A comment is used so the user can choose to leave a comment of the place.
 * The comment is meant to be shown only to the user created the comment.
 */
public interface Comment {

    /**
     * Sets a comment to a any object.
     *
     * @param comment the comment to be set
     */
    void setComment(String comment);
}
