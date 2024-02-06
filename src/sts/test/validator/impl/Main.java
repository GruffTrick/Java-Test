package sts.test.validator.impl;

import sts.test.validator.Validator;

public class Main {
    public static void main(String[] arguments) {
        Validator validator = new ValidatorImpl();

        byte[] messageValid = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14 };
        printAsHex(messageValid);
        System.out.println("Message is valid: " + validator.isValid(messageValid));
        System.out.println();

        byte[] messageWithoutDLE0x02 = { 0x02, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14 };
        printAsHex(messageWithoutDLE0x02);
        System.out.println("Message is valid: " + validator.isValid(messageWithoutDLE0x02));
        System.out.println();

        byte[] messageWithoutDLE0x03 = { 0x02, 0x10, 0x02, 0x0A, 0x03, 0x08, 0x03, 0x03 };
        printAsHex(messageWithoutDLE0x03);
        System.out.println("Message is valid: " + validator.isValid(messageWithoutDLE0x03));
        System.out.println();

        byte[] messageDLEWithNonSpecialByte = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x08, 0x03, 0x03 };
        printAsHex(messageDLEWithNonSpecialByte);
        System.out.println("Message is valid: " + validator.isValid(messageDLEWithNonSpecialByte));
        System.out.println();

        byte[] messageIncorrectLRCValue = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x03 };
        printAsHex(messageIncorrectLRCValue);
        System.out.println("Message is valid: " + validator.isValid(messageIncorrectLRCValue));
        System.out.println();

        byte[] messageInvalidArraySize = { 0x02, 0x03, 0x00 };
        printAsHex(messageInvalidArraySize);
        System.out.println("Message is valid: " + validator.isValid(messageInvalidArraySize));
        System.out.println();

    }

    /**
     * Prints the values of a message as hexadecimal for readability
     * @param message takes in an array of bytes
     */
    private static void printAsHex(byte[] message) {
        System.out.print("Message: ");
        for (byte b : message) {
            System.out.format("0x%02X, ", b);
        }
        System.out.println();
    }


}
