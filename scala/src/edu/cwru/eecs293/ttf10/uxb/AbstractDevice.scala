package edu.cwru.eecs293.ttf10.uxb

/** <p>
  * Represents a prototypical UXB device.
  * <p>
  * Case Western Reserve University
  * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
  * Programming Assignment 2  |  Due at beginning of discussion on Wednesday, September 7, 2016
  *
  * @see [[https://blackboard.case.edu/bbcswebdav/pid-1379538-dt-content-rid-4276971_1/courses/eecs293_vxl11/Hw2.pdf Hw2.pdf]]
  *
  * @author Theodore Frohlich <ttf10@case.edu>
  */
abstract class AbstractDevice[T <: AbstractDevice.Builder[T]] extends Device {

  protected final var productCode: Option[Int] = _
  protected final var serialNumber: Option[BigInt] = _
  protected final var version: Int = _
  protected final var connectors: List[Connector] = _

  /**
    * Initializes an abstract device from the given builder.
    * @param builder a builder for initializing an abstract device
    */
  protected def this(builder: AbstractDevice.Builder[T]) {
    this
    productCode = builder.getProductCode
    serialNumber = builder.getSerialNumber
    version = builder.getVersion
    val connectorTypes = builder.getConnectors
    connectors = List.empty
    for (index <- connectorTypes.indices) {
      connectors ::= new Connector(this, index, connectorTypes(index))
    }
  }

  override def getProductCode: Option[Int] = productCode

  override def getSerialNumber: Option[BigInt] = serialNumber

  override def getVersion: Int = version

  override def getDeviceClass: DeviceClass.DeviceClass = DeviceClass.UNSPECIFIED

  override def getConnectorCount: Int = connectors.size

  override def getConnectors: List[Connector] = connectors

  override def getConnector(index: Int): Connector = connectors(index)

}


object AbstractDevice {

  abstract class Builder[T] {

    protected var version: Int = _
    protected var productCode: Option[Int] = _
    protected var serialNumber: Option[BigInt] = _
    protected var connectors: List[Connector.Type.Type] = _

    /**
      * Creates a new builder with the given UXB version, no connectors, empty product code, and empty serial number.
      * @param version the UXB version that this device supports
      */
    def this(version: Int) {
      this
      this.version = version
      productCode(null.asInstanceOf[Int])
      serialNumber(null.asInstanceOf[BigInt])
      connectors(null)
    }

    def getProductCode: Option[Int] = productCode
    def getSerialNumber: Option[BigInt] = serialNumber
    def getVersion: Int = version

    /**
      * Sets the product code to the given value. If the <tt>productCode</tt> is null, set it to an empty optional.
      * @param productCode the product code of this device
      * @return [[getThis]]
      */
    def productCode(productCode: Int): T = {
      this.productCode =  // Must perform null check since Int is not a nullable type
        if (productCode != null.asInstanceOf[Int]) Option(productCode)
        else Option.empty
      getThis
    }

    /**
      * Sets the serial number to the given value. If the serial number is null, set it to an empty optional.
      * @param serialNumber the serial number of this device
      * @return [[getThis()]]
      */
    def serialNumber(serialNumber: BigInt): T = {
      this.serialNumber = Option(serialNumber)  // BigInt is a nullable type (see counterexample above)
      getThis
    }

    /**
      * Sets the connector types to a copy of the given value. If the argument is null, the device will have no connectors.
      * @param connectors the type of each connector in this device
      * @return [[getThis()]]
      */
    def connectors(connectors: List[Connector.Type.Type]): T = {
      this.connectors = if (connectors != null) connectors else List.empty
      getThis
    }

    protected def getThis: T

    /**
      * Returns a copy of the connector types.
      * @return a copy of the connector types
      */
    /*protected*/ def getConnectors: List[Connector.Type.Type] = connectors

    /**
      * Validates this builder.
      * @throws NullPointerException if and only if the version number is null
      */
    @throws[NullPointerException]
    protected def validate() {
      if (version == null.asInstanceOf[Int]) {
        throw new NullPointerException("Validation failed: version number is null.")
      }
    }

  }

}
