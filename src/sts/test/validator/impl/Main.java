package sts.test.validator.impl;

import sts.test.validator.Validator;

public class Main {
    public static void main(String[] arguments) {

        Validator validator = new ValidatorImpl();

        byte[] message = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14 };
        System.out.println("Message is valid: " + validator.isValid(message));

    }
}
