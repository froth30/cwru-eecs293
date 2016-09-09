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

    protected AbstractDevice(Builder<T> builder) {
        productCode = builder.productCode;
        serialNumber = builder.serialNumber;
        version = builder.version;
        connectors = null;  // TODO verify type Connector versus Connector.Type
    }

    public Optional<Integer> getProductCode() {
        return productCode;
    }

    public Optional<BigInteger> getSerialNumber() {
        return serialNumber;
    }

    public Integer getVersion() {
        return version;
    }

    public DeviceClass getDeviceClass() {
        // TODO
        return null;
    }

    public Integer getConnectorCount() {
        return connectors.size();
    }

    public List<Connector.Type> getConnectors() {
        // TODO
        return null;
    }

    public Connector getConnector(int index) {
        // TODO
        return connectors.get(index);
    }


    public static abstract class Builder<T> {

        private Integer version;
        private Optional<Integer> productCode;
        private Optional<BigInteger> serialNumber;
        private List<Connector.Type> connectors;

        public Builder(Integer version) {
            this.version = version;
            connectors = Collections.emptyList();
            productCode = Optional.empty();
            serialNumber = Optional.empty();
        }

        public T productCode(Integer productCode) {
            this.productCode = Optional.ofNullable(productCode);
            return getThis();
        }

        public T serialNumber(BigInteger serialNumber) {
            this.serialNumber = Optional.ofNullable(serialNumber);
            return getThis();
        }

        public T connectors(List<Connector.Type> connectors) {
            Collections.copy(this.connectors, connectors);  // TODO check Collections.copy if exception is thrown
            return getThis();
        }

        protected abstract T getThis();

        protected List<Connector.Type> getConnectors() {
            return connectors;  // TODO if mutable, might need to return a copy instead
        }

        protected void validate() throws NullPointerException {
            if (version == null) {
                throw new NullPointerException("Validation failed: version number cannot be null.");
            }
        }

    }  // class Builder

}  // class AbstractDevice
