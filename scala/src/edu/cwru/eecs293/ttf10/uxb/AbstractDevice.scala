package edu.cwru.eecs293.ttf10.uxb

import Connector.Connector

/**
  * Represents a prototypical UXB device.
  *
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see <a href="Hw2.pdf">https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf</a>
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
object AbstractDevice extends Device {


  abstract class AbstractDevice[T <: AbstractDevice.Builder[T]](private val builder: Builder[T]) extends Device {

    private final val productCode = builder.productCode
    private final val serialNumber = builder.serialNumber
    private final val version = builder.version
    private final val connectors = List[Connector]

    override def getProductCode = productCode

    override def getSerialNumber = serialNumber

    override def getVersion = version

    override def getDeviceClass = DeviceClass.UNSPECIFIED

    override def getConnectorCount = connectors.size

    override def getConnectors: List[Connector.Type] = {
      null  // TODO
    }

    override def getConnector(index: Int): Connector = {
      // TODO
      connectors.get(index)
    }

  }


  abstract class Builder[T](val version: Int) {

    val productCode: Option[Int] = Option.empty
    val serialNumber: Option[BigInt] = Option.empty
    val connectors: List[Connector.Type] = _

    /**
      * Sets the product code to the given value. If the <tt>productCode</tt> is null, set it to an empty optional.
      * @param productCode the product code of this device
      * @return { @link #getThis()}
      */
    def productCode(productCode: Integer): T = {
      this.productCode = Option.ofNullable(productCode)
      getThis
    }

    /**
      * Sets the serial number to the given value. If the serial number is null, set it to an empty optional.
      * @param serialNumber the serial number of this device
      * @return { @link #getThis()}
      */
    def serialNumber(serialNumber: BigInt): T = {
      this.serialNumber = Option.ofNullable(serialNumber)
      getThis
    }

    /**
      * Sets the connector types to a copy of the given value. If the argument is null, the device will have no connectors.
      * @param connectors the type of each connector in this device
      * @return { @link #getThis()}
      */
    def connectors(connectors: List[Connector.Type.Type]): T = {
      this.connectors = connectors // TODO check if works
      getThis
    }

    protected def getThis: T

    /**
      * Returns a copy of the connector types.
      * @return a copy of the connector types
      */
    protected def getConnectors: List[Connector.Type.Type] = connectors // TODO if mutable, might need to return a copy instead

    /**
      * Validates this builder.
      * @throws NullPointerException if and only if the version number is null
      */
    @throws[NullPointerException]
    protected def validate() {
      if (version == null) {
        throw new NullPointerException("Validation failed: version number is null.")
      }
    }

  }


}
