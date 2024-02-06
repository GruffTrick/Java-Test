package sts.test.validator.impl;

import org.junit.jupiter.api.Test;
import sts.test.validator.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorImplTest {

    @Test
    void testisValidWithValid() {
        Validator validator = new ValidatorImpl();
        byte[] message = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14 };


        assertTrue(validator.isValid(message));
    }

    @Test
    void testWithoutDLE0x02() {
        Validator validator = new ValidatorImpl();

        byte lrc = 0;
        lrc ^= (byte) 0x02;
        lrc ^= (byte) 0x0A;
        lrc ^= (byte) 0x10;
        lrc ^= (byte) 0x07;
        lrc ^= (byte) 0x08;
        lrc ^= (byte) 0x03;
        System.out.println("LRC:" + lrc);

        byte[] message = { 0x02, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, lrc };

        assertFalse(validator.isValid(message));
    }

    @Test
    void testWithoutDLE0x03() {
        Validator validator = new ValidatorImpl();

        byte lrc = 0;
        lrc ^= (byte) 0x02;
        lrc ^= (byte) 0x0A;
        lrc ^= (byte) 0x03;
        lrc ^= (byte) 0x08;
        lrc ^= (byte) 0x03;
        lrc ^= (byte) 0x03;
        System.out.println("LRC:" + lrc);

        byte[] message = { 0x02, 0x10, 0x02, 0x0A, 0x03, 0x08, 0x03, lrc };

        assertFalse(validator.isValid(message));
    }

    @Test
    void testsDLEWithNonSpecialByte() {
        Validator validator = new ValidatorImpl();

        byte lrc = 0;
        lrc ^= (byte) 0x02;
        lrc ^= (byte) 0x0A;
        lrc ^= (byte) 0x08;
        lrc ^= (byte) 0x03;
        System.out.println("LRC:" + lrc);

        byte[] message = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x08, 0x03, lrc };

        assertFalse(validator.isValid(message));
    }

    @Test
    void testInvalidLRC() {
        Validator validator = new ValidatorImpl();

        byte correctLrc = 0x14;

        byte[] message = { 0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x03 };

        assertFalse(validator.isValid(message));

    }

    @Test
    void testInvalidArraySize() {
        Validator validator = new ValidatorImpl();

        byte[] message = { 0x02, 0x03, 0x00 };

        assertFalse(validator.isValid(message));
    }

}