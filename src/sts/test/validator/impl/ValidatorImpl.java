package sts.test.validator.impl;
import sts.test.validator.Validator;

import java.util.ArrayList;

/**
 *  Implementation of {@link Validator} interface.
 */
public class ValidatorImpl implements Validator {
    final byte STX = 0x02;
    final byte ETX = 0x03;
    final byte DLE = 0x10;

    /**
     * Public no-argument constructor
     */
    public ValidatorImpl() {

    }

    /**
     * Indicate if the given message is valid.
     *
     * @param message The message to check
     * @return {@code true} if the message starts with STX, ends with ETX and the
     *         correct LRC, and has correctly-escaped data; {@code false}
     *         otherwise.
     *
     */
    public boolean isValid(byte[] message) {
        ArrayList<Byte> data = new ArrayList<>();
        System.out.println("Testing for Valid array length, STX, ETX");
        // Check if message is big enough.
        if (message.length <= 3) {
            System.out.println("message array size too small!");
            return false;
        }
        // Check for valid STX byte.
        if ( message[0] != STX) {
            System.out.println("Message does not contain an STX Byte, returning false");
            return false;
        }
        // Check for Valid ETX byte.
        if ( message[message.length-2] != ETX) {
            System.out.println("Message not ended with ETX Byte correctly, returning false");
            return false;
        }

        // Iterate through the message array.
        // Starts on index 1 after STX byte. Ends on the array element BEFORE last (the LRC).
        System.out.println("Iterating through Data Portion of message.");
        for (int index = 1; index < message.length-2; index++) {

            // Check for misused STX, ETX.
            if (isSpecialByte(message[index]) && message[index] != DLE) {
                System.out.format("SpecialByte Used without DLE at index %d, returning False%n", index);
                return false;
            }

            // Check if Data Link Escape byte
            if (message[index] == DLE) {
                System.out.format("DLE at index %d%n", index);
                index++;
                // Check if valid data link escape
                if (isSpecialByte(message[index])) {
                    data.add(message[index]);
                    continue;
                } else {
                    System.out.format("DLE used at index %d without STX, ETX or DLE after, returning false%n", index);
                    return false;
                }
            }
            // Append the Data to the List
            data.add(message[index]);
        }
        // Append the ETX
        data.add((byte) 0x03);

        // LRC Handling
        // Should be exclusive-OR value of each element in the data list.
        System.out.println("Testing LRC");
        byte lrc = 0;
        for (byte dataByte : data) {
            lrc ^= dataByte;
        }

        return lrc == message[message.length - 1];
    }


    /**
     * Validates if DataLinkEscape (DLE) has been used correctly by checking the following byte is STX, ETX or DLE.
     *
     * @param dataByte current byte after DLE being validated.
     * @return {@code true} if {@param dataByte is a valid STX/ETX/DLE character}.
     */
    private boolean isSpecialByte(byte dataByte) {
        return dataByte == STX || dataByte == DLE || dataByte == ETX;
    }

}
