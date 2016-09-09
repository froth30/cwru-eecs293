package edu.cwru.eecs293.ttf10.uxb;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Represents a binary message carried between devices via UXB cable.
 *
 * Case Western Reserve University
 * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
 * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
 *
 * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
 *
 * Theodore Frohlich <ttf10@case.edu>
 */
public final class BinaryMessage implements Message {

    private final BigInteger value;

    public BinaryMessage(BigInteger value) {
        this.value = value == null ? BigInteger.ZERO : value;
    }

    public BigInteger getValue() {
        return value;
    }

    @Override
    public boolean equals(Object anObject) {
        return anObject != null
                && anObject instanceof BinaryMessage
                && Objects.equals(((BinaryMessage) anObject).value, value);
    }
}
