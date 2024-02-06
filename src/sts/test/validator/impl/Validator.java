package sts.test.validator.impl;
import sts.test.validator.Validate;

/**
 *  Implementation of {@ink Validator} interface.
 */
public class Validator implements Validate {


    /**
     * no-argument constructor
     */
    public Validator() {

    }

    /**
     * Indicate if the given message is valid.
     *
     * @param message The message to check
     * @return {@code true} if the message starts with STX, ends with ETX and the
     *         correct LRC, and has correctly-escaped data; {@code false}
     *         otherwise.
     * @todo Implement this method
     */
    public boolean isValid(byte[] message) {

        /*

        TODO: Implementation Here

        */

        return false;
    }
}
