package edu.cwru.eecs293.ttf10.uxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a prototypical UXB device.
 *
 * Case Western Reserve University
 * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
 * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
 *
 * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
 *
 * Theodore Frohlich <ttf10@case.edu>
 */
public abstract class AbstractDevice<T extends AbstractDevice.Builder<T>> implements Device {

    private final Optional<Integer> productCode;
    private final Optional<BigInteger> serialNumber;
    private final Integer version;
    private final List<Connector> connectors;

    /**
     * Initializes an abstract device from the given builder.
     * @param builder a builder for initializing an abstract device
     */
    protected AbstractDevice(Builder<T> builder) {
        productCode = builder.productCode;
        serialNumber = builder.serialNumber;
        version = builder.version;
        connectors = null;  // TODO verify type Connector versus Connector.Type
    }

    @Override
    public Optional<Integer> getProductCode() {
        return productCode;
    }

    @Override
    public Optional<BigInteger> getSerialNumber() {
        return serialNumber;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public DeviceClass getDeviceClass() {
        // TODO
        return null;
    }

    @Override
    public Integer getConnectorCount() {
        return connectors.size();
    }

    @Override
    public List<Connector.Type> getConnectors() {
        // TODO
        return null;
    }

    @Override
    public Connector getConnector(int index) {
        // TODO
        return connectors.get(index);
    }


    public static abstract class Builder<T> {

        private Integer version;
        private Optional<Integer> productCode;
        private Optional<BigInteger> serialNumber;
        private List<Connector.Type> connectors;

        /**
         * Creates a new builder with the given UXB version, no connectors, empty product code, and empty serial number.
         * @param version the UXB version that this device supports
         */
        public Builder(Integer version) {
            this.version = version;
            connectors = Collections.emptyList();
            productCode = Optional.empty();
            serialNumber = Optional.empty();
        }

        /**
         * Sets the product code to the given value. If the <tt>productCode</tt> is null, set it to an empty optional.
         * @param productCode the product code of this device
         * @return {@link #getThis()}
         */
        public T productCode(Integer productCode) {
            this.productCode = Optional.ofNullable(productCode);
            return getThis();
        }

        /**
         * Sets the serial number to the given value. If the serial number is null, set it to an empty optional.
         * @param serialNumber the serial number of this device
         * @return {@link #getThis()}
         */
        public T serialNumber(BigInteger serialNumber) {
            this.serialNumber = Optional.ofNullable(serialNumber);
            return getThis();
        }

        /**
         * Sets the connector types to a copy of the given value. If the argument is null, the device will have no connectors.
         * @param connectors the type of each connector in this device
         * @return {@link #getThis()}
         */
        public T connectors(List<Connector.Type> connectors) {
            Collections.copy(this.connectors, connectors);  // TODO check Collections.copy if exception is thrown
            return getThis();
        }

        protected abstract T getThis();

        /**
         * Returns a copy of the connector types.
         * @return a copy of the connector types
         */
        protected List<Connector.Type> getConnectors() {
            return connectors;  // TODO if mutable, might need to return a copy instead
        }

        /**
         * Validates this builder.
         * @throws NullPointerException if and only if the version number is null
         */
        protected void validate() throws NullPointerException {
            if (version == null) {
                throw new NullPointerException("Validation failed: version number is null.");
            }
        }

    }  // class Builder

}  // class AbstractDevice
