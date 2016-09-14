package edu.cwru.eecs293.ttf10.uxb

import java.math.BigInteger
import java.util.Collections
import java.util.List
import java.util.Optional

/**
  * Represents a prototypical UXB device.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
  * @author Theodore Frohlich <ttf10@case.edu>
  */
object AbstractDevice {

  abstract class Builder[T](var version: Integer)

  /**
    * Creates a new builder with the given UXB version, no connectors, empty product code, and empty serial number.
    *
    * @param version the UXB version that this device supports
    */ {
    connectors = Collections.emptyList
    productCode = Optional.empty
    serialNumber = Optional.empty
    private var productCode: Optional[Integer] = null
    private var serialNumber: Optional[BigInteger] = null
    private var connectors: util.List[Connector.Type] = null

    /**
      * Sets the product code to the given value. If the <tt>productCode</tt> is null, set it to an empty optional.
      *
      * @param productCode the product code of this device
      * @return { @link #getThis()}
      */
    def productCode(productCode: Integer): T = {
      this.productCode = Optional.ofNullable(productCode)
      getThis
    }

    /**
      * Sets the serial number to the given value. If the serial number is null, set it to an empty optional.
      *
      * @param serialNumber the serial number of this device
      * @return { @link #getThis()}
      */
    def serialNumber(serialNumber: BigInteger): T = {
      this.serialNumber = Optional.ofNullable(serialNumber)
      getThis
    }

    /**
      * Sets the connector types to a copy of the given value. If the argument is null, the device will have no connectors.
      *
      * @param connectors the type of each connector in this device
      * @return { @link #getThis()}
      */
    def connectors(connectors: util.List[Connector.Type]): T = {
      Collections.copy(this.connectors, connectors) // TODO check Collections.copy if exception is thrown
      getThis
    }

    protected def getThis: T

    /**
      * Returns a copy of the connector types.
      *
      * @return a copy of the connector types
      */
    protected def getConnectors: util.List[Connector.Type] = connectors // TODO if mutable, might need to return a copy instead

    /**
      * Validates this builder.
      *
      * @throws NullPointerException if and only if the version number is null
      */
    @throws[NullPointerException]
    protected def validate() {
      if (version == null) throw new NullPointerException("Validation failed: version number is null.")
    }
  }

}

abstract class AbstractDevice[T <: AbstractDevice.Builder[T]] protected(val builder: AbstractDevice.Builder[T])

/**
  * Initializes an abstract device from the given builder.
  *
  * @param builder a builder for initializing an abstract device
  */
  extends Device {
  productCode = builder.productCode
  serialNumber = builder.serialNumber
  version = builder.version
  connectors = null
  // TODO verify type Connector versus Connector.Type
  final private var productCode: Optional[Integer] = null
  final private var serialNumber: Optional[BigInteger] = null
  final private var version: Integer = null
  final private var connectors: util.List[Connector] = null

  def getProductCode: Optional[Integer] = productCode

  def getSerialNumber: Optional[BigInteger] = serialNumber

  def getVersion: Integer = version

  def getDeviceClass: DeviceClass = DeviceClass.UNSPECIFIED

  def getConnectorCount: Integer = connectors.size

  def getConnectors: util.List[Connector.Type] = {
    // TODO
    null
  }

  def getConnector(index: Int): Connector = {
    // TODO
    connectors.get(index)
  }
}
