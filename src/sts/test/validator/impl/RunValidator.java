package sts.test.validator.impl;

import sts.test.validator.Validate;
import sts.test.validator.example.ValidatorExample;

public class RunValidator {
    public static void main(String[] arguments) {

        Validate validate = new ValidatorExample();

        byte[] message =
                { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14 };
        System.out.println("Message is valid: " + validate.isValid(message));

    }
}
