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
public class Hub extends AbstractDevice.Builder<Hub.Builder> {

    private Hub(Builder builder) {
        // TODO
        super(null);
    }

    public DeviceClass getDeviceClass() {
        return DeviceClass.HUB;
    }

    @Override
    protected Builder getThis() {
        return null;
    }


    public static class Builder extends AbstractDevice.Builder<Builder> {

        public Builder(Integer version) {
            // TODO
            super(version);
        }

        public Hub build() {
            // TODO
            return null;
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        protected void validate() throws IllegalStateException {
            // TODO
        }
    }
}
