package edu.cwru.eecs293.ttf10.uxb;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Represents a UXB device, which is a UXB-enabled computer, peripheral, or hub.
 *
 * Case Western Reserve University
 * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
 * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
 *
 * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
 *
 * @author Theodore Frohlich <ttf10@case.edu>
 */
public interface Device {

    /**
     * Returns the product code of this device.
     * @return the product code of this device. If the product code is unknown, return an empty optional.
     */
    Optional<Integer> getProductCode();

    /**
     * Returns the serial number of this device.
     * @return the serial number of this device. If the serial number is unknown, return an empty optional.
     */
    Optional<BigInteger> getSerialNumber();

    /**
     * Returns the UXB version that this device supports.
     * @return the UXB version that this device supports
     */
    Integer getVersion();

    /**
     * Returns the class of this UXB device.
     * @return the class of this UXB device
     */
    DeviceClass getDeviceClass();

    /**
     * Returns the number of connectors that this device has.
     * @return the number of connectors that this device has
     */
    Integer getConnectorCount();

    /**
     * Returns the type of each connector in this device.
     * @return the type of each connector in this device
     */
    List<Connector.Type> getConnectors();

    /**
     * Returns the connector of this device at the given index.
     * @param index the plug number of the connector in this device
     * @return the connector of this device at the given index
     */
    Connector getConnector(int index);

    // TODO In future assignments, additional methods will be defined on the Device interface.

}
