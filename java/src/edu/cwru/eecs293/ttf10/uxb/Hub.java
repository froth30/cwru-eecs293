package edu.cwru.eecs293.ttf10.uxb;

/**
 * Represents a UXB hub, which can have multiple computer-side and peripheral-side connectors.
 *
 * Case Western Reserve University
 * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
 * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
 *
 * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
 *
 * Theodore Frohlich <ttf10@case.edu>
 */
public class Hub extends AbstractDevice<Hub.Builder> {

    /**
     * Initializes the hub from the given builder.
     * @param builder a builder
     */
    private Hub(Builder builder) {
        super(builder);
    }

    @Override
    public DeviceClass getDeviceClass() {
        return DeviceClass.HUB;
    }


    public static class Builder extends AbstractDevice.Builder<Builder> {

        /**
         * Creates a new builder with the given UXB version, no connectors, and with empty product code and serial number.
         * @param version the UXB version that this device supports
         */
        public Builder(Integer version) {
            super(version);
        }

        /**
         * Initializes the hub with the builder’s version, product code, serial number, and connector list.
         * @return the initialized hub
         * @throws IllegalStateException if the version is null, or the hub has no computer connector, or the hub has no peripheral connector.
         */
        public Hub build() throws IllegalStateException {
            // TODO
            return null;
        }

        /**
         * Returns this builder.
         * @return this builder
         */
        @Override
        protected Builder getThis() {
            return this;
        }

        /**
         *
         * @throws IllegalStateException if and only if the version number is null, or if the hub has no computer connector, or if the hub has no peripheral connector
         */
        @Override
        protected void validate() throws IllegalStateException {
            // TODO
        }

    }  // class Builder

}  // class Hub
